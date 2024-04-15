package com.devajayantha.main.config;

import com.devajayantha.main.models.responses.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    private String message;
    private int status;
    private Object data;
    private Object errors;

    public ResponseData(String message, HttpStatus status, Object data) {
        this.message = message;
        this.status = status.value();
        this.data = data;
    }

    public ResponseData(String message, HttpStatus status, Object data, Object errors) {
        this.message = message;
        this.status = status.value();
        this.data = data;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public static ResponseData getResponseData(Errors errors) {
        if (errors.hasErrors()) {
            List<ErrorMessage> errorMessages = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                errorMessages.add(new ErrorMessage(error.getDefaultMessage(),error.getObjectName()));
            }
            return new ResponseData("Validation Error", HttpStatus.BAD_REQUEST, null, errorMessages);
        }
        return null;
    }
}
