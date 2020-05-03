package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import br.com.beblue.musicstore.model.repository.GenreCashbackRepository;
import br.com.beblue.musicstore.service.CashbackService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.beblue.musicstore.util.DateUtil.getDayOfWeek;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class CashbackServiceImpl implements CashbackService {

    private final GenreCashbackRepository cashbackRepository;

    public GenreCashbackEntity getCashback(final Integer genreId) {
        return cashbackRepository.findByGenreEntityIdAndWeekdayAndEnableTrue(genreId, getDayOfWeek());
    }

    public List<GenreCashbackEntity> getCashBacks() {
        return cashbackRepository.findByWeekdayAndEnableTrue(getDayOfWeek());
    }

}