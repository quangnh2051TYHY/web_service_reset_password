package org.example.quangnh1.exception;

import org.example.quangnh1.model.response.ServiceResult;
import org.example.quangnh1.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<ServiceResult> handlerUserServiceException(UserServiceException ex, WebRequest request) {
        ServiceResult serviceResult = new ServiceResult(null, ServiceResult.STATUS_FAIL, ex.getMessage());
        return new ResponseEntity(serviceResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String,String> responseBody = new HashMap<>();
//        responseBody.put("path",request.getContextPath());
//        responseBody.put("message","The URL you have reached is not in service at this time (404).");
//        return new ResponseEntity<Object>(responseBody,HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<ServiceResult> handlerAllException(Exception ex, WebRequest request) {
//        return new ResponseEntity(ex,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
