package br.com.beblue.musicstore.exception;

public class ImportedException extends Exception {
    public ImportedException(String message) {
        super(message);
    }
    public ImportedException(Throwable cause) {
        super(cause);
    }
}
