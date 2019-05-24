package br.com.beblue.musicstore.exception;

public class AlreadyImportedDiscsException extends ImportedException {
    public AlreadyImportedDiscsException() {
        super("Discs already imported");
    }

    @Override
    public void printStackTrace() {
        System.out.println("[INFO] "+getMessage());
    }
}
