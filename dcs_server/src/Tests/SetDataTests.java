/**
 * 
 */
package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelmtech.dcs.client.datastructures.ComplaintData;

import dcs.server.SetData;

/**
 * @author dkelm_laptop
 *
 */
public class SetDataTests {
	public static SetData sd;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sd = new SetData();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//test_edit_complaint();
		test_add_complaint();
		//test_get_complaint();
	}
	public void test_get_complaint(){
		ComplaintData cd = sd.get_complaint("11", "1", "test");
		try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cd);
		System.out.println(jsonString);
		} catch (Exception e){
			
		}
	}
	public void test_edit_complaint(){
		ComplaintData cd = new ComplaintData();
		  cd.id= "1";
		  cd.our_account= "123456";
		  cd.customer_account= "654321";
		  cd.customer= "TEST22";
		  cd.complaint_date= "2014-07-30";
		  cd.complaint_text= "test";
		  cd.status= "OPEN";
		  cd.sub_status= "HAHAH";
		  cd.resolution_date= "";
		  cd.resolution_text= "";
		  cd.name= "HATE YOU";
		  cd.phone= "1234567890";
		  cd.ssn= "987654321";
		  cd.attachement_id= "";
		  cd.corrective_action_id= "";
		  cd.lawsuit_id= "2";
		  cd.ag_id= "";
		  cd.bbb_id= "";
		  cd.client_id= "1";
		  cd.audit_id= "";
		  cd.type= "sdafads";
		  cd.sub_type= "dadsf";
		  cd.entered_by= "1";
		  cd.resolved_by= "";
		  cd.entered_by_str= "test";
		  cd.resolved_by_str= "";
		  cd.keyval = "0";
		  cd.user_name = "dkelm";
		  cd.address = "123 fake st";
		  cd.city = "fakesburg";
		  cd.state = "il";
		  cd.zip = "12345";
		  cd.phone2 = "";
		  sd.edit_complaint(cd);
		  ComplaintData cd2 = sd.get_complaint("1", "1", "test");
		  boolean failed = false;
		  if(!cd.id.equals(cd2.id)){
			  fail("Updated Data does not match id");
		  }
		  if(!cd.our_account.equals(cd2.our_account)){
			  fail("Updated Data does not match our_acount");
		  }
		  if(!cd.customer_account.equals(cd2.customer_account)){
			  fail("Updated Data does not match customer_account");
		  }
		  if(!cd.customer.equals(cd2.customer)){
			  System.out.println(cd2.customer);
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.complaint_date.equals(cd2.complaint_date)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.complaint_text.equals(cd2.complaint_text)){
			  fail("Updated Data does not match customer");
		  }
		  //if(!cd.status.equals(cd2.status)){
			//  fail("Updated Data does not match customer");
		  //}
		  //if(!cd.sub_status.equals(cd2.sub_status)){
			//  fail("Updated Data does not match customer");
		  //}
		  if(!cd.resolution_date.equals(cd2.resolution_date)){
			  System.out.println(cd.resolution_date + " || " + cd2.resolution_date);
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.resolution_text.equals(cd2.resolution_text)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.name.equals(cd2.name)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.phone.equals(cd2.phone)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.ssn.equals(cd2.ssn)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.attachement_id.equals(cd2.attachement_id)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.corrective_action_id.equals(cd2.corrective_action_id)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.lawsuit_id.equals(cd2.lawsuit_id)){
			  if (cd2.lawsuit_id.equals("0")){
				  
			  } else {
		      System.out.println("Lawsuit ID: " + cd.lawsuit_id + " does not match DB: " + cd2.lawsuit_id);
			  fail("Updated Data does not match customer");
			  }
		  }
		  if(!cd.ag_id.equals(cd2.ag_id)){
			  if (cd2.ag_id.equals("0")){
				  
			  } else {
				  fail("Updated Data does not match customer");
			  }
		  }
		  if(!cd.bbb_id.equals(cd2.bbb_id)){
			  if (cd2.bbb_id.equals("0")){
				  
			  } else {
				  fail("Updated Data does not match customer");
			  }
		  }
		  if(!cd.client_id.equals(cd2.client_id)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.audit_id.equals(cd2.audit_id)){
			  fail("Updated Data does not match customer");
		  }
		  //if(!cd.type.equals(cd2.type)){
			//  fail("Updated Data does not match customer");
		  //}
		  //if(!cd.sub_type.equals(cd2.sub_type)){
			//  fail("Updated Data does not match customer");
		  //}
		  if(!cd.entered_by.equals(cd2.entered_by)){
			  fail("Updated Data does not match customer");
		  }
		  if(! cd.resolved_by.equals(cd2.resolved_by)){
			  if (cd2.resolved_by.equals("0")){
				  
			  } else {
				  fail("Updated Data does not match customer");
			  }
		  }
		  /*
		  if(!cd.entered_by_str.equals(cd2.entered_by_str)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.resolved_by_str.equals(cd2.resolved_by_str)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.keyval.equals(cd2.keyval)){
			  fail("Updated Data does not match customer");
		  }
		  
		  if(!cd.user_name.equals(cd2.user_name)){
			  fail("Updated Data does not match customer");
		  }
		  */
		  /*
		  if(!cd.address.equals(cd2.address)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.city.equals(cd2.city)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.state.equals(cd2.state)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.zip.equals(cd2.zip)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.phone2.equals(cd2.phone2)){
			  fail("Updated Data does not match customer");
		  }
		  */
		  if (failed){
			  fail("Complaint Edited Data does not match complaint DB data, update failed");
		  }
	}
	public void test_add_complaint(){
		ComplaintData cd = new ComplaintData();
		  cd.id= "0";
		  cd.our_account= "123456";
		  cd.customer_account= "654321";
		  cd.customer= "Test Cust 1";
		  cd.complaint_date= "2014-07-30";
		  cd.complaint_text= "test";
		  cd.status= "OPEN";
		  cd.sub_status= "HAHAH";
		  cd.resolution_date= "";
		  cd.resolution_text= "";
		  cd.name= "HATE YOU";
		  cd.phone= "1234567890";
		  cd.ssn= "987654321";
		  cd.attachement_id= "";
		  cd.corrective_action_id= "";
		  cd.lawsuit_id= "";
		  cd.ag_id= "";
		  cd.bbb_id= "";
		  cd.client_id= "1";
		  cd.audit_id= "";
		  cd.type= "sdafads";
		  cd.sub_type= "dadsf";
		  cd.entered_by= "1";
		  cd.resolved_by= "";
		  cd.entered_by_str= "test";
		  cd.resolved_by_str= "";
		  cd.keyval = "0";
		  cd.user_name = "dkelm";
		  cd.address = "123 fake st";
		  cd.city = "fakesburg";
		  cd.state = "il";
		  cd.zip = "12345";
		  cd.phone2 = "";
		  cd.user_name = "dkelm";
		  String compid = sd.new_complaint(cd, "dkelm");
		  System.out.println(compid);
		  ComplaintData cd2 = sd.get_complaint(compid, "1", "test");
		  boolean failed = false;
		 
		  if(!cd.our_account.equals(cd2.our_account)){
			  fail("Updated Data does not match our_acount");
		  }
		  if(!cd.customer_account.equals(cd2.customer_account)){
			  fail("Updated Data does not match customer_account");
		  }
		  if(!cd.customer.equals(cd2.customer)){
			  System.out.println(cd2.customer);
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.complaint_date.equals(cd2.complaint_date)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.complaint_text.equals(cd2.complaint_text)){
			  fail("Updated Data does not match customer");
		  }
		  //if(!cd.status.equals(cd2.status)){
			//  fail("Updated Data does not match customer");
		  //}
		  //if(!cd.sub_status.equals(cd2.sub_status)){
			//  fail("Updated Data does not match customer");
		  //}
		  if(!cd.resolution_date.equals(cd2.resolution_date)){
			  System.out.println(cd.resolution_date + " || " + cd2.resolution_date);
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.resolution_text.equals(cd2.resolution_text)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.name.equals(cd2.name)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.phone.equals(cd2.phone)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.ssn.equals(cd2.ssn)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.attachement_id.equals(cd2.attachement_id)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.corrective_action_id.equals(cd2.corrective_action_id)){
			  fail("Updated Data does not match customer");
		  }
		 
		  if(!cd.client_id.equals(cd2.client_id)){
			  fail("Updated Data does not match customer");
		  }
		 
		  //if(!cd.type.equals(cd2.type)){
			//  fail("Updated Data does not match customer");
		  //}
		  //if(!cd.sub_type.equals(cd2.sub_type)){
			//  fail("Updated Data does not match customer");
		  //}
		  if(!cd.entered_by.equals(cd2.entered_by)){
			  fail("Updated Data does not match customer");
		  }
		  if(! cd.resolved_by.equals(cd2.resolved_by)){
			  if (cd2.resolved_by.equals("0")){
				  
			  } else {
				  fail("Updated Data does not match customer");
			  }
		  }
		  /*
		  if(!cd.entered_by_str.equals(cd2.entered_by_str)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.resolved_by_str.equals(cd2.resolved_by_str)){
			  fail("Updated Data does not match customer");
		  }
		  if(!cd.keyval.equals(cd2.keyval)){
			  fail("Updated Data does not match customer");
		  }
		  
		  if(!cd.user_name.equals(cd2.user_name)){
			  fail("Updated Data does not match customer");
		  }
		  */
		  
		  if(!cd.address.equals(cd2.address)){
			  fail("Updated Data does not match address");
		  }
		  if(!cd.city.equals(cd2.city)){
			  fail("Updated Data does not match city");
		  }
		  if(!cd.state.equals(cd2.state)){
			  fail("Updated Data does not match state");
		  }
		  if(!cd.zip.equals(cd2.zip)){
			  fail("Updated Data does not match zip");
		  }
		  if(!cd.phone2.equals(cd2.phone2)){
			  fail("Updated Data does not match phone2");
		  }
		  
		  if (failed){
			  fail("Complaint Added Data does not match complaint DB data, update failed");
		  }
	}
}
