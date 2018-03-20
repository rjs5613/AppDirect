package com.appdirect.jbilling.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author rajesh
 *
 */
public class ResponseUtil {
	
	/**
	 * 
	 * @param response
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity<ResponseContainer> getResponse(Object response, HttpStatus httpStatus){
		ResponseContainer responseContainer = ResponseContainer.getInstance(response, APIStatus.SUCCESS);
		responseContainer.setStatus(httpStatus.value());
		return ResponseEntity.status(httpStatus).body(responseContainer);
	}

}
