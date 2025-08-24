package com.tuonglh.coffee.samplecode.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHander {


    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            ResourceNotFoundException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request){
        System.out.println("handleValidationException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));


        String message = e.getMessage();
        if(e instanceof MethodArgumentNotValidException){
            int start = message.lastIndexOf("["); // lấy vị trí cuối cùng có [
            int end = message.lastIndexOf("]"); //lấy vị trí cuối cùng có ]
            message = message.substring(start + 1, end - 1 );  //lấy lỗi

            errorResponse.setError("Payload Invalid");
        }else if(e instanceof ConstraintViolationException){
            message = message.substring(message.indexOf(" ")+1);
            errorResponse.setError("PathVariable Invalid");
        }

        errorResponse.setMessage(message);
        return errorResponse;
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(Exception e, WebRequest request){
        System.out.println("handleValidationException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        String message = e.getMessage();
        if(e instanceof MethodArgumentTypeMismatchException){
            errorResponse.setMessage("Failed to convert value of type");
        }
        return errorResponse;
    }

}
