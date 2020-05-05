package br.com.helpdev.musicstore.service;

import br.com.helpdev.musicstore.exception.ImportedException;

public interface SpotifyImportService {

    @FunctionalInterface
    interface ImportErrorCallback {
        void error(ImportedException throwable);
    }

    void importDiscsByGenres(SpotifyImportService.ImportErrorCallback importErrorCallback);
}
