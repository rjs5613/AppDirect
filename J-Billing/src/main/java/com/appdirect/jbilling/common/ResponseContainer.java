/**
 * 
 */
package com.appdirect.jbilling.common;

/**
 * @author rajesh
 *
 */
public class ResponseContainer {
	
	private int status;
	
	private APIStatus message;
	
	private Object payload;
	
	private ResponseContainer(){
		
	}
	
	public static ResponseContainer getInstance(Object obj, APIStatus message){
		ResponseContainer responseContainer = new ResponseContainer();
		responseContainer.message=message;
		responseContainer.payload=obj;
		return responseContainer;
	}

	public int getStatus() {
		return status;
	}

	public APIStatus getMessage() {
		return message;
	}

	public Object getPayload() {
		return payload;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	

}
