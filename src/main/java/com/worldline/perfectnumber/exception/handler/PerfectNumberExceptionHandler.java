package com.worldline.perfectnumber.exception.handler;

import com.worldline.perfectnumber.exception.EmptyArrayException;
import com.worldline.perfectnumber.exception.InvalidRangeException;
import com.worldline.perfectnumber.valueobject.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class PerfectNumberExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request){

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmptyArrayException.class)
    public ResponseEntity<ErrorResponse> handleEmptyArrayException(EmptyArrayException e){

        List<String> errors = List.of(e.getMessage());
        ErrorResponse response = new ErrorResponse(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRangeException(InvalidRangeException e){

        List<String> errors = List.of(e.getMessage());
        ErrorResponse response = new ErrorResponse(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
