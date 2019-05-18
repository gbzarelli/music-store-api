package br.com.beblue.musicstore.settings.exception;

import br.com.beblue.musicstore.dto.HttpResponseException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProjectExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoValuePresentException.class)
    public ResponseEntity handleNoValuePresentException(NoValuePresentException ex) {
        return ResponseEntity.badRequest().body(createHttpResponse(ex));
    }

    private HttpResponseException createHttpResponse(Exception exception) {
        HttpResponseException httpResponseException = new HttpResponseException(exception.getMessage());
        httpResponseException.addFieldError(exception.getLocalizedMessage());
        httpResponseException.addFieldError(exception.toString());
        return httpResponseException;
    }

}
