package dcs.server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keyczar.Crypter;


public class DownloadServlet extends HttpServlet {


    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	OutputStream out = response.getOutputStream();
    	try { 
    	Crypter crypter = new Crypter("/etc/dcs/keys");
    	 
    	 
         String id = request.getParameter("id");
         String rid = crypter.decrypt(id);
         String cid = request.getParameter("cid");
         String crid = crypter.decrypt(cid);
         String fileName = "";
         String fileType = "";
         // Find this file id in database to get file name, and file type
         Connection mdb = DB_Conn.getConn();
         Connection edb = DB_Conn.getConnKeyDB();
         Encryptor en = new Encryptor(edb, crid);
         // You must tell the browser the file type you are going to send
         // for example application/pdf, text/plain, text/html, image/jpg
         
         CallableStatement stmt5 = mdb.prepareCall("{call dcs_get_attachment_info (?)}");
			//String q_in = "INSERT INTO keys (client_id, client_key_id, key) VALUES (" + client_id + "," + i + ",'" + 
			stmt5.setLong(1,new Long (rid));
			
			ResultSet rs = stmt5.executeQuery();
			long keyval = 0;
			while (rs.next()){
				fileName = rs.getString(1);
				
				keyval = rs.getLong(2) / 55 / 33;
			}
			for (int i=fileName.length(); i > 0; i--){
				if (fileName.substring(i-1,i).equals(".")){
					fileType = "application/" + fileName.substring(i, fileName.length());
					i=0;
				}
			}
         response.setContentType(fileType);

         // Make sure to show the download dialog
         response.setHeader("Content-disposition","attachment; filename=" + URLEncoder.encode(fileName));

         // Assume file name is retrieved from database
         // For example D:\\file\\test.pdf

         //File my_file = new File(fileName);

         // This should send the file to browser
         
         //FileInputStream in = new FileInputStream(my_file);
         //String key = en.getKey(new Integer ("" + keyval));
         stmt5 = mdb.prepareCall("{call dcs_get_attachment (?, ?)}");
			stmt5.setLong(1, new Long (rid));
			stmt5.setString(2, en.getKey(new Integer ("" + keyval)));
			
			
			
			rs = stmt5.executeQuery();
			byte[] b = null;
			while (rs.next()){
				out.write(rs.getBytes(1));
				//System.out.println(new String(b,"US-ASCII"));
			}
            //out.write(buffer);
         
    	} catch (Exception e){
    		System.out.println(e.toString());
    	} finally {
         out.flush();
    	}
    }
}
