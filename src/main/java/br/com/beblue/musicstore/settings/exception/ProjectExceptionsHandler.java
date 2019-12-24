package br.com.beblue.musicstore.settings.exception;

import br.com.beblue.musicstore.controller.dto.HttpResponseException;
import br.com.beblue.musicstore.exception.IllegalDateException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ProjectExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoValuePresentException.class)
    public ResponseEntity handleNoValuePresentException(NoValuePresentException ex) {
        return ResponseEntity.badRequest().body(createHttpResponse(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalDateException.class)
    public ResponseEntity handleIllegalDateException(IllegalDateException ex) {
        return ResponseEntity.badRequest().body(createHttpResponse(ex));
    }

    private HttpResponseException createHttpResponse(Exception exception) {
        HttpResponseException response = new HttpResponseException(exception.getMessage());
        response.addFieldError(exception.getLocalizedMessage());
        response.addFieldError(exception.toString());
        return response;
    }

}
