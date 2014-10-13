package com.kelmtech.dcs.client.datastructures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TestData {
	public TaskData GenerateTestTask(int i){
		TaskData td = new TaskData();
		td.id = "" + i;
		td.task_desc = "Test " + i;
		td.created_by = "dkelm";
		td.date_created = "2013-01-01";
		td.date_due ="2013-01-01";
		td.group_id = "test";
		td.assigned_to = "";
		td.task_response="";
		
		
		return (td);
	}
	public AGComplaintData GenerateTestAG(int i){
		AGComplaintData td = new AGComplaintData();
		td.id = "" + i;
		td.complaint_id = "" + i;
		td.status = "Open";
		td.ag_state = "IL";
		td.complaint_date = "2013-01-0" + i;
		td.consumer_name = "Test Consumer " + i;
		td.case_num = "10-0000" + i;
		return (td);
	}
	public static UserData getTestUser(){
		UserData ud = new UserData();
		ud.id="1";
		ud.client_id="1";
		ud.user_name="dkelm";
		ud.full_name="David Kelm";
		ud.isadmin = true;
		ud.view_complaints = true;
		ud.view_attachments = true;
		ud.view_ag = true;
		ud.view_bbb = true;
		ud.view_my_ca = true;
		ud.view_dept_ca = true;
		ud.view_unassigned_ca = true;
		ud.view_assigned_ca = true;
		ud.view_lawsuits = true;
		ud.view_my_tasks = true;
		ud.view_dept_tasks = true;
		ud.view_unassigned_tasks = true;
		ud.edit_complaints = true;
		ud.edit_attachments = true;
		ud.edit_ag = true;
		ud.edit_bbb = true;
		ud.edit_my_ca = true;
		ud.edit_dept_ca = true;
		ud.edit_unassigned_ca = true;
		ud.edit_assigned_ca = true;
		ud.edit_lawsuits = true;
		ud.edit_my_tasks = true;
		ud.edit_dept_tasks = true;
		ud.edit_unassigned_tasks = true;
		ud.add_complaints = true;
		ud.add_attachments = true;
		ud.add_ag = true;
		ud.add_bbb = true;
		ud.add_ca = true;
		ud.add_lawsuits = true;
		ud.add_tasks = true;
		return(ud);
	}
	public static List<GroupsData> GenerateTestGroups(){
		List<GroupsData> ld = new ArrayList<GroupsData>();
		GroupsData cd = new GroupsData();
		cd.id = "1";
		cd.desc = "IT";
		ld.add(cd);
		cd = new GroupsData();
		cd.id = "2";
		cd.desc = "HR";
		ld.add(cd);
		cd = new GroupsData();
		cd.id = "3";
		cd.desc = "OPERATIONS";
		ld.add(cd);
		
		return(ld);
	}
	public static List<UsersData> GenerateTestUsers(){
		List<UsersData> ld = new ArrayList<UsersData>();
		UsersData cd = new UsersData();
		cd.id = "1";
		cd.username = "dkelm";
		ld.add(cd);
		cd = new UsersData();
		cd.id = "2";
		cd.username = "testuser2";
		ld.add(cd);
		cd = new UsersData();
		cd.id = "3";
		cd.username = "testuser3";
		ld.add(cd);
		
		return(ld);
	}
	public static List<ComplaintStatusData> GenerateTestStatusCodes(){
		List<ComplaintStatusData> ld = new ArrayList<ComplaintStatusData>();
		ComplaintStatusData cd = new ComplaintStatusData();
		cd.id = "1";
		cd.desc = "NEW";
		ld.add(cd);
		cd = new ComplaintStatusData();
		cd.id = "2";
		cd.desc = "OPEN";
		ld.add(cd);
		cd = new ComplaintStatusData();
		cd.id = "3";
		cd.desc = "CLOSED";
		ld.add(cd);
		
		return(ld);
	}
	public static List<AGStatusData> GenerateAGStatusCodes(){
		List<AGStatusData> ld = new ArrayList<AGStatusData>();
		AGStatusData cd = new AGStatusData();
		cd.id = "1";
		cd.desc = "NEW";
		ld.add(cd);
		cd = new AGStatusData();
		cd.id = "2";
		cd.desc = "OPEN";
		ld.add(cd);
		cd = new AGStatusData();
		cd.id = "3";
		cd.desc = "CLOSED";
		ld.add(cd);
		
		return(ld);
	}
	public static List<LawsuitStatusData> GenerateTestLawsuitCodes(){
		List<LawsuitStatusData> ld = new ArrayList<LawsuitStatusData>();
		LawsuitStatusData cd = new LawsuitStatusData();
		cd.id = "1";
		cd.desc = "NEW";
		ld.add(cd);
		cd = new LawsuitStatusData();
		cd.id = "2";
		cd.desc = "PENDING";
		ld.add(cd);
		cd = new LawsuitStatusData();
		cd.id = "3";
		cd.desc = "DISMISSED";
		ld.add(cd);
		cd = new LawsuitStatusData();
		cd.id = "4";
		cd.desc = "SETTLED";
		ld.add(cd);
		cd = new LawsuitStatusData();
		cd.id = "5";
		cd.desc = "CLOSED";
		ld.add(cd);
		return(ld);
	}
	public static List<BBBStatusData> GenerateTestBBBCodes(){
		List<BBBStatusData> ld = new ArrayList<BBBStatusData>();
		BBBStatusData cd = new BBBStatusData();
		cd.id = "1";
		cd.desc = "OPEN";
		ld.add(cd);
		cd = new BBBStatusData();
		cd.id = "2";
		cd.desc = "CLOSED";
		ld.add(cd);
		
		return(ld);
	}
	public static List<ComplaintSubStatusData> GenerateTestSubStatusCodes(){
		List<ComplaintSubStatusData> ld = new ArrayList<ComplaintSubStatusData>();
		ComplaintSubStatusData cd = new ComplaintSubStatusData();
		cd.id = "1";
		cd.desc = "NEW";
		ld.add(cd);
		cd = new ComplaintSubStatusData();
		cd.id = "2";
		cd.desc = "WAITING ON DOCS";
		ld.add(cd);
		cd = new ComplaintSubStatusData();
		cd.id = "3";
		cd.desc = "PENDING REVIEW";
		ld.add(cd);
		
		return(ld);
	}
	public static List<ComplaintTypeData> GenerateTestComplaintTypes(){
		List<ComplaintTypeData> ld = new ArrayList<ComplaintTypeData>();
		ComplaintTypeData cd = new ComplaintTypeData();
		cd.id = "1";
		cd.desc = "Attorney General";
		ld.add(cd);
		cd = new ComplaintTypeData();
		cd.id = "2";
		cd.desc = "BBB";
		ld.add(cd);
		cd = new ComplaintTypeData();
		cd.id = "3";
		cd.desc = "Lawsuit";
		ld.add(cd);
		cd = new ComplaintTypeData();
		cd.id = "4";
		cd.desc = "Verbal";
		ld.add(cd);
		cd = new ComplaintTypeData();
		cd.id = "5";
		cd.desc = "Written";
		ld.add(cd);
		return(ld);
	}
	public static List<ComplaintSubTypeData> GenerateTestComplaintSubTypes(){
		List<ComplaintSubTypeData> ld = new ArrayList<ComplaintSubTypeData>();
		ComplaintSubTypeData cd = new ComplaintSubTypeData();
		cd.id = "1";
		cd.desc = "Agent";
		ld.add(cd);
		cd = new ComplaintSubTypeData();
		cd.id = "2";
		cd.desc = "Call Attempts";
		ld.add(cd);
		cd = new ComplaintSubTypeData();
		cd.id = "3";
		cd.desc = "Bussiness Process";
		ld.add(cd);
		return(ld);
	}
	public static List<CustomerData> GenerateCustomerData(){
		List<CustomerData> ld = new ArrayList<CustomerData>();
		CustomerData cd = new CustomerData();
		cd.id = "1";
		cd.name = "Test Customer 1";
		ld.add(cd);
		cd = new CustomerData();
		cd.id = "2";
		cd.name = "Test Customer 2";
		ld.add(cd);
		cd = new CustomerData();
		cd.id = "3";
		cd.name = "Test Customer 3";
		ld.add(cd);
		return(ld);
	}
	/*
	  ColumnConfig<ComplaintData, String> phoneCol = new ColumnConfig<ComplaintData, String>(props.id(), 60, "Complaint ID");
    phoneCol.setCell(button);
    ColumnConfig<ComplaintData, String> deptCol = new ColumnConfig<ComplaintData, String>(props.customer(), 100, "Customer");
    ColumnConfig<ComplaintData, String> descCol = new ColumnConfig<ComplaintData, String>(props.complaint_text(), 100, "Description");
    ColumnConfig<ComplaintData, String> assignedCol = new ColumnConfig<ComplaintData, String>(props.our_account(), 100, "Our Acct#");
    ColumnConfig<ComplaintData, String> createdCol = new ColumnConfig<ComplaintData, String>(props.complaint_date(), 80, "Created");
    ColumnConfig<ComplaintData, String> dueCol = new ColumnConfig<ComplaintData, String>(props.status(), 40, "Status");
    ColumnConfig<ComplaintData, String> closedCol = new ColumnConfig<ComplaintData, String>(props.resolution_date(), 80, "Date Resolved");
	 */
	public ComplaintData GenerateTestComp(int i){
		ComplaintData td = new ComplaintData();
		td.id = "" + i;
		
		td.status = "Open";
		
		td.complaint_date = "2013-01-0" + i;
	
		td.customer = "Test Cust " + i;
		td.complaint_text = "Agent was rude " + i;
		td.our_account = "1000" + i;
		td.resolution_date = "";
		return (td);
	}
	public BBBComplaintData GenerateTestBBB(int i){
		BBBComplaintData td = new BBBComplaintData();
		td.complaint_id = "" + i;
		td.bbb_name = "TEST BBB " + i;
		td.bbb_complaint_summary = "Test Summary " + i;
		td.bbb_complaint_number = "10-ab" + i;
		td.bbb_complain_date = "2013-02-02";
		td.status = "Open";
		td.bbb_response_date = "";
		/*
		 ColumnConfig<BBBComplaintData, String> phoneCol = new ColumnConfig<BBBComplaintData, String>(props.complaint_id(), 60, "Complaint ID");
		    phoneCol.setCell(button);
		    ColumnConfig<BBBComplaintData, String> deptCol = new ColumnConfig<BBBComplaintData, String>(props.bbb_name(), 100, "BBB_name");
		    ColumnConfig<BBBComplaintData, String> descCol = new ColumnConfig<BBBComplaintData, String>(props.bbb_complaint_summary(), 100, "Description");
		    ColumnConfig<BBBComplaintData, String> assignedCol = new ColumnConfig<BBBComplaintData, String>(props.bbb_complaint_number(), 100, "BBB Comp#");
		    ColumnConfig<BBBComplaintData, String> createdCol = new ColumnConfig<BBBComplaintData, String>(props.bbb_complain_date(), 80, "Created");
		    ColumnConfig<BBBComplaintData, String> dueCol = new ColumnConfig<BBBComplaintData, String>(props.status(), 40, "Status");
		    ColumnConfig<BBBComplaintData, String> closedCol = new ColumnConfig<BBBComplaintData, String>(props.bbb_response_date(), 80, "Date Resolved");
		*/
		return (td);
	}
	public CorrectiveActionData GenerateTestCA(int i){
		CorrectiveActionData cad = new CorrectiveActionData();
		cad.id = "" + i;
		cad.complaint_id = "" + i;
		cad.corrective_action_text = "Smacked Collector #" + i + " Upside Head";
		cad.correct_action_assigned_to = "Test User " + i;
		cad.date_created = "2012-02-02";
		cad.corrective_action_type = "Smack Down";
		cad.corrective_action_closed_date = "";
		/*
		  ColumnConfig<CorrectiveActionData, String> phoneCol = new ColumnConfig<CorrectiveActionData, String>(props.id(), 60, "CA Id");
    phoneCol.setCell(button);
    ColumnConfig<CorrectiveActionData, String> deptCol = new ColumnConfig<CorrectiveActionData, String>(props.complaint_id(), 100, "Complaint #");
    ColumnConfig<CorrectiveActionData, String> descCol = new ColumnConfig<CorrectiveActionData, String>(props.corrective_action_text(), 100, "Description");
    ColumnConfig<CorrectiveActionData, String> assignedCol = new ColumnConfig<CorrectiveActionData, String>(props.correct_action_assigned_to(), 100, "Assigned To");
    ColumnConfig<CorrectiveActionData, String> createdCol = new ColumnConfig<CorrectiveActionData, String>(props.date_created(), 80, "Created");
    ColumnConfig<CorrectiveActionData, String> dueCol = new ColumnConfig<CorrectiveActionData, String>(props.corrective_action_type(), 80, "Type");
    ColumnConfig<CorrectiveActionData, String> closedCol = new ColumnConfig<CorrectiveActionData, String>(props.corrective_action_closed_date(), 80, "Date Closed");
     
		 */
		return(cad);
	}
	public LawsuitData GenerateTestSuit(int i){
		LawsuitData ld = new LawsuitData();
		/*ColumnConfig<LawsuitData, String> phoneCol = new ColumnConfig<LawsuitData, String>(props.id(), 60, "Suit Id");
	    phoneCol.setCell(button);
	    ColumnConfig<LawsuitData, String> deptCol = new ColumnConfig<LawsuitData, String>(props.complaint_id(), 100, "Complaint #");
	    ColumnConfig<LawsuitData, String> descCol = new ColumnConfig<LawsuitData, String>(props.case_summary(), 100, "Description");
	    ColumnConfig<LawsuitData, String> assignedCol = new ColumnConfig<LawsuitData, String>(props.assigned_to_name(), 100, "Assigned To");
	    ColumnConfig<LawsuitData, String> createdCol = new ColumnConfig<LawsuitData, String>(props.date_entered(), 80, "Created");
	    ColumnConfig<LawsuitData, String> dueCol = new ColumnConfig<LawsuitData, String>(props.status(), 80, "Status");
	    ColumnConfig<LawsuitData, String> closedCol = new ColumnConfig<LawsuitData, String>(props.date_filed(), 80, "Date Filed");
	    */
		ld.id = "" + i;
		ld.complaint_id = "" + i;
		ld.case_summary = "Test Case #" + i;
		ld.assigned_to_name = "Test User " + i;
		ld.date_entered = "2013-01-09";
		ld.date_filed = "2013-01-04";
		ld.status = "OPEN";
		return(ld);
	}
	public List<LawsuitData> getSuit(){
		List<LawsuitData> ld = new ArrayList<LawsuitData>();
		 for (int i=1; i<=10; i++){
		    	ld.add(GenerateTestSuit(i));
		    }
		    return(ld);
		
	}
	public List<CorrectiveActionData> getCA() {
	    List<CorrectiveActionData> phones = new ArrayList<CorrectiveActionData>();
	    for (int i=1; i<=10; i++){
	    	phones.add(GenerateTestCA(i));
	    }
	    return(phones);
	    
	    
	  }
	public List<TaskData> getTasks() {
	    List<TaskData> phones = new ArrayList<TaskData>();
	    for (int i=1; i<=10; i++){
	    	phones.add(GenerateTestTask(i));
	    }
	    return(phones);
	    
	    
	  }
	public List<AGComplaintData> getAG() {
		List<AGComplaintData> tmp = new ArrayList<AGComplaintData>();
		for (int i=1; i<=9; i++){
	    	tmp.add(GenerateTestAG(i));
	    }
	    return(tmp);
	}
	public List<ComplaintData> getComp() {
		List<ComplaintData> tmp = new ArrayList<ComplaintData>();
		for (int i=1; i<=9; i++){
	    	tmp.add(GenerateTestComp(i));
	    }
	    return(tmp);
	}
	public List<BBBComplaintData> getBBBComp() {
		List<BBBComplaintData> tmp = new ArrayList<BBBComplaintData>();
		for (int i=1; i<=9; i++){
	    	tmp.add(GenerateTestBBB(i));
	    }
	    return(tmp);
	}
}
