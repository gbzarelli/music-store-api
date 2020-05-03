package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;

import java.util.List;

public interface CashbackService {
    List<GenreCashbackEntity> getCashBacks();
}
