package dcs.server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.keyczar.Crypter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelmtech.dcs.client.datastructures.ComplaintData;
import com.kelmtech.dcs.client.datastructures.StatusCodes;


public class DataService extends HttpServlet {
	SetData sd = new SetData();

    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try { 
    		
    		String action = request.getParameter("action");
    		String cid = request.getParameter("cid");
    		if (action.equals("getComplaints")){
    			getComplaints(request, response, out, sd, mapper, cid);
    		}
    		if (action.equals("getComplaint")){
    			getComplaint(request, response, out, sd, mapper, cid);
    		}
    		if (action.equals("getNewComplaint")){
    			ComplaintData cd = sd.getCodes(cid);
    			cd.id = "0";
    			cd.client_id = cid;
    			String jsonString = mapper.writeValueAsString(cd);
				out.write(jsonString);
    		}
    		//String action = ("" + request.getParameter("action")).replaceAll(null, "");
    		/*
    		String cid = ("" + request.getAttribute("cid")).replaceAll(null, "");
    		if (action.equals("getComplaints")){
    			getComplaints(request, response, out, sd, mapper, cid);
    		}
    		*/
         
    	} catch (Exception e){
    		System.out.println("DataService doGet: " + e.toString());
    	}
    }
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		PrintWriter out = response.getWriter();
    		System.out.println(request.getParameterNames());
    		String action = request.getParameter("action");
    		String cid = request.getParameter("cid");
    		ObjectMapper mapper = new ObjectMapper();
    		if (action.equalsIgnoreCase("addstatus")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			StatusCodes sc = new StatusCodes();
    			sc.cid = cid;
    			sc.name = request.getParameter("name");
    			sc.user = request.getParameter("user");
    			sd.add_status_code(sc, new Long(cid));
    		}
    		if (action.equalsIgnoreCase("addsubstatus")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			StatusCodes sc = new StatusCodes();
    			sc.cid = cid;
    			sc.name = request.getParameter("name");
    			sc.user = request.getParameter("user");
    			sd.add_sub_status_code(sc, new Long(cid));
    		}
    		if (action.equalsIgnoreCase("addtype")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			StatusCodes sc = new StatusCodes();
    			sc.cid = cid;
    			sc.name = request.getParameter("name");
    			sc.user = request.getParameter("user");
    			sd.add_type_code(sc, new Long(cid));
    		}
    		if (action.equalsIgnoreCase("addsubtype")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			StatusCodes sc = new StatusCodes();
    			sc.cid = cid;
    			sc.name = request.getParameter("name");
    			sc.user = request.getParameter("user");
    			sd.add_sub_type_code(sc, new Long(cid));
    		}
    		if (action.equalsIgnoreCase("addcustomer")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			StatusCodes sc = new StatusCodes();
    			sc.cid = cid;
    			sc.name = request.getParameter("name");
    			sc.user = request.getParameter("user");
    			sd.add_customer_code(sc, new Long(cid));
    		}
    		if (action.equalsIgnoreCase("addbbb")){
    			//StatusCodes sc = mapper.readValue(request.getParameter("data"), StatusCodes.class);
    			
    			sd.addBBB(request.getParameter("comp_id"), cid);
    			ComplaintData cd2 = sd.get_complaint(request.getParameter("comp_id"), cid, request.getParameter("user"));
				String jsonString = mapper.writeValueAsString(cd2);
				out.write(jsonString);
    		}
    		if (action.equalsIgnoreCase("addeditcomplaint")){
    			Enumeration<String> parameterNames = request.getParameterNames();
    				 
    			while (parameterNames.hasMoreElements()) {
    				System.out.println(parameterNames.nextElement());
    			}
    			System.out.println(request.getParameter("comp"));
    			ComplaintData cd = mapper.readValue(request.getParameter("comp"), ComplaintData.class);
    			cd.client_id = cid;
    			System.err.println(cd);
    			cd.user_name = request.getParameter("user");
    			if (cd.complaint_date.length() >= 10){
    		    	cd.complaint_date = cd.complaint_date.substring(0,10);
    		    }
    		    if (cd.resolution_date.length() >= 10){
    		    	cd.resolution_date = cd.resolution_date.substring(0,10);
    		    } else {
    		    	cd.resolution_date = "";
    		    }
    			
    			if (cd.id.length() > 0 && !cd.id.trim().equals("0")){
    				System.out.println("Calling Edit Complaint");
    				String id = sd.edit_complaint(cd);
    				//ComplaintData cd2 = sd.get_complaint(id, cid);
    				String jsonString = mapper.writeValueAsString(cd);
    				out.write(jsonString);
    				
    			} else {
    				System.out.println("Calling New Complaint");
    				String id = sd.new_complaint(cd, cd.user_name);
    				ComplaintData cd2 = sd.get_complaint(id, cid, cd.user_name);
    				String jsonString = mapper.writeValueAsString(cd2);
    				out.write(jsonString);
    			}
    		}
    	} catch (Exception e){
    		System.out.println("DataService doPost: " + e.toString());
    	}
    }
    private String parse_date(String dte){
    	String out = "";
    	dte = dte.substring(dte.indexOf(" ")+1, dte.length());
    	String mo = dte.substring(0, dte.indexOf(" "));
    	dte = dte.substring(dte.indexOf(" ")+1, dte.length());
    	String day = dte.substring(0, dte.indexOf(" "));
    	dte = dte.substring(dte.indexOf(" ")+1, dte.length());
    	String yr = dte.substring(0, dte.indexOf(" "));
    	
    	String num_mo = "";
    	if (mo.equalsIgnoreCase("jan")){
    		num_mo = "01";
    	}
    	if (mo.equalsIgnoreCase("feb")){
    		num_mo = "02";
    	}
    	if (mo.equalsIgnoreCase("mar")){
    		num_mo = "03";
    	}
    	if (mo.equalsIgnoreCase("apr")){
    		num_mo = "04";
    	}
    	if (mo.equalsIgnoreCase("may")){
    		num_mo = "05";
    	}
    	if (mo.equalsIgnoreCase("jun")){
    		num_mo = "06";
    	}
    	if (mo.equalsIgnoreCase("jul")){
    		num_mo = "07";
    	}
    	if (mo.equalsIgnoreCase("aug")){
    		num_mo = "08";
    	}
    	if (mo.equalsIgnoreCase("sep")){
    		num_mo = "09";
    	}
    	if (mo.equalsIgnoreCase("oct")){
    		num_mo = "10";
    	}
    	if (mo.equalsIgnoreCase("nov")){
    		num_mo = "11";
    	}
    	if (mo.equalsIgnoreCase("dec")){
    		num_mo = "12";
    	}
    	out = yr + "-" + num_mo + "-" + day;
    	return(out);
    }
    private void getComplaints(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SetData sd, ObjectMapper mapper, String cid){
    	try { 
	    	String gettypes = request.getParameter("comp_type");
			if (gettypes.equalsIgnoreCase("all")){
				ComplaintData cd1[] = sd.get_complaint_open(cid);
				ComplaintData cd2[] = sd.get_complaint_closed(cid);
				ComplaintData cd[] = new ComplaintData[cd1.length + cd2.length];
				for (int i=0; i<cd1.length; i++){
					cd[i] = cd1[i];
				}
				for (int i=0; i<cd2.length; i++){
					cd[i + cd1.length] = cd2[i];
				}
				String jsonString = mapper.writeValueAsString(cd);
				out.write(jsonString);
			}
			if (gettypes.equalsIgnoreCase("open")){
				ComplaintData cd[] = sd.get_complaint_open(cid);
				String jsonString = mapper.writeValueAsString(cd);
				out.write(jsonString);
			}
			if (gettypes.equalsIgnoreCase("closed")){
				ComplaintData cd[] = sd.get_complaint_closed(cid);
				String jsonString = mapper.writeValueAsString(cd);
				out.write(jsonString);
			}
    	} catch (Exception e){
    		System.out.println("getComplaints: " + e.toString());
    	}
    }
    private void getComplaint(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SetData sd, ObjectMapper mapper, String cid){
    	try { 
	    	String id = request.getParameter("id");
	    	String user = request.getParameter("user");
	    	ComplaintData cd = sd.get_complaint(id, cid, user);
				String jsonString = mapper.writeValueAsString(cd);
				out.write(jsonString);
			
    	} catch (Exception e){
    		System.out.println(new java.util.Date() + "getComplaint: " + e.toString());
    	}
    }
}
