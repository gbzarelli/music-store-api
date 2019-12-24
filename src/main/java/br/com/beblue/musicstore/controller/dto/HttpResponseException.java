package br.com.beblue.musicstore.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpResponseException {

    private String message;
    private Date timestamp = new Date();
    private List<String> fields = new ArrayList<>();

    public HttpResponseException(String message) {
        this.message = message;
    }

    public void addFieldError(String field) {
        fields.add(field);
    }

    public List<String> getFields() {
        return fields;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "HttpResponseException{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", fields=" + fields +
                '}';
    }
}
