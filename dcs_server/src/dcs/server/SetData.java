package dcs.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import com.kelmtech.dcs.client.datastructures.BBBComplaintData;
import com.kelmtech.dcs.client.datastructures.ComplaintData;
import com.kelmtech.dcs.client.datastructures.LawsuitData;
import com.kelmtech.dcs.client.datastructures.StatusCodes;
import com.kelmtech.dcs.client.datastructures.TaskData;
import com.kelmtech.dcs.client.datastructures.UserData;

public class SetData {
	Connection con;
	
	public SetData(){ 
		con = DB_Conn.getConn();
		
	}
	public String new_complaint(ComplaintData cd, String username){
		String rtn = "error: failed to add new complaint";
		System.out.println(cd.complaint_date);
		System.out.println(("" + cd.resolution_date).replaceAll("null", "").trim());
		try {
			System.out.println(cd.complaint_date);
			System.out.println(("" + cd.resolution_date).replaceAll("null", "").trim());
			Encryptor en = new Encryptor(DB_Conn.getConnKeyDB(), cd.client_id);
			check_con();
			CallableStatement stmt5;
		    stmt5 = con.prepareCall("{call dcs_get_max_complain ()}");
		    int val = 0;
		    ResultSet rs = stmt5.executeQuery();
		    while (rs.next()){
		    	val = (int) (rs.getLong(1)%1000);
		    }
		    stmt5 = con.prepareCall("{call dcs_get_userid (?,?)}");
		    stmt5.setLong(1, new Long(cd.client_id));
		    stmt5.setString(2, username);
		    rs = stmt5.executeQuery();
		    while (rs.next()){
		    	cd.entered_by = rs.getString(1);
		    }
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    //System.out.println("Complaint Date: " + cd.complaint_date.substring);
		    if (cd.resolved_by.length() == 0){
		    	cd.resolved_by = "0";
		    }
		    stmt5 = con.prepareCall("{call dcs_add_complaint(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)}");
		    stmt5.setString(1, cd.our_account);
		    stmt5.setString(2, cd.customer_account);
		    stmt5.setString(3, cd.customer);
		    stmt5.setString(4, cd.complaint_date);
		    stmt5.setString(5, cd.complaint_text);
		    stmt5.setString(6, cd.status);
		    stmt5.setString(7, cd.sub_status);
		    stmt5.setString(8, ("" + cd.resolution_date).replaceAll("null", "").trim());
		    stmt5.setString(9, cd.resolution_text);
		    stmt5.setString(10, cd.name);
		    stmt5.setString(11, cd.phone);
		    stmt5.setString(12, cd.ssn);
		    stmt5.setLong(13, new Long(cd.client_id));
		    stmt5.setString(14, cd.type);
		    stmt5.setString(15, cd.sub_type);
		    stmt5.setLong(16, new Long(cd.entered_by));
		    stmt5.setLong(17, new Long(cd.resolved_by));
		    stmt5.setString(18, en.getKey(val));
			stmt5.setLong(19, val * 55 * 33);
			stmt5.setString(20, username);
			stmt5.setString(21, cd.address);
			stmt5.setString(22, cd.city);
			stmt5.setString(23, cd.state);
			stmt5.setString(24, cd.zip);
			stmt5.setString(25, cd.phone2);
			ResultSet rsin = stmt5.executeQuery();
			while (rsin.next()){
				rtn = rsin.getString(1);
			}
		    /*
		     our_account character varying, 
		     customer_account character varying, 
		     customer bigint, 
		     complaint_date date, 
		     complaint_text text, 
		     status character varying, 
		     sub_status character varying, 
		     resolution_date date, 
		     resolution_text text, 
		     name character varying, 
		     phone character varying, 
		     ssn text, 
		     client_id bigint, 
		     type character varying, 
		     sub_type character varying, 
		     entered_by bigint, 
		     resolved_by bigint, 
		     e_pw character varying, 
		     keyval bigint)

		     */
		} catch (Exception e){
			System.out.println("edit_complaint: " + e.toString());
		}
		
		return(rtn);
	}
	public String edit_task(TaskData td, String username){
		String rtn = "error: failed to add new complaint";
		try {
			/*
			task_desc text, date_due character varying, group_id bigint, 
			assigned_to bigint, task_response text, task_closed_date character varying, 
			task_closed_by bigint, client_id bigint, complaint_id bigint, user_name character varying, task_id bigint)
			 */
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_edit_task(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		    stmt5.setString(1, td.task_desc);
		    //stmt5.setLong(2, td.created_by);
		    //stmt5.setDate(3, new java.sql.Date(new Date().getTime()));
		    stmt5.setString(2, td.date_due);
		    stmt5.setString(3, td.group_id);
		    stmt5.setString(4, td.assigned_to);
		    stmt5.setString(5, td.task_response);
		    stmt5.setString(6, td.task_closed_date);
		    stmt5.setString(7, td.task_closed_by);
		    stmt5.setLong(8, new Long(td.client_id));
		    stmt5.setLong(9, new Long(td.complaint_id));
		    stmt5.setString(10, username);
		    stmt5.setLong(11, new Long(td.id));
			stmt5.execute();
			
		    /*
		   dcs_edit_task(task_desc text, 
date_due character varying, 
group_id bigint, assigned_to bigint, task_response text, 
task_closed_date character varying, task_closed_by bigint, 
client_id bigint, complaint_id bigint, 
user_name character varying(50), task_id bigint)
		     */
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public TaskData get_task(Long task_id, Long client_id, String username){
		TaskData td = new TaskData();
		try {
			
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_get_task(?, ?, ?)}");
		    stmt5.setLong(1, task_id);
		    stmt5.setLong(2, client_id);
		    stmt5.setString(3, username);
		    ResultSet rs = stmt5.executeQuery();
		    
		    while (rs.next()){
		    	/*
		    	 SELECT id, task_desc, created_by, date_created, date_due, group_id, 
			       assigned_to, task_response, task_closed_date, task_closed_by, 
			       client_id, complaint_id
			  	 FROM tasks;
		    	 */
		    	td.id = rs.getString(1);
		    	td.task_desc = rs.getString(2);
		    	td.created_by = rs.getString(3);
		    	td.date_created = rs.getString(4).substring(0,10);
		    	td.date_due = rs.getString(5);
		    	td.group_id = rs.getString(6);
		    	td.assigned_to = rs.getString(7);
		    	td.task_response = ("" + rs.getString(8)).replaceAll("null", "");;
		    	td.task_closed_date = ("" + rs.getString(9)).replaceAll("null", "");;
		    	td.task_closed_by = rs.getString(10);
		    	td.client_id = rs.getString(11);
		    	td.complaint_id = rs.getString(12);
		    }
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(td); 
	}
	public String update_bbb(BBBComplaintData ld, UserData ud){
		String rtn = "";

			try {

				check_con();
				CallableStatement stmt5;
			    if (ld.id.length() > 0){
			    	//Existing BBB

			    	 
			    	 stmt5 = con.prepareCall("{dcs_edit_bbb_complaint(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?" +

			    	 		")}");
					 
			    	
			    	 stmt5.setString(1, ld.bbb_complaint_number); //bbb_complaint_number character varying
			    	 stmt5.setString(2, ld.bbb_complain_date);// bbb_complain_date text
			    	 stmt5.setString(3, ld.bbb_email);//bbb_email text
			    	 stmt5.setString(4, ld.bbb_name);//bbb_name text
			    	 stmt5.setString(5, ld.bbb_contact_name);//bbb_contact_name text
			    	 stmt5.setString(6, ld.bbb_address1);// bbb_address1 text
			    	 stmt5.setString(7, ld.bbb_address2);//bbb_address2 text
			    	 stmt5.setString(8, ld.bbb_city);//bbb_city text
			    	 stmt5.setString(9, ld.bbb_state);//bbb_state text
			    	 stmt5.setString(10, ld.bbb_zipcode);//bbb_zipcode text
			    	 stmt5.setString(11, ld.bbb_phone);//bbb_phone text
			    	 stmt5.setString(12, ld.bbb_phone_ext);//bbb_phone_ext text
			    	 stmt5.setString(13, ld.bbb_fax);//bbb_fax text
			    	 stmt5.setString(14, ld.bbb_url);//bbb_url text
			    	 stmt5.setString(15, ld.bbb_complaint_summary);//bbb_complaint_summary text
			    	 stmt5.setString(16, ld.bbb_response_text);//bbb_response_text text
			    	 stmt5.setString(17, ld.bbb_response_date);//bbb_response_date text
			    	 stmt5.setLong(18, new Long(ld.client_id));//client_id bigint
			    	 stmt5.setString(19, ld.status);//status character varying
			    	 stmt5.setLong(20, new Long(ld.response_by));//response_by bigint
			    	 stmt5.setLong(21, new Long(ld.entered_by));//entered_by bigint
			    	 stmt5.setLong(22, new Long(ld.complaint_id));//complaint_id bigint
			    	 stmt5.setLong(23, new Long(ld.id));//bbb_id bigint
			    	 stmt5.setString(24, ud.user_name);//username character varying)
			    	
			    	 stmt5.execute();
			    	 
			    } else {
			    	//New BBB
			    	 stmt5 = con.prepareCall("{dcs_add_bbb_complaint(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?" +

			    	 		")}");
					 
			    	
			    	 stmt5.setString(1, ld.bbb_complaint_number); //bbb_complaint_number character varying
			    	 stmt5.setString(2, ld.bbb_complain_date);// bbb_complain_date text
			    	 stmt5.setString(3, ld.bbb_email);//bbb_email text
			    	 stmt5.setString(4, ld.bbb_name);//bbb_name text
			    	 stmt5.setString(5, ld.bbb_contact_name);//bbb_contact_name text
			    	 stmt5.setString(6, ld.bbb_address1);// bbb_address1 text
			    	 stmt5.setString(7, ld.bbb_address2);//bbb_address2 text
			    	 stmt5.setString(8, ld.bbb_city);//bbb_city text
			    	 stmt5.setString(9, ld.bbb_state);//bbb_state text
			    	 stmt5.setString(10, ld.bbb_zipcode);//bbb_zipcode text
			    	 stmt5.setString(11, ld.bbb_phone);//bbb_phone text
			    	 stmt5.setString(12, ld.bbb_phone_ext);//bbb_phone_ext text
			    	 stmt5.setString(13, ld.bbb_fax);//bbb_fax text
			    	 stmt5.setString(14, ld.bbb_url);//bbb_url text
			    	 stmt5.setString(15, ld.bbb_complaint_summary);//bbb_complaint_summary text
			    	 stmt5.setString(16, ld.bbb_response_text);//bbb_response_text text
			    	 stmt5.setString(17, ld.bbb_response_date);//bbb_response_date text
			    	 stmt5.setLong(18, new Long(ld.client_id));//client_id bigint
			    	 stmt5.setString(19, ld.status);//status character varying
			    	 stmt5.setLong(20, new Long(ld.response_by));//response_by bigint
			    	 stmt5.setLong(21, new Long(ld.entered_by));//entered_by bigint
			    	 stmt5.setLong(22, new Long(ld.complaint_id));//complaint_id bigint
			    	
			    	 stmt5.execute();
			    }
			   
			   
			} catch (Exception e){
				System.out.println(e.toString());
			}
		return(rtn);
	}
	public String update_lawsuit(LawsuitData ld, UserData ud){
		String rtn = "";

			try {

				check_con();
				CallableStatement stmt5;
			    if (ld.id.length() > 0){
			    	//Existing Lawsuit

			    	//new java.sql.d
			    	//stmt5.setString(32, ld.date_filed);//date_filed date
			    	// stmt5.setString(33, ld.date_entered);//date_entered date
			    	// stmt5.setString(34, ld.date_updated);//date_updated date
			    	// stmt5.setString(37, ld.resolution_date);
			    	// stmt5.setString(45, ld.court_date);//court_date date
			    	 
			    	 
			    	 stmt5 = con.prepareCall("{call dcs_edit_lawsuit(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
			    	 		")}");
					 
			    	 stmt5.setString(1, ld.case_num); //case_num character varying
			    	 stmt5.setString(2, ld.atty_name);//atty_name text
			    	 stmt5.setString(3, ld.atty_firm);//atty_firm text
			    	 stmt5.setString(4, ld.atty_add1);//atty_add1 text
			    	 stmt5.setString(5, ld.atty_add2);//atty_add2 text
			    	 stmt5.setString(6, ld.atty_city);//atty_city text
			    	 stmt5.setString(7, ld.atty_state);//atty_state character varying
			    	 stmt5.setString(8, ld.atty_zipcode);//atty_zipcode text
			    	 stmt5.setString(9, ld.atty_phone);//atty_phone text
			    	 stmt5.setString(10, ld.atty_fax);//atty_fax text
			    	 stmt5.setString(11, ld.atty_email);//atty_email text
			    	 stmt5.setString(12, ld.our_atty_name);//our_atty_name text
			    	 stmt5.setString(13, ld.our_atty_firm);//our_atty_firm text
			    	 stmt5.setString(14, ld.our_atty_add1);//our_atty_add1 text
			    	 stmt5.setString(15, ld.our_atty_add2);//our_atty_add2 text
			    	 stmt5.setString(16, ld.our_atty_city);//our_atty_city text
			    	 stmt5.setString(17, ld.our_atty_state);//our_atty_state character varying
			    	 stmt5.setString(18, ld.our_atty_zipcode);//our_atty_zipcode text
			    	 stmt5.setString(19, ld.our_atty_phone);//our_atty_phone text
			    	 stmt5.setString(20, ld.our_atty_fax);//our_atty_fax text
			    	 stmt5.setString(21, ld.our_atty_email);//our_atty_email text
			    	 stmt5.setString(22, ld.court_name);//court_name text
			    	 stmt5.setString(23, ld.county);//county text
			    	 stmt5.setString(24, ld.court_address);//court_address text
			    	 stmt5.setString(25, ld.court_address2);//court_address2 text
			    	 stmt5.setString(26, ld.court_city);//court_city text
			    	 stmt5.setString(27, ld.court_state);//court_state text
			    	 stmt5.setString(28, ld.court_zip);//court_zip text
			    	 stmt5.setString(29, ld.court_phone);//court_phone text
			    	 stmt5.setString(30, ld.court_fax);//court_fax text
			    	 stmt5.setString(31, ld.clerk_name);//clerk_name text
			    	 stmt5.setString(32, ld.date_filed);//date_filed date
			    	 stmt5.setString(33, ld.date_entered);//date_entered date
			    	 stmt5.setString(34, ld.date_updated);//date_updated date
			    	 stmt5.setString(35, ld.settlement_amount);//settlement_amount text
			    	 stmt5.setString(36, ld.status);//status character varying
			    	 stmt5.setString(37, ld.resolution_date);//resolution_date date
			    	 stmt5.setString(38, ld.resolution_text);//resolution_text text
			    	 stmt5.setBoolean(39, ld.tcpa);//tcpa boolean
			    	 stmt5.setBoolean(40, ld.fdcpa);//fdcpa boolean
			    	 stmt5.setBoolean(41, ld.fcra);//fcra boolean
			    	 stmt5.setBoolean(42, ld.fcba);//fcba boolean
			    	 stmt5.setBoolean(43, ld.other);//other boolean
			    	 stmt5.setString(44, ld.other_text);//other_text text
			    	 stmt5.setString(45, ld.court_date);//court_date date
			    	 stmt5.setString(46, ld.court_date_text);//court_date_text text
			    	 stmt5.setString(47, ld.assigned_to);//assigned_to text
			    	 stmt5.setLong(48, new Long(ld.complaint_id));//complaint_id bigint
			    	 stmt5.setLong(49, new Long(ld.client_id));//client_id bigint
			    	 stmt5.setLong(50, new Long(ld.id));//lawsuit_id bigint
			    	 stmt5.setString(51, ud.user_name);//user_name character varying)
			    	 stmt5.execute();
			    	 
			    } else {
			    	//New Lawsuit
			    	 stmt5 = con.prepareCall("{call add_lawsuit(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			    			 " ?, ?, ?, ?, ?, ?, ?, ?, ?" +
			    	 		")}");
					 
			    	 stmt5.setString(1, ld.case_num); //case_num character varying
			    	 stmt5.setString(2, ld.atty_name);//atty_name text
			    	 stmt5.setString(3, ld.atty_firm);//atty_firm text
			    	 stmt5.setString(4, ld.atty_add1);//atty_add1 text
			    	 stmt5.setString(5, ld.atty_add2);//atty_add2 text
			    	 stmt5.setString(6, ld.atty_city);//atty_city text
			    	 stmt5.setString(7, ld.atty_state);//atty_state character varying
			    	 stmt5.setString(8, ld.atty_zipcode);//atty_zipcode text
			    	 stmt5.setString(9, ld.atty_phone);//atty_phone text
			    	 stmt5.setString(10, ld.atty_fax);//atty_fax text
			    	 stmt5.setString(11, ld.atty_email);//atty_email text
			    	 stmt5.setString(12, ld.our_atty_name);//our_atty_name text
			    	 stmt5.setString(13, ld.our_atty_firm);//our_atty_firm text
			    	 stmt5.setString(14, ld.our_atty_add1);//our_atty_add1 text
			    	 stmt5.setString(15, ld.our_atty_add2);//our_atty_add2 text
			    	 stmt5.setString(16, ld.our_atty_city);//our_atty_city text
			    	 stmt5.setString(17, ld.our_atty_state);//our_atty_state character varying
			    	 stmt5.setString(18, ld.our_atty_zipcode);//our_atty_zipcode text
			    	 stmt5.setString(19, ld.our_atty_phone);//our_atty_phone text
			    	 stmt5.setString(20, ld.our_atty_fax);//our_atty_fax text
			    	 stmt5.setString(21, ld.our_atty_email);//our_atty_email text
			    	 stmt5.setString(22, ld.court_name);//court_name text
			    	 stmt5.setString(23, ld.county);//county text
			    	 stmt5.setString(24, ld.court_address);//court_address text
			    	 stmt5.setString(25, ld.court_address2);//court_address2 text
			    	 stmt5.setString(26, ld.court_city);//court_city text
			    	 stmt5.setString(27, ld.court_state);//court_state text
			    	 stmt5.setString(28, ld.court_zip);//court_zip text
			    	 stmt5.setString(29, ld.court_phone);//court_phone text
			    	 stmt5.setString(30, ld.court_fax);//court_fax text
			    	 stmt5.setString(31, ld.clerk_name);//clerk_name text
			    	 stmt5.setString(32, ld.date_filed);//date_filed date
			    	 stmt5.setString(33, ld.date_entered);//date_entered date
			    	 stmt5.setString(34, ld.date_updated);//date_updated date
			    	 stmt5.setString(35, ld.settlement_amount);//settlement_amount text
			    	 stmt5.setString(36, ld.status);//status character varying
			    	 stmt5.setString(37, ld.resolution_date);//resolution_date date
			    	 stmt5.setString(38, ld.resolution_text);//resolution_text text
			    	 stmt5.setBoolean(39, ld.tcpa);//tcpa boolean
			    	 stmt5.setBoolean(40, ld.fdcpa);//fdcpa boolean
			    	 stmt5.setBoolean(41, ld.fcra);//fcra boolean
			    	 stmt5.setBoolean(42, ld.fcba);//fcba boolean
			    	 stmt5.setBoolean(43, ld.other);//other boolean
			    	 stmt5.setString(44, ld.other_text);//other_text text
			    	 stmt5.setString(45, ld.court_date);//court_date date
			    	 stmt5.setString(46, ld.court_date_text);//court_date_text text
			    	 stmt5.setString(47, ld.assigned_to);//assigned_to text
			    	 stmt5.setLong(48, new Long(ld.complaint_id));//complaint_id bigint
			    	 stmt5.setLong(49, new Long(ld.client_id));//client_id bigint

			    	 stmt5.execute();
			    }
			   
			   
			} catch (Exception e){
				System.out.println(e.toString());
			}
		return(rtn);
	}
	public BBBComplaintData[] get_bbb(Long comp_id, Long client_id, String user){
		Vector<BBBComplaintData> vbbb = new Vector<BBBComplaintData>();
		BBBComplaintData ld = new BBBComplaintData();
		try {
			
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_get_bbb(?, ?, ?)}");
		    stmt5.setLong(1, comp_id);
		    stmt5.setLong(2, client_id);
		    stmt5.setString(3, user);
		    ResultSet rs = stmt5.executeQuery();
		    
		    while (rs.next()){
		    	ld.id = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_complaint_number = ("" + rs.getString(2)).replaceAll("null", "");
		    	ld.bbb_complain_date = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_email = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_name = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_contact_name = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_address1 = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_address2 = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_city = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_state = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_zipcode = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_phone = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_phone_ext = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_fax = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_url = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_complaint_summary = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_response_text = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.bbb_response_date = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.client_id = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.status = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.response_by = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.entered_by = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.complaint_id = ("" + rs.getString(1)).replaceAll("null", "");
		    	vbbb.add(ld);
		    }
		 		} catch (Exception e){
		 			System.out.println(e.toString());
		 		}
		 		return (BBBComplaintData[]) (vbbb.toArray());
		 	}
	public LawsuitData get_lawsuit(Long lawsuit_id, Long client_id, UserData ud){
		LawsuitData ld = new LawsuitData();
		try {
			
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_get_lawsuit(?, ?, ?)}");
		    stmt5.setLong(1, lawsuit_id);
		    stmt5.setLong(2, client_id);
		    stmt5.setString(3, ud.user_name);
		    ResultSet rs = stmt5.executeQuery();
		    
		    while (rs.next()){
		    	/*
		    	id, case_num, atty_name, atty_firm, atty_add1, atty_add2, atty_city, 
	            atty_state, atty_zipcode, atty_phone, atty_fax, atty_email, our_atty_name, 
	            our_atty_firm, our_atty_add1, our_atty_add2, our_atty_city, our_atty_state, 
	            our_atty_zipcode, our_atty_phone, our_atty_fax, our_atty_email, 
	            court_name, county, court_address, court_address2, court_city, 
	            court_state, court_zip, court_phone, court_fax, clerk_name, date_filed, 
	            date_entered, date_updated, settlement_amount, status, resolution_date, 
	            resolution_text, tcpa, fdcpa, fcra, fcba, other, other_text, 
	            court_date, court_date_text, assigned_to, complaint_id, client_id */
		    	ld.id = ("" + rs.getString(1)).replaceAll("null", "");
		    	ld.case_num = ("" + rs.getString(2)).replaceAll("null", "");
		    	ld.atty_name = ("" + rs.getString(3)).replaceAll("null", "");
		    	ld.atty_firm = ("" + rs.getString(4)).replaceAll("null", "");
		    	ld.atty_add1 = ("" + rs.getString(5)).replaceAll("null", "");
		    	ld.atty_add2 = ("" + rs.getString(6)).replaceAll("null", "");
		    	ld.atty_city = ("" + rs.getString(7)).replaceAll("null", "");
		    	ld.atty_state = ("" + rs.getString(8)).replaceAll("null", "");
		    	ld.atty_zipcode = ("" + rs.getString(9)).replaceAll("null", "");
		    	ld.atty_phone = ("" + rs.getString(10)).replaceAll("null", "");
		    	ld.atty_fax = ("" + rs.getString(11)).replaceAll("null", "");
		    	ld.atty_email = ("" + rs.getString(12)).replaceAll("null", "");
		    	ld.our_atty_name = ("" + rs.getString(13)).replaceAll("null", "");
		    	ld.our_atty_firm = ("" + rs.getString(14)).replaceAll("null", "");
		    	ld.our_atty_add1 = ("" + rs.getString(15)).replaceAll("null", "");
		    	ld.our_atty_add2 = ("" + rs.getString(16)).replaceAll("null", "");
		    	ld.our_atty_city = ("" + rs.getString(17)).replaceAll("null", "");
		    	ld.our_atty_state = ("" + rs.getString(18)).replaceAll("null", "");
		    	ld.our_atty_zipcode = ("" + rs.getString(19)).replaceAll("null", "");
		    	ld.our_atty_phone = ("" + rs.getString(20)).replaceAll("null", "");
		    	ld.our_atty_fax = ("" + rs.getString(21)).replaceAll("null", "");
		    	ld.our_atty_email = ("" + rs.getString(22)).replaceAll("null", "");
		    	ld.court_name = ("" + rs.getString(23)).replaceAll("null", "");
		    	ld.county = ("" + rs.getString(24)).replaceAll("null", "");
		    	ld.court_address = ("" + rs.getString(25)).replaceAll("null", "");
		    	ld.court_address2 = ("" + rs.getString(26)).replaceAll("null", "");
		    	ld.court_city = ("" + rs.getString(27)).replaceAll("null", "");
		    	ld.court_state = ("" + rs.getString(28)).replaceAll("null", "");
		    	ld.court_zip = ("" + rs.getString(29)).replaceAll("null", "");
		    	ld.court_phone = ("" + rs.getString(30)).replaceAll("null", "");
		    	ld.court_fax = ("" + rs.getString(31)).replaceAll("null", "");
		    	ld.clerk_name = ("" + rs.getString(32)).replaceAll("null", "");
		    	ld.date_filed = ("" + rs.getString(33)).replaceAll("null", "");
		    	ld.date_entered = ("" + rs.getString(34)).replaceAll("null", "");
		    	ld.date_updated = ("" + rs.getString(35)).replaceAll("null", "");
		    	ld.settlement_amount = ("" + rs.getString(36)).replaceAll("null", "");
		    	ld.status = ("" + rs.getString(37)).replaceAll("null", "");
		    	ld.resolution_date = ("" + rs.getString(38)).replaceAll("null", "");
		    	ld.resolution_text = ("" + rs.getString(39)).replaceAll("null", "");
		    	ld.tcpa = rs.getBoolean(40);
		    	ld.fdcpa = rs.getBoolean(41);
		    	ld.fcra = rs.getBoolean(42);
		    	ld.fcba = rs.getBoolean(43);
		    	ld.other = rs.getBoolean(44);
		    	ld.other_text = ("" + rs.getString(45)).replaceAll("null", "");
		    	ld.court_date_text = ("" + rs.getString(46)).replaceAll("null", "");
		    	ld.court_date_text = ("" + rs.getString(47)).replaceAll("null", "");
		    	ld.assigned_to = ("" + rs.getString(48)).replaceAll("null", "");
		    	ld.complaint_id = ("" + rs.getString(49)).replaceAll("null", "");
		    	ld.client_id = ("" + rs.getString(50)).replaceAll("null", "");
		    }
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return(ld);
	}
	public TaskData[] get_tasks_list(Long complaint_id, Long client_id){
		//String rtn = "error: failed to add new complaint";
		TaskData td[] = new TaskData[1];
		td[0] = new TaskData();
		try {
			
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_get_tasks_list(?, ?)}");
		    stmt5.setLong(1, complaint_id);
		    stmt5.setLong(2, client_id);
		    //stmt5.setLong(2, td.created_by);
		    //stmt5.setDate(3, new java.sql.Date(new Date().getTime()));
			ResultSet rs = stmt5.executeQuery();
			Vector<TaskData> vtd = new Vector<TaskData>();
			while (rs.next()){
				TaskData td1 = new TaskData();
				/*
				 * CREATE TYPE task_list_data AS
				   (id bigint,
				    task_desc text,
				    created_by bigint,
				    date_created date,
				    date_due character varying(50),
				    task_closed_date character varying(50),
				    group_id bigint,
				    assigned_to bigint,
				    task_response text);
				 */
				td1.id = rs.getString(1);
				td1.task_desc = rs.getString(2);
				td1.created_by = rs.getString(3);
				td1.date_created = rs.getString(4).substring(0,10);
				td1.date_due = rs.getString(5);
				td1.task_closed_date = ("" + rs.getString(6)).replaceAll("null", "");
				td1.group_id = rs.getString(7);
				td1.assigned_to = rs.getString(8);
				td1.task_response = ("" + rs.getString(9)).replaceAll("null", "");
				vtd.add(td1);
			}
			td = new TaskData[vtd.size()];
			for (int i=0; i<vtd.size(); i++){
				td[i] = vtd.get(i);
			}
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(td);
	}
	public String new_task(TaskData td){
		String rtn = "error: failed to add new complaint";
		try {
			
			check_con();
			CallableStatement stmt5;
		   
		    stmt5 = con.prepareCall("{call dcs_add_task(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		    stmt5.setString(1, td.task_desc);
		    stmt5.setString(2, td.created_by);
		    //stmt5.setDate(3, new java.sql.Date(new Date().getTime()));
		    stmt5.setString(3, td.date_due);
		    stmt5.setString(4, td.group_id);
		    stmt5.setString(5, td.assigned_to);
		    stmt5.setString(6, td.task_response);
		    stmt5.setString(7, td.task_closed_date);
		    stmt5.setString(8, td.task_closed_by);
		    stmt5.setString(9, td.client_id);
		    stmt5.setString(10, td.complaint_id);
			ResultSet rs = stmt5.executeQuery();
			while (rs.next()){
				rtn = rs.getString(1);
			}
		    /*
		      task_desc text,
  created_by integer,
  date_created date,
  date_due date,
  group_id bigint,
  assigned_to bigint,
  task_response text,
  task_closed_date date,
  task_closed_by bigint,
  client_id bigint,
  complaint_id bigint,
		     */
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String add_status_code(StatusCodes sc, Long cid){
		String rtn = "error: failed to add new status code";
		try {
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_add_status_code(?, ?)}");
		    stmt5.setLong(1,cid);
		    stmt5.setString(2, sc.getName());
		    stmt5.execute();
		    
		    rtn = "";
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String add_sub_status_code(StatusCodes sc, Long cid){
		String rtn = "error: failed to add new status code";
		try {
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_add_sub_status_code(?, ?)}");
		    stmt5.setLong(1,cid);
		    stmt5.setString(2, sc.getName());
		    stmt5.execute();
		    
		    rtn = "";
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String add_type_code(StatusCodes sc, Long cid){
		String rtn = "error: failed to add new status code";
		try {
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_add_type_code(?, ?)}");
		    stmt5.setLong(1,cid);
		    stmt5.setString(2, sc.getName());
		    stmt5.execute();
		    
		    rtn = "";
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String add_sub_type_code(StatusCodes sc, Long cid){
		String rtn = "error: failed to add new status code";
		try {
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_add_sub_type_code(?, ?)}");
		    stmt5.setLong(1,cid);
		    stmt5.setString(2, sc.getName());
		    stmt5.execute();
		    
		    rtn = "";
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String add_customer_code(StatusCodes sc, Long cid){
		String rtn = "error: failed to add new status code";
		try {
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_add_customer_code(?, ?)}");
		    stmt5.setLong(1,cid);
		    stmt5.setString(2, sc.getName());
		    stmt5.execute();
		    
		    rtn = "";
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(rtn);
	}
	public String edit_complaint(ComplaintData cd){
		System.out.println("In Edit Complaint");
		String rtn = "error: failed to add new complaint";
		System.out.println(cd.complaint_date);
		System.out.println(("" + cd.resolution_date).replaceAll("null", "").trim());
		System.out.println("complaint id: " + cd.id);
		try {
			Encryptor en = new Encryptor(DB_Conn.getConnKeyDB(), cd.client_id);
			check_con();
			//CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
			

		   
		    
			
		    if (cd.complaint_date.length() > 10){
		    	cd.complaint_date = cd.complaint_date.substring(0,10);
		    }
		    if (cd.resolution_date.length() > 10){
		    	cd.resolution_date = cd.resolution_date.substring(0,10);
		    }
		    if (cd.resolved_by.length() == 0){
		    	cd.resolved_by = "0";
		    }
		    if (cd.id.length() == 0 || cd.id.equals("0")){
		    	System.out.println("Calling new Complaint");

		    	
		    	new_complaint(cd, cd.user_name);
		    	
		    } else {
		    	try {
		    	System.out.println("Calling dcs_edit_complaint");
				System.out.println(cd.complaint_date);
				System.out.println(("" + cd.resolution_date).replaceAll("null", "").trim());
				CallableStatement stmt5 = con.prepareCall("{call dcs_edit_complaint(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			    stmt5.setString(1, cd.our_account);
			    stmt5.setString(2, cd.customer_account);
			    stmt5.setString(3, (cd.customer));
			    stmt5.setString(4, cd.complaint_date);
			    stmt5.setString(5, cd.complaint_text);
			    stmt5.setString(6, cd.status);
			    stmt5.setString(7, cd.sub_status);
			    stmt5.setString(8, ("" + cd.resolution_date).replaceAll("null", "").trim());
			    stmt5.setString(9, cd.resolution_text);
			    stmt5.setString(10, cd.name);
			    stmt5.setString(11, cd.phone);
			    stmt5.setString(12, cd.ssn);
			    stmt5.setLong(13, new Long(cd.client_id));
			    stmt5.setString(14, cd.type);
			    stmt5.setString(15, cd.sub_type);
			    stmt5.setLong(16, new Long(cd.entered_by));
			    stmt5.setLong(17, new Long(cd.resolved_by));
			    stmt5.setString(18, en.getKey(new Integer(cd.id)));
				stmt5.setLong(19, new Long(cd.id) * 55 * 33);
				stmt5.setLong(20, new Long(cd.id));
				stmt5.setString(21, cd.user_name);
				stmt5.setString(22, cd.address);
				
				stmt5.setString(23,cd.city);
				stmt5.setString(24,cd.state);
				stmt5.setString(25,cd.zip);
				stmt5.setString(26,cd.phone2);
						
				stmt5.execute();
		    	 } catch (Exception e){
				    	System.out.println("Failed to Edit Complaint: " + cd.id + " error:" + e.toString());
		    	 }
		    }
		} catch (Exception e){
			System.out.println("edit_complaint: " +e.toString());
		}
		
		return(rtn);
	}
	public ComplaintData get_complaint(String cid, String client_id, String user){
		ComplaintData cd = new ComplaintData();
		cd = getCodes(client_id);
		System.out.println("In get_complaint");
		try {
			Encryptor en = new Encryptor(DB_Conn.getConnKeyDB(), client_id);
			check_con();
			CallableStatement stmt5;
		    
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    
		    stmt5 = con.prepareCall("{call dcs_get_complaint_key(?, ?)}");
			stmt5.setLong(1, new Long(cid));
			stmt5.setLong(2, new Long(client_id));
		    ResultSet rs = stmt5.executeQuery();
			long keyval = 0;
			while (rs.next()){
				keyval = rs.getLong(1) / 55 / 33;
			}
			String dkey = en.getKey(new Integer(new Long(keyval).toString()));
			stmt5 = con.prepareCall("{call dcs_get_complaint(?, ?, ?)}");
			stmt5.setLong(1, new Long(cid));
			stmt5.setLong(2, new Long(client_id));
			stmt5.setString(3, dkey);
		    rs = stmt5.executeQuery();
			
			while (rs.next()){
				
				cd.id = rs.getString(1);//SELECT id, 
				cd.our_account = rs.getString(2);//our_account,  
				cd.customer_account = rs.getString(3);//our_account,    
				cd.customer = rs.getString(4);//customer,  
				cd.complaint_date = rs.getString(5);//complaint_date, 
				
				cd.complaint_text = rs.getString(6);//complaint_text,  
				cd.status = rs.getString(7);//status,  
				cd.sub_status = rs.getString(8);//sub_status,  
				cd.resolution_date = ("" + rs.getString(9)).replaceAll("null", "");//resolution_date,

				cd.resolution_text = rs.getString(10);//resolution_text,  
				cd.name = rs.getString(11);//name,  
				cd.phone = rs.getString(12);//phone,  
				//cd.attachement_id = rs.getString(13);//attachement_id,  
				//corrective_action_id,  
				cd.lawsuit_id = rs.getString("lawsuit_id");
				 
				cd.ag_id = rs.getString("ag_id");
				cd.bbb_id = rs.getString("bbb_id");
				cd.client_id = rs.getString(18);//client_id,  
				cd.audit_id = rs.getString(19);
				cd.type = rs.getString(20);//type,  
				cd.sub_type=rs.getString(21);//sub_type,  
				cd.entered_by=rs.getString(22);//entere_by, 
				 
				cd.resolved_by=rs.getString(23);//resolved_by,  
				cd.keyval=rs.getString(24);//keyval,  
				cd.customer_account=rs.getString(25);//pgp_sym_decrypt_bytea(customer_account_e, $3) as customer_account_e,  
				//ssn_4,  
				cd.ssn = rs.getString(27);//pgp_sym_decrypt_bytea(ssn, $3) as ssn
				
				cd.address = rs.getString(28);
				cd.city = rs.getString(29);
				cd.state = rs.getString(30);
				cd.zip = rs.getString(31);
				cd.phone2 = rs.getString(32);
				
				
				ComplaintData cd1 = getCodes(client_id);
				cd.status_codes = cd1.status_codes;
				cd.customers = cd1.customers;
				cd.sub_status_codes = cd1.sub_status_codes;
				cd.type_codes = cd1.type_codes;
				cd.sub_type_codes = cd1.sub_type_codes;
			    
			    
			    //cd.customer = cd1.customer;
			    //Fix Date format for json pass back
				if (("" + cd.resolution_date).length() > 10){
					
					cd.resolution_date = cd.resolution_date.substring(0,10);
				}
				if (("" + cd.complaint_date).length() > 10){
					cd.complaint_date = cd.complaint_date.substring(0,10);
				}
			}

		} catch (Exception e){
			System.out.println("get_complaint: " + e.toString());
		}
		try {
		TaskData[] td = get_tasks_list(new Long(cid), new Long(client_id));
		Vector<TaskData> vtd = new Vector<TaskData>();
		for (int i=0; i<td.length; i++){
			vtd.add(td[i]);
		}
		cd.tasks = vtd;
		} catch (Exception e){
			System.out.println("get_complaint get tasks: " + e.toString());
		}
		try {
			BBBComplaintData[] td = get_bbb(new Long(cid), new Long(client_id), user);
			Vector<BBBComplaintData> vtd = new Vector<BBBComplaintData>();
			for (int i=0; i<td.length; i++){
				vtd.add(td[i]);
			}
			cd.bbb = vtd;
			} catch (Exception e){
				System.out.println("get_complaint get bbb: " + e.toString());
			}
		return(cd);
	}
	public void addBBB(String complaint_id, String client_id){
		/*
		dcs_add_bbb_complaint(bbb_complaint_number character varying, 
		bbb_complain_date text, 
		bbb_email text, 
		bbb_name text, 
		bbb_contact_name text, 
		bbb_address1 text, 
		bbb_address2 text, 
		bbb_city text, 
		bbb_state text, 
		bbb_zipcode text, 
		bbb_phone text, 
		bbb_phone_ext text, 
		bbb_fax text, 
		bbb_url text, 
		bbb_complaint_summary text, 
		bbb_response_text text, 
		bbb_response_date text, 
		client_id bigint, 
		status character varying, 
		response_by bigint, 
		entered_by bigint, 
		complaint_id bigint)
		*/
		try {
			if (complaint_id.equals("0")){

			} else {
				CallableStatement stmt6 = con.prepareCall("{call dcs_add_bbb_complaint(?)}");
				stmt6.setString(1, "");
				
				//bbb_complain_date text, 
				stmt6.setString(2, "");
				//bbb_email text, 
				stmt6.setString(3, "");
				//bbb_name text, 
				stmt6.setString(4, "");
				//bbb_contact_name text, 
				stmt6.setString(5, "");
				//bbb_address1 text, 
				stmt6.setString(6, "");
				//bbb_address2 text,
				stmt6.setString(7, "");
				//bbb_city text, 
				stmt6.setString(8, "");
				//bbb_state text,
				stmt6.setString(9, "");
				//bbb_zipcode text,
				stmt6.setString(10, "");
				//bbb_phone text, 
				stmt6.setString(11, "");
				//bbb_phone_ext text,
				stmt6.setString(12, "");
				//bbb_fax text, 
				stmt6.setString(13, "");
				//bbb_url text,
				stmt6.setString(14, "");
				//bbb_complaint_summary text,
				stmt6.setString(15, "");
				//bbb_response_text text, 
				stmt6.setString(16, "");
				//bbb_response_date text, 
				stmt6.setString(17, "");
				//client_id bigint, 
				stmt6.setLong(18, new Long(client_id));
				//status character varying, 
				stmt6.setString(19, "OPEN");
				//response_by bigint, 
				stmt6.setString(20, "");
				//entered_by bigint, 
				stmt6.setString(21, "");
				//complaint_id bigint
				stmt6.setLong(22, new Long(complaint_id));
			}
		} catch (Exception e){
			System.out.println("add new bbb complaint: " + e.toString());
		}
	}
	public ComplaintData getCodes(String cid){
		ComplaintData cd = new ComplaintData();
		try {
			//Get Customers List
			
			CallableStatement stmt6 = con.prepareCall("{call dcs_get_customers(?)}");
			stmt6.setLong(1, new Long(cid));
			
		    ResultSet rst = stmt6.executeQuery();
		   
		    while (rst.next()){
		    	if (cd.customer.equals(rst.getString(1))){
		    		cd.customer = rst.getString(3);
		    	}
		    	cd.addCustomers(rst.getString(3));
		    }
		    
			//Get Status Codes List
		    stmt6 = con.prepareCall("{call dcs_get_status_codes(?)}");
			stmt6.setLong(1, new Long(cid));
			
		    rst = stmt6.executeQuery();
		    
		    while (rst.next()){
		    	if (cd.status.equals(rst.getString(1))){
		    		cd.status = rst.getString(3);
		    	}
		    	cd.addStatusCode(rst.getString(3));
		    }
			//Get Sub Status Codes List
		    stmt6 = con.prepareCall("{call dcs_get_sub_status_codes(?)}");
			stmt6.setLong(1, new Long(cid));
			
		    rst = stmt6.executeQuery();
		    
		    while (rst.next()){
		    	if (cd.sub_status.equals(rst.getString(1))){
		    		cd.sub_status = rst.getString(3);
		    	}
		    	cd.addSubStatusCode(rst.getString(3));
		    }
			//Get Type Codes List
		    stmt6 = con.prepareCall("{call dcs_get_type_codes(?)}");
			stmt6.setLong(1, new Long(cid));
			
		    rst = stmt6.executeQuery();
		    
		    while (rst.next()){
		    	if (cd.type.equals(rst.getString(1))){
		    		cd.type = rst.getString(3);
		    	}
		    	cd.addTypeCode(rst.getString(3));
		    }
			//Get Sub Type Codes List
		    stmt6 = con.prepareCall("{call dcs_get_sub_type_codes(?)}");
			stmt6.setLong(1, new Long(cid));
			
		    rst = stmt6.executeQuery();
		    
		    while (rst.next()){
		    	if (cd.sub_type.equals(rst.getString(1))){
		    		cd.sub_type = rst.getString(3);
		    	}
		    	cd.addSubTypeCode(rst.getString(3));
		    }
		
	} catch (Exception e){
		System.out.println("get_codes: " + e.toString());
	}
		return(cd);
	}
	public ComplaintData[] get_complaint_byuser(String uid, String cid){
		ComplaintData cd[] = new ComplaintData[1];
		cd[0] = new ComplaintData();
		try {
			
			check_con();
			//PreparedStatement stmt5;
		    CallableStatement stmt5;
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    

			
			stmt5 = con.prepareCall("{call dcs_get_complaint_byuser(?,?)}");
			stmt5.setLong(1, new Long(cid));
			stmt5.setLong(2, new Long(uid));
			
			System.out.println("Calling call dcs_get_complaint_byuser " + cid + "," + uid);
		    ResultSet rs = stmt5.executeQuery();
			
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * FROM \"dcs_get_complaint_byuser\"(" + cid + "," + uid + ")");
		    Vector<ComplaintData> vcd = new Vector<ComplaintData>();
			while (rs.next()){
				System.out.println("In RS");
				ComplaintData cd1 = new ComplaintData();
				cd1.id = rs.getString(1);//SELECT id, 
				cd1.our_account = rs.getString(2);//our_account,  
				//customer_account,  
				cd1.customer = rs.getString(4);//customer,  
				cd1.complaint_date = rs.getString(5);//complaint_date, 
				cd1.complaint_text = rs.getString(6);//complaint_text,  
				cd1.status = rs.getString(7);//status,  
				cd1.sub_status = rs.getString(8);//sub_status,  
				cd1.resolution_date = rs.getString(9);//resolution_date,  
				cd1.resolution_text = rs.getString(10);//resolution_text,  
				cd1.name = rs.getString(11);//name,  
				cd1.phone = rs.getString(12);//phone,  
				//cd.attachement_id = rs.getString(13);//attachement_id,  
				//corrective_action_id,  
				//lawsuit_id, 
				 
				//ag_id,  
				//bbb_id,  
				cd1.client_id = rs.getString(18);//client_id,  
				//audit_id,  
				cd1.type = rs.getString(20);//type,  
				cd1.sub_type=rs.getString(21);//sub_type,  
				cd1.entered_by=rs.getString(22);//entere_by, 
				 
				cd1.resolved_by=rs.getString(23);//resolved_by,  
				cd1.keyval=rs.getString(24);//keyval,  
				cd1.customer_account=rs.getString(3);//pgp_sym_decrypt_bytea(customer_account_e, $3) as customer_account_e,  
				//ssn_4,  
				cd1.ssn = rs.getString(26);//pgp_sym_decrypt_bytea(ssn, $3) as ssn
				
				if (("" + cd1.resolution_date).length() > 10){
					cd1.resolution_date = cd1.resolution_date.substring(0,10);
				}
				if (("" + cd1.complaint_date).length() > 10){
					cd1.complaint_date = cd1.complaint_date.substring(0,10);
				}
				vcd.add(cd1);
			}
			//vcd.toArray(cd);
			cd = new ComplaintData[vcd.size()];
			for (int i=0; i<vcd.size(); i++){
				cd[i] = vcd.get(i);
				System.out.println(i);
			}
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(cd);
	}
	public ComplaintData[] get_complaint_closed(String cid){
		ComplaintData cd[] = new ComplaintData[1];
		cd[0] = new ComplaintData();
		try {
			
			check_con();
			//PreparedStatement stmt5;
		    CallableStatement stmt5;
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    

			
			stmt5 = con.prepareCall("{call dcs_get_complaint_closed(?)}");
			stmt5.setLong(1, new Long(cid));
			//stmt5.setLong(2, new Long(uid));
			
			//System.out.println("Calling call dcs_get_complaint_byuser " + cid + "," + uid);
		    ResultSet rs = stmt5.executeQuery();
			
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * FROM \"dcs_get_complaint_byuser\"(" + cid + "," + uid + ")");
		    Vector<ComplaintData> vcd = new Vector<ComplaintData>();
			while (rs.next()){
				System.out.println("In RS");
				ComplaintData cd1 = new ComplaintData();
				cd1.id = rs.getString(1);//SELECT id, 
				cd1.our_account = rs.getString(2);//our_account,  
				//customer_account,  
				cd1.customer = rs.getString(4);//customer,  
				cd1.complaint_date = rs.getString(5);//complaint_date, 
				cd1.complaint_text = rs.getString(6);//complaint_text,  
				cd1.status = rs.getString(7);//status,  
				cd1.sub_status = rs.getString(8);//sub_status,  
				cd1.resolution_date = rs.getString(9);//resolution_date,  
				cd1.resolution_text = rs.getString(10);//resolution_text,  
				cd1.name = rs.getString(11);//name,  
				cd1.phone = rs.getString(12);//phone,  
				//cd.attachement_id = rs.getString(13);//attachement_id,  
				//corrective_action_id,  
				//lawsuit_id, 
				 
				//ag_id,  
				//bbb_id,  
				cd1.client_id = rs.getString(18);//client_id,  
				//audit_id,  
				cd1.type = rs.getString(20);//type,  
				cd1.sub_type=rs.getString(21);//sub_type,  
				cd1.entered_by=rs.getString(22);//entere_by, 
				 
				cd1.resolved_by=rs.getString(23);//resolved_by,  
				cd1.keyval=rs.getString(24);//keyval,  
				cd1.customer_account=rs.getString(3);//pgp_sym_decrypt_bytea(customer_account_e, $3) as customer_account_e,  
				//ssn_4,  
				cd1.ssn = rs.getString(26);//pgp_sym_decrypt_bytea(ssn, $3) as ssn
				
				if (("" + cd1.resolution_date).length() > 10){
					cd1.resolution_date = cd1.resolution_date.substring(0,10);
				}
				if (("" + cd1.complaint_date).length() > 10){
					cd1.complaint_date = cd1.complaint_date.substring(0,10);
				}
				vcd.add(cd1);
			}
			//vcd.toArray(cd);
			cd = new ComplaintData[vcd.size()];
			for (int i=0; i<vcd.size(); i++){
				cd[i] = vcd.get(i);
				System.out.println(i);
			}
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return(cd);
	}
	public ComplaintData[] get_complaint_open(String cid){
		ComplaintData cd[] = new ComplaintData[1];
		cd[0] = new ComplaintData();
		try {
			
			check_con();
			//PreparedStatement stmt5;
		    CallableStatement stmt5;
			//stmt5.setString(5, en.getKey(kv));
			//stmt5.setLong(6, kv * 55 * 33);
		    
		    

			
			stmt5 = con.prepareCall("{call dcs_get_complaint_open(?)}");
			stmt5.setLong(1, new Long(cid));
			//stmt5.setLong(2, new Long(uid));
			
			//System.out.println("Calling call dcs_get_complaint_byuser " + cid + "," + uid);
		    ResultSet rs = stmt5.executeQuery();
			
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * FROM \"dcs_get_complaint_byuser\"(" + cid + "," + uid + ")");
		    Vector<ComplaintData> vcd = new Vector<ComplaintData>();
			while (rs.next()){
				System.out.println("In RS");
				ComplaintData cd1 = new ComplaintData();
				cd1.id = rs.getString(1);//SELECT id, 
				cd1.our_account = rs.getString(2);//our_account,  
				//cd1.customer_account = rs.getString(3);//our_account,   
				cd1.customer = rs.getString(4);//customer,  
				cd1.complaint_date = rs.getString(5);//complaint_date, 
				cd1.complaint_text = rs.getString(6);//complaint_text,  
				cd1.status = rs.getString(7);//status,  
				cd1.sub_status = rs.getString(8);//sub_status,  
				cd1.resolution_date = rs.getString(9);//resolution_date,  
				cd1.resolution_text = rs.getString(10);//resolution_text,  
				cd1.name = rs.getString(11);//name,  
				cd1.phone = rs.getString(12);//phone,  
				//cd.attachement_id = rs.getString(13);//attachement_id,  
				//corrective_action_id,  
				//lawsuit_id, 
				 
				//ag_id,  
				//bbb_id,  
				cd1.client_id = rs.getString(18);//client_id,  
				//audit_id,  
				cd1.type = rs.getString(20);//type,  
				cd1.sub_type=rs.getString(21);//sub_type,  
				cd1.entered_by=rs.getString(22);//entere_by, 
				 
				cd1.resolved_by=rs.getString(23);//resolved_by,  
				cd1.keyval=rs.getString(24);//keyval,  
				cd1.customer_account=rs.getString(3);//pgp_sym_decrypt_bytea(customer_account_e, $3) as customer_account_e,  
				//ssn_4,  
				cd1.ssn = rs.getString(26);//pgp_sym_decrypt_bytea(ssn, $3) as ssn
				
				if (("" + cd1.resolution_date).length() > 10){
					cd1.resolution_date = cd1.resolution_date.substring(0,10);
				}
				if (("" + cd1.complaint_date).length() > 10){
					cd1.complaint_date = cd1.complaint_date.substring(0,10);
				}
				vcd.add(cd1);
			}
			//vcd.toArray(cd);
			cd = new ComplaintData[vcd.size()];
			for (int i=0; i<vcd.size(); i++){
				cd[i] = vcd.get(i);
				System.out.println(i);
			}
		} catch (Exception e){
			System.out.println("get_complaint_open: " + e.toString());
		}
		
		return(cd);
	}
	public void check_con() throws SQLException{
		if (con.isClosed()){
			con = DB_Conn.getConn();
		}
	}
}
