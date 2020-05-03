package br.com.beblue.musicstore.exception;

public class ImportedException extends Exception {
    public ImportedException(final String message) {
        super(message);
    }
    public ImportedException(final Throwable cause) {
        super(cause);
    }
}
