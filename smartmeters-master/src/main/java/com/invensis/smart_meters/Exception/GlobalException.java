package com.invensis.smart_meters.Exception;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.invensis.smart_meters.Response.ResponseHandler;
import com.invensis.smart_meters.Response.StandardResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Configuration
@Slf4j
public class GlobalException {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public StandardResponse handleNoHandlerFoundException(HttpServletRequest request, Exception ex) {
		log.error("Resource No found ", ex);
		return ResponseHandler.failedResponse("Resource No found" + ex.getLocalizedMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public StandardResponse handler(Exception ex) {
		log.error("Resource No found ", ex);
		return ResponseHandler.failedResponse("Exception: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public StandardResponse handleMultipartException(HttpServletRequest request, MultipartException ex) {
		log.error("Invalid Upload Request ", ex);
		return ResponseHandler.failedResponse("Invalid Upload Request");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public StandardResponse handleMethodNotSupported(HttpServletRequest request,
			HttpRequestMethodNotSupportedException ex) {
		log.error("method not supported ", ex);
		return ResponseHandler.failedResponse("HTTP request method not supported for this operation.");
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public StandardResponse handleIOException(HttpServletRequest request, IOException ex) {
		log.error("IO Error: ", ex);
		return ResponseHandler.failedResponse("IO Error: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public StandardResponse validationError(MethodArgumentNotValidException ex) {
		log.error("validation failed ", ex);
		BindingResult result = ex.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		FieldError[] errors = (FieldError[]) fieldErrors.toArray(new FieldError[fieldErrors.size()]);
		return ResponseHandler.failedResponse(errors, "validation failed");
	}

	@ExceptionHandler(ServletException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public StandardResponse handleServletException(HttpServletRequest request, Exception ex) {
		log.error("UnAuthorized Request ", ex);
		return ResponseHandler.failedResponse("Error: " + ex.getLocalizedMessage());
	}
}