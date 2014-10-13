package com.kelmtech.dcs.client.datastructures;

import java.util.Vector;



public class ComplaintData{
	  public String id= "";
	  public String our_account= "";
	  public String customer_account= "";
	  public String customer= "";
	  public String complaint_date= "";
	  public String complaint_text= "";
	  public String status= "";
	  public String sub_status= "";
	  public String resolution_date= "";
	  public String resolution_text= "";
	  public String name= "";
	  public String phone= "";
	  public String ssn= "";
	  public String attachement_id= "";
	  public String corrective_action_id= "";
	  public String lawsuit_id= "";
	  public String ag_id= "";
	  public String bbb_id= "";
	  public String client_id= "";
	  public String audit_id= "";
	  public String type= "";
	  public String sub_type= "";
	  public String entered_by= "";
	  public String resolved_by= "";
	  public String entered_by_str= "";
	  public String resolved_by_str= "";
	  public String keyval = "";
	  public String user_name = "";
	  public String address = "";
	  public String city = "";
	  public String state = "";
	  public String zip = "";
	  public String phone2 = "";
	  public boolean hidebbb = true;
	  public boolean hideag = true;
	  public boolean hidelawsuit = true;
	  public Vector<StatusCodes> status_codes = new Vector<StatusCodes>();
	  public Vector<StatusCodes> sub_status_codes = new Vector<StatusCodes>();
	  public Vector<StatusCodes> type_codes = new Vector<StatusCodes>();
	  public Vector<StatusCodes> sub_type_codes = new Vector<StatusCodes>();
	  public Vector<StatusCodes> customers = new Vector<StatusCodes>();
	  public Vector<TaskData> tasks = new Vector<TaskData>();
	  public Vector<BBBComplaintData> bbb = new Vector<BBBComplaintData>();
	  public Vector<AGComplaintData> ag = new Vector<AGComplaintData>();
	  public Vector<LawsuitData> lawsuit = new Vector<LawsuitData>();
	  
	public ComplaintData(){
		
	}
	public void addStatusCode(String status){
		StatusCodes s = new StatusCodes();
		s.name = status;
		status_codes.add(s);
	}
	public void addSubStatusCode(String status){
		StatusCodes s = new StatusCodes();
		s.name = status;
		sub_status_codes.add(s);
	}
	public void addTypeCode(String status){
		StatusCodes s = new StatusCodes();
		s.name = status;
		type_codes.add(s);
	}
	public void addSubTypeCode(String status){
		StatusCodes s = new StatusCodes();
		s.name = status;
		sub_type_codes.add(s);
	}
	public void addCustomers(String status){
		StatusCodes s = new StatusCodes();
		s.name = status;
		customers.add(s);
	}
	 public String getId(){
		 return(this.id);
	 }
	  public String getOur_account(){
		  return(this.our_account);
	  }
	  public String getCustomer_account(){
		  return(this.customer_account);
	  }
	  
	  public String getCustomer(){
		  return(this.customer);
	  }
	  public String getComplaint_date(){
		  return(this.complaint_date);
		  
	  }
	  public String getComplaint_text(){
		  return(this.complaint_text);
	  }
	  public String getStatus(){
		  return(this.status);
	  }
	  public String getSub_status(){
		  return(this.sub_status);
	  }
	  public String getResolution_date(){
		  return(this.resolution_date);
	  }
	  public String getResolution_text(){
		  return(this.resolution_text);
	  }
	  public String getName(){
		  return(this.name);
	  }
	  public String getPhone(){
		  return(this.phone);
	  }
	  public String getSsn(){
		  return(this.ssn);
	  }
	  public String getAttachement_id(){
		  return(this.attachement_id);
	  }
	  public String getCorrective_action_id(){
		  return(this.corrective_action_id);
	  }
	  public String getLawsuit_id(){
		  return(this.lawsuit_id);
	  }
	  public String getAg_id(){
		  return(this.ag_id);
	  }
	  public String getBbb_id(){
		  return(this.bbb_id);
	  }
	  public String getClient_id(){
		  return(this.client_id);
	  }
	  public String getAudit_id(){
		  return(this.audit_id);
	  }
	  public String getType(){
		  return(this.type);
	  }
	  public String getSub_type(){
		  return(this.sub_type);
	  }
	  public String getEntered_by(){
		  return(this.entered_by);
	  }
	  public String getResolved_by(){
		  return(this.resolved_by);
	  }
	  public String getEntered_by_str(){
		  return(this.entered_by_str);
	  }
	  public String getResolved_by_str(){
		  return(this.resolved_by_str);
	  }
	  
}
