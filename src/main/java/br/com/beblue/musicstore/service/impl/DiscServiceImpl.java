package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.service.DiscService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.beblue.musicstore.util.mapper.DiscMapper.discEntityToDiscDTO;
import static br.com.beblue.musicstore.util.mapper.DiscMapper.pageEntityToPageDTO;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class DiscServiceImpl implements DiscService {

    private final DiscRepository discRepository;
    private final CashbackServiceImpl cashbackService;

    public Page<DiscDTO> getDiscs(Pageable pageable) {
        Page<DiscEntity> entities = discRepository.findAllByOrderByName(pageable);
        return fillCashbackAndConvert(entities);
    }

    public Page<DiscDTO> getDiscsByGenre(String genre, Pageable pageable) {
        final var entities = discRepository.findAllByGenreEntityNameIgnoreCaseOrderByName(genre, pageable);
        return fillCashbackAndConvert(entities);
    }

    public DiscDTO getDiscByID(Integer id) throws NoValuePresentException {
        final var byId = discRepository.findById(id);
        if (byId.isPresent()) {
            DiscDTO discDTO = discEntityToDiscDTO(byId.get());
            return fillCashback(discDTO);
        }
        throw new NoValuePresentException("No disc with id=" + id);
    }

    private Page<DiscDTO> fillCashbackAndConvert(Page<DiscEntity> entities) {
        final var discDTOS = pageEntityToPageDTO(entities);
        return fillCashback(discDTOS);
    }

    private Page<DiscDTO> fillCashback(Page<DiscDTO> entities) {
        entities.forEach(this::fillCashback);
        return entities;
    }

    private DiscDTO fillCashback(DiscDTO disc) {
        final var cashback = cashbackService.getCashback(disc.getGenre().getId());
        disc.setCashback(cashback.getCashback());
        return disc;
    }

}
