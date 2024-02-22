package com.bookmyshow.exception;

import com.bookmyshow.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,false);
        return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity <Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map< String,String> response= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error1)->{
            String fieldName = ((FieldError)error1).getField();
            String message= error1.getDefaultMessage();
            response.put(fieldName,message);
        });
        return  new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ApiException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,true);
        return  new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(InternalAuthenticationServiceException.class)
public ResponseEntity<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
    ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    return response;
}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception e) {
        e.printStackTrace();
        ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }


}
