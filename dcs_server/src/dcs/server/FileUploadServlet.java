package dcs.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class FileUploadServlet extends HttpServlet{
	private String name = "";
	private String desc = "";
	private String cid = "";
	private String uid = "";
	private String fieldname = "";
    private String filename = "";
    private byte[] data;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldname1 = item.getFieldName();
	                String fieldvalue = item.getString();
	                // ... (do your job here)
	                //System.out.println(fieldname1);
	                //desc = desc + "|" + item.getFieldName();
	                //desc = desc + "|" + item.getString();
	                if (fieldname1.equalsIgnoreCase("name")){
	                	name = fieldvalue;
	                	
	                }
	                if (fieldname1.equalsIgnoreCase("descr")){
	                	desc = fieldvalue;
	                }
	                if (fieldname1.equalsIgnoreCase("cid")){
	                	cid = fieldvalue;
	                }
	                if (fieldname1.equalsIgnoreCase("uid")){
	                	uid = fieldvalue;
	                }
	                if (fieldname1.equalsIgnoreCase("cust id")){
	                	cid = fieldvalue;
	                }
	                if (fieldname1.equalsIgnoreCase("user id")){
	                	uid = fieldvalue;
	                }
	            } else {
	            	
	                fieldname = item.getFieldName();
	                filename = FilenameUtils.getName(item.getName());
	                data = item.get();
	                 
	                //DataInputStream in = new DataInputStream(request.getInputStream()); 
	                //this loop converting the uploaded file into byte code   
	                //Vector<Byte> b = new Vector<Byte>();
	                
	                //while (in.available() > 0) {   
	                //byteRead = in.read(dataBytes, totalBytesRead,formDataLength); 
	                //b.add(dataBytes);
	                //b.add(in.readByte());
	                //byte b = in.readByte();
	                //System.out.println(totalBytesRead + " : " + b);
	                //dataBytes[totalBytesRead] = b;
	                //totalBytesRead += 1;   
	                //} 
	                //byte[] data = 
	                //System.out.println(dataBytes.toString());
	                //System.out.println(new String(item.get(),"US-ASCII"));
	                
	    				/*
	    				query = "SELECT keyval, id FROM attachments WHERE id = (SELECT MAX(id) FROM attachments)";
	    				
	    				rs = s.executeQuery(query);
	    				long id = 0;
	    				while (rs.next()){
	    					kv = new Integer(("" + (rs.getLong(1) / 55/ 33)));
	    					id = rs.getLong(2);
	    				}
	    				
	    				stmt5 = con.prepareCall("{call dcs_get_attachment (?, ?)}");
	    				stmt5.setLong(1, id);
	    				stmt5.setString(2, en.getKey(kv));
	    				
	    				query = "SELECT file_data FROM attachments WHERE id = (SELECT MAX(id) FROM attachments)";
	    				
	    				rs = stmt5.executeQuery();
	    				while (rs.next()){
	    					byte[] b = rs.getBytes(1);
	    					System.out.println(new String(b,"US-ASCII"));
	    				}
	    				*/
	    			
	            }
	       
	        }
	        Encryptor en = new Encryptor(DB_Conn.getConnKeyDB(), "1");
	        Connection con = DB_Conn.getConn();
            CallableStatement stmt5;
			try {
				/*
				stmt5 = con.prepareCall("{call dcs_add_attachment (?, ?, ?, ?, ?)}");
				stmt5.setString(1, filename);
				stmt5.setBinaryStream(2, in);
				stmt5.setLong(3, 1);
				stmt5.setLong(4, 1);
				stmt5.setString(5, en.getKey(1));
				*/
				int kv = 0;
				String query = "SELECT MAX(id) FROM attachments";
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query);
				while (rs.next()){
					kv = rs.getInt(1)%1000;
				}
				
				stmt5 = con.prepareCall("{call dcs_add_attachment (?, ?, ?, ?, ?, ?, ?, ?)}");
				stmt5.setString(1, filename);
				stmt5.setBytes(2, data);
				stmt5.setLong(3, new Long(uid));
				stmt5.setLong(4, new Long(cid));
				//stmt5.setLong(3, 1);
				//stmt5.setLong(4, 1);
				stmt5.setString(5, en.getKey(kv));
				stmt5.setLong(6, kv * 55 * 33);
				stmt5.setString(7,name);
				stmt5.setString(8, desc);
				//INSERT INTO debtor_phones(debtor_id, acct_id, phone_type, source, phone_number, entry_date)
				stmt5.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(name + "|" + desc + "|" + uid + "|" + cid);
				e.printStackTrace();
			}
	    } catch (FileUploadException e) {
	        throw new ServletException("Cannot parse multipart request.", e);
	    }

	    // ...
	}
}
