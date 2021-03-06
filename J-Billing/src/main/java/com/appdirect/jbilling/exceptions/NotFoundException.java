/**
 * 
 */
package com.appdirect.jbilling.exceptions;

/**
 * Exception for Any Resource not found
 * 
 * @author rajesh
 *
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
