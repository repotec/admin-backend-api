package com.ntg.adm.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ntg.adm.response.ErrorResponse;

@ControllerAdvice
@Slf4j
public class AdminExceptionHandler extends ResponseEntityExceptionHandler {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	@ExceptionHandler(value = { RecordNotFoundException.class })
	public final ResponseEntity<Object> FunctionNotFoundHandler(Exception e, WebRequest r) {
		List<String> errors = new ArrayList<String>();
		errors.add(e.getMessage());
	
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
														 "0001",
														 "Record Not Found Exception",
														 r.getDescription(false),
														 errors);
		
		//jdbcTemplate.update("call LOG_EXCEPTIONS(?,?,?)", adminErrorResponse.getErrorCode(), adminErrorResponse.getErrorMessage(), r.getDescription(false));
		
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(value = { IdentifierNotFound.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handleIdentifierNotFound(Exception e, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		errors.add(e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
														"9910",
														"Identifier Not Found Exception", 
														request.getDescription(false),
														errors);

		//jdbcTemplate.update("call LOG_EXCEPTIONS(?,?,?)", adminErrorResponse.getErrorCode(), adminErrorResponse.getErrorMessage(), r.getDescription(false));
		
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST); 
	}
	
	
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	//public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
	//	
	//	String defaultMessage = null;
	//	final Optional<ObjectError> firstError = ex.getBindingResult().getAllErrors().stream().findFirst();
	//	if (firstError.isPresent()) {
	//		defaultMessage = firstError.get().getDefaultMessage();
	//	}
	//	
	//	List<String> errors = new ArrayList<String>();
	//	errors.add(defaultMessage);
	//	 
	//	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
	//													"0002",
	//													"Method Argument Not Valid Exception", 
	//													request.getDescription(false),
	//													errors);
	//	
	//	//jdbcTemplate.update("call LOG_EXCEPTIONS(?,?,?)", adminErrorResponse.getErrorCode(), defaultMessage, request.getDescription(false));
	//	
	//	return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST); 
	//}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> generalHandleException(Exception ex, WebRequest request) {
		
		//jdbcTemplate.update("call LOG_EXCEPTIONS(?,?,?)", adminErrorResponse.getErrorCode(), adminErrorResponse.getErrorMessage(), request.getDescription(false));
		
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		 
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
														"9999",
														"Internal Server Error",
														request.getDescription(false),
														errors);
		ex.printStackTrace();
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = new ArrayList<String>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();


		for(FieldError fieldError : fieldErrors)
			errors.add(fieldError.getDefaultMessage());

		for(ObjectError globalError : globalErrors)
			errors.add(globalError.getDefaultMessage());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
														"9998",
														"Internal Server Error",
														request.getDescription(false),
														errors);
		ex.getStackTrace();
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
