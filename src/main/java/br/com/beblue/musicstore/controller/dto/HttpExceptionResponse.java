package br.com.beblue.musicstore.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class HttpExceptionResponse {

    private final String message;
    private final Date timestamp = new Date();
    private final List<String> fields = new ArrayList<>();

    public HttpExceptionResponse(String message) {
        this.message = message;
    }

    public void addFieldError(String field) {
        fields.add(field);
    }

}
