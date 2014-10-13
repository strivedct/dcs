package com.kelmtech.dcs.client.datastructures;

public class StatusCodes {
	public String name = "";
	public String cid = "";
	public String user = "";
	public String action = "";
	public StatusCodes(){
		
	}
	public String getName(){
		return(name);
	}
	public String getCid(){
		return(cid);
	}
	public String getUser(){
		return(user);
	}
	public String getAction(){
		return(action);
	}
	public void setName(String n){
		name = n;
	}
	public void setCid(String c){
		cid = c;
	}
	public void getUser(String u){
		user = u;
	}
	public void getAction(String a){
		action = a;
	}
}
