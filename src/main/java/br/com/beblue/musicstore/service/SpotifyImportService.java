package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.exception.ImportedException;

public interface SpotifyImportService {

    @FunctionalInterface
    interface ImportErrorCallback {
        void error(ImportedException throwable);
    }

    void importDiscsByGenres(SpotifyImportService.ImportErrorCallback importErrorCallback);
}
