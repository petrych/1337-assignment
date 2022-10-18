package tech.petrych.congestion.calculator.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	public GlobalExceptionHandler() {
		
		super();
	}
	
	@ExceptionHandler(InputModelValidationException.class)
	public ResponseEntity<Object> handleInputModelValidationException(InputModelValidationException ex,
	                                                                  HttpServletRequest request) {
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String errorMessage = ex.getMessage();
		ApiError apiError = new ApiError(httpStatus.value(), errorMessage, request.getRequestURI());
		LOG.warn(errorMessage);
		
		return new ResponseEntity<>(apiError, Objects.requireNonNull(httpStatus));
	}
	
}
