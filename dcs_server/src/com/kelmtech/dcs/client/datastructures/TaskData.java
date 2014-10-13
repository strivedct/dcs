package com.kelmtech.dcs.client.datastructures;


import java.util.Date;




public class TaskData{
	  public String id = "0";
	  public String task_desc;
	  public String created_by;
	  public String date_created;
	  public String date_due ;
	  public String group_id ;
	  public String assigned_to ;
	  public String task_response;
	  public String task_closed_date;
	  public String task_closed_by;
	  public String client_id;
	  public String created_by_value = "";
	  public String closed_by_value = "";
	  public String assigned_to_value = "";
	  public String group_value = "";
	  public String complaint_id = "0";
	public TaskData(){
		
	}
	
	
	 public String getID(){
		 return(this.id);
	 }
	
	  public String getTask_desc(){
		  return(this.task_desc);
	  }
	  
	  public String getCreated_by(){
		  return(this.created_by);
	  }
	  public String getDate_created(){
		  return(this.date_created);
	  }
	  public String getDate_due(){
		  return(this.date_due);
	  }
	  public String getGroup_id(){
		  return(this.group_id);
	  }
	  public String getAssigned_to(){
		  return (this.assigned_to);
	  }
	  public String getTask_response(){
		  return (this.task_response);
	  }
	  public String getTask_closed_date(){
		  return (this.task_closed_date);
	  }
	  public String getTask_closed_by(){
		  return (this.task_closed_by);
	  }
	  public String getClient_id(){
		  return (this.client_id);
	  }
	  public String getClosed_by_value(){
		  return (this.closed_by_value);
	  }
	  public String getAssigned_to_value(){
		  return (this.assigned_to_value);
	  }
	  public String created_by_value(){
		  return (this.created_by_value);
	  }
	  public String getGroup_value(){
		  return (this.group_value);
	  }
}
