package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import br.com.beblue.musicstore.model.repository.GenreCashbackRepository;
import br.com.beblue.musicstore.service.CashbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.beblue.musicstore.util.DateUtil.getDayOfWeek;

@Service
class CashbackServiceImpl implements CashbackService {

    private final GenreCashbackRepository cashbackRepository;

    @Autowired
    CashbackServiceImpl(final GenreCashbackRepository cashbackRepository) {
        this.cashbackRepository = cashbackRepository;
    }

    public GenreCashbackEntity getCashback(Integer genreId) {
        return cashbackRepository.findByGenreEntityIdAndWeekdayAndEnableTrue(genreId, getDayOfWeek());
    }

    public List<GenreCashbackEntity> getCashbacks() {
        return cashbackRepository.findByWeekdayAndEnableTrue(getDayOfWeek());
    }

}