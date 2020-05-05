package br.com.helpdev.musicstore.exception;

public class NoGenresException extends ImportedException {
    public NoGenresException() {
        super("No Genres to import");
    }
}
