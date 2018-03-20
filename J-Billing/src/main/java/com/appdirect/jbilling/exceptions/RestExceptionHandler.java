package com.appdirect.jbilling.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Uncaught Exception Handler for Rest APIs
 * 
 * @author rajesh
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex);
		errorMessage.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		return buildResponseEntity(errorMessage);
	}

	/**
	 * Exception Handler for {@link NotFoundException}
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(NotFoundException ex) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex);
		return buildResponseEntity(errorMessage);

	}

	/**
	 * Exception Handler for {@link JobExecutionException}
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(JobExecutionException.class)
	public ResponseEntity<Object> handleJobExecutionException(JobExecutionException ex) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex);
		return buildResponseEntity(errorMessage);

	}

	private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
		return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
	}

}