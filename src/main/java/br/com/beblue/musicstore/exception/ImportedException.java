package br.com.beblue.musicstore.exception;

public class ImportedException extends Exception {
    public ImportedException() {
    }

    public ImportedException(String message) {
        super(message);
    }

    public ImportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportedException(Throwable cause) {
        super(cause);
    }

    public ImportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
