package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.model.entity.GenreCashbackEntity;
import br.com.helpdev.musicstore.model.repository.GenreCashbackRepository;
import br.com.helpdev.musicstore.service.CashbackService;
import br.com.helpdev.musicstore.util.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class CashbackServiceImpl implements CashbackService {

    private final GenreCashbackRepository cashbackRepository;

    public GenreCashbackEntity getCashback(final Integer genreId) {
        return cashbackRepository.findByGenreEntityIdAndWeekdayAndEnableTrue(genreId, DateUtil.getDayOfWeek());
    }

    public List<GenreCashbackEntity> getCashBacks() {
        return cashbackRepository.findByWeekdayAndEnableTrue(DateUtil.getDayOfWeek());
    }

}