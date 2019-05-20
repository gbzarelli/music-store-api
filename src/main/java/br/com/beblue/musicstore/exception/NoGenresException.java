package br.com.beblue.musicstore.exception;

public class NoGenresException extends ImportedException {
    public NoGenresException() {
        super("No Genres to import");
    }
}
