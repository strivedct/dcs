package com.kelmtech.dcs.client.datastructures;



public class UserData{
	  public String id="";
	  public String client_id="";
	  public String user_name="";
	  public String full_name="";
	  public boolean isadmin = false;
	  public boolean view_complaints = false;
	  public boolean view_attachments = false;
	  public boolean view_ag = false;
	  public boolean view_bbb = false;
	  public boolean view_my_ca = false;
	  public boolean view_dept_ca = false;
	  public boolean view_unassigned_ca = false;
	  public boolean view_assigned_ca = false;
	  public boolean view_lawsuits = false;
	  public boolean view_my_tasks = false;
	  public boolean view_dept_tasks = false;
	  public boolean view_unassigned_tasks = false;
	  public boolean edit_complaints = false;
	  public boolean edit_attachments = false;
	  public boolean edit_ag = false;
	  public boolean edit_bbb = false;
	  public boolean edit_my_ca = false;
	  public boolean edit_dept_ca = false;
	  public boolean edit_unassigned_ca = false;
	  public boolean edit_assigned_ca = false;
	  public boolean edit_lawsuits = false;
	  public boolean edit_my_tasks = false;
	  public boolean edit_dept_tasks = false;
	  public boolean edit_unassigned_tasks = false;
	  public boolean add_complaints = false;
	  public boolean add_attachments = false;
	  public boolean add_ag = false;
	  public boolean add_bbb = false;
	  public boolean add_ca = false;
	  public boolean add_lawsuits = false;
	  public boolean add_tasks = false;

	  
	public UserData(){
		
	}
	
	
	 public String getId(){
		 return(this.id);
	 }
	
}
