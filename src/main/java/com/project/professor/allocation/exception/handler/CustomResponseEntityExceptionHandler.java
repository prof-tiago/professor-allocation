package com.project.professor.allocation.exception.handler;

import com.project.professor.allocation.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected final ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, getErrorDTO(ex, status, request), new HttpHeaders(), status, request);
    }

    private ErrorDTO getErrorDTO(Throwable ex, HttpStatus status, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setTimestamp(new Date());
        errorDTO.setStatusCode(status.value());
        errorDTO.setStatusReason(status.getReasonPhrase());
        errorDTO.setPath(((ServletWebRequest) request).getRequest().getServletPath());
        errorDTO.setException(ex.getClass().getName());
        errorDTO.setMessage(ex.getLocalizedMessage());
        errorDTO.setStack(getStackTrace(ex));

        return errorDTO;
    }

    private String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
