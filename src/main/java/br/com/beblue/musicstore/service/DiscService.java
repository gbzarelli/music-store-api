package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.beblue.musicstore.util.converter.DiscConverter.discEntityToDiscDTO;
import static br.com.beblue.musicstore.util.converter.DiscConverter.pageEntityToPageDTO;

@Service
public class DiscService {

    private final DiscRepository discRepository;
    private final CashbackService cashbackService;

    @Autowired
    public DiscService(DiscRepository discRepository, CashbackService cashbackService) {
        this.discRepository = discRepository;
        this.cashbackService = cashbackService;
    }

    public Page<DiscDTO> getDiscs(Pageable pageable) {
        Page<DiscEntity> entities = discRepository.findAllByOrderByName(pageable);
        return fillCashbackAndConvert(entities);
    }

    public Page<DiscDTO> getDiscs(String genre, Pageable pageable) {
        Page<DiscEntity> entities = discRepository.findAllByGenreEntityNameIgnoreCaseOrderByName(genre, pageable);
        return fillCashbackAndConvert(entities);
    }

    public DiscDTO getDisc(int id) throws NoValuePresentException {
        Optional<DiscEntity> byId = discRepository.findById(id);
        if (byId.isPresent()) {
            DiscDTO discDTO = discEntityToDiscDTO(byId.get());
            return fillCashback(discDTO);
        }
        throw new NoValuePresentException("No disc with id=" + id);
    }

    private Page<DiscDTO> fillCashbackAndConvert(Page<DiscEntity> entities) {
        Page<DiscDTO> discDTOS = pageEntityToPageDTO(entities);
        return fillCashback(discDTOS);
    }

    private Page<DiscDTO> fillCashback(Page<DiscDTO> entities) {
        entities.forEach(this::fillCashback);
        return entities;
    }

    private DiscDTO fillCashback(DiscDTO disc) {
        GenreCashbackEntity cashback = cashbackService.getCashback(disc.getGenre().getId());
        disc.setCashback(cashback.getCashback());
        return disc;
    }

}
