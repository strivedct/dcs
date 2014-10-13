package dcs.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.commons.lang3.RandomStringUtils;
import org.keyczar.*;

public class Encryptor {
	private Vector<String> keys = new Vector<String>();
	private String pw = "Mhjanshiu98911jkladsfisai";
	private String salt = "";
	private String iv = "";
	//System key that encrypts key lists
	
	
	public Encryptor(Connection con, String client_id){
		try {
			keys = new Vector<String>();
			Crypter crypter = new Crypter("/etc/dcs/keys");
			Statement stmt = con.createStatement();
			String q = "SELECT * FROM keys WHERE client_id = " + client_id + " ORDER BY client_key_id";
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()){
				keys.add(crypter.decrypt(rs.getString("key")));
			}
			if (keys.size() > 2){
				
			} else {
				//No keys for this client id, need to generate them
				RandomStringUtils ru = new RandomStringUtils();
				for (int i=0; i<1000; i++){
					keys.add(ru.randomAlphanumeric(32));
					CallableStatement stmt5 = con.prepareCall("{call add_key (?, ?, ?)}");
					//String q_in = "INSERT INTO keys (client_id, client_key_id, key) VALUES (" + client_id + "," + i + ",'" + 
					stmt5.setLong(1,new Long (client_id));
					stmt5.setInt(2, i);
					stmt5.setString(3, crypter.encrypt(keys.get(i)));
					stmt5.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getKeys(File f, Crypter crypter){
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(f));
 
			while ((sCurrentLine = br.readLine()) != null) {
				String e_message = sCurrentLine.substring(0,sCurrentLine.length()-1);
				keys.add(crypter.decrypt(e_message));
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void getIV(File fiv){
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(fiv));
 
			while ((sCurrentLine = br.readLine()) != null) {
				iv = sCurrentLine.substring(0,sCurrentLine.length()-1);
				
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void getSalt(File fsalt){
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(fsalt));
 
			while ((sCurrentLine = br.readLine()) != null) {
				salt = sCurrentLine.substring(0,sCurrentLine.length()-1);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	private void saveIV(File fiv){
		try {
		FileWriter fw = new FileWriter(fiv.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
			try {
				
					bw.write(iv);
					
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)bw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void saveSalt(File fs){
		try {
		FileWriter fw = new FileWriter(fs.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
			try {
				
					bw.write(salt);
					
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)bw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void genKeys(Crypto en, File f){
		try {
			f.createNewFile();
			File ftmp = new File("/etc/dcs-tmp.conf");
			ftmp.createNewFile();
		FileWriter fw = new FileWriter(ftmp.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);


			RandomStringUtils ru = new RandomStringUtils();
			for (int i=0; i<1000; i++){
				keys.add(ru.randomAlphanumeric(32));
				bw.write(keys.get(i) + "\n");
				//Cipher c = Cipher.getInstance("AES");
				//SecretKeySpec k =
				//  new SecretKeySpec(key, "AES");
				//c.init(Cipher.ENCRYPT_MODE, k);
				
				//data.t
				//String salt = "dsajfk;na;jfdas;jkdda";

		        //String value = Protector.encrypt(keys.get(i), salt);
		        //System.out.println("value");
				//bw.write(value + "\n");
			}
			
			bw.close();
			
			en.WriteEncryptedFile(ftmp, f);
			ftmp.delete();
			
			} catch (Exception e) {
					e.printStackTrace();
			}
	}
	public String getKey(int val){
		return (keys.get(val%1000));
	}
	/*
	public byte[] encryptit(byte[] in, int val){
		byte[] out = new byte[1];
		
         byte[] bytes = in;
         		//... secret sequence of bytes
         try {	
        	 	String dakey = keys.get(val % keys.size()) + keys.get(val % keys.size()-2) + keys.get(val % keys.size()-6) + keys.get(val % keys.size()-9);
        	 	
         } catch (Exception e){
        	 
         }
		return(out);
	}
	public byte[] decryptit(byte[] in, int val){
		byte[] out = new byte[1];
		//byte[] key = //... we know the secret!
				byte[] encryptedData = in;
				try {

	        	 	String dakey = keys.get(val % keys.size()) + keys.get(val % keys.size()-2) + keys.get(val % keys.size()-6) + keys.get(val % keys.size()-9);
	        	 	byte[] dasalt = keys.get(val % keys.size() - 7).getBytes();
	        	 	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        		KeySpec spec = new PBEKeySpec(dakey.toCharArray(), dasalt, 65536, 128);
	        		SecretKey tmp = factory.generateSecret(spec);
	        		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
	        		
	        		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        		cipher.init(Cipher.DECRYPT_MODE, secret);
				byte[] data = cipher.doFinal(encryptedData);
				out = data;
				 } catch (Exception e){
		        	 
		         }
		return(out);
	}
	*/
}
