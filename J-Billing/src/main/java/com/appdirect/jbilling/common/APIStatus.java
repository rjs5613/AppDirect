package com.appdirect.jbilling.common;

public enum APIStatus {
	
	SUCCESS("Success"), FAILED("Failed");
	
	private String name;
	
	APIStatus(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}

}
