package br.com.beblue.musicstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlreadyImportedDiscsException extends ImportedException {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlreadyImportedDiscsException.class.getName());

    public AlreadyImportedDiscsException() {
        super("Discs already imported");
    }

    @Override
    public void printStackTrace() {
        LOGGER.info(getMessage());
    }
}
