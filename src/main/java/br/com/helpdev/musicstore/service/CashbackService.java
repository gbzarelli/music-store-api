package br.com.helpdev.musicstore.service;

import br.com.helpdev.musicstore.model.entity.GenreCashbackEntity;

import java.util.List;

public interface CashbackService {
    List<GenreCashbackEntity> getCashBacks();
}
