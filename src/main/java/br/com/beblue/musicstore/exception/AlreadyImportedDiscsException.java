package br.com.beblue.musicstore.exception;

public class AlreadyImportedDiscsException extends ImportedException {
    public AlreadyImportedDiscsException() {
        super("Already imported discs");
    }
}
