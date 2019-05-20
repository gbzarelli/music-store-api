package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.beblue.musicstore.util.converter.DiscConverter.*;

@Service
public class DiscService {

    private final DiscRepository discRepository;

    @Autowired
    public DiscService(DiscRepository discRepository) {
        this.discRepository = discRepository;
    }

    public Page<DiscDTO> getDiscs(Pageable pageable) {
        Page<DiscEntity> entities = discRepository.findAllByOrderByName(pageable);
        return pageEntrityToPageDTO(entities);
    }

    public Page<DiscDTO> getDiscs(String genre, Pageable pageable) {
        Page<DiscEntity> entities = discRepository.findAllByGenreEntityNameIgnoreCaseOrderByName(genre, pageable);
        return pageEntrityToPageDTO(entities);
    }

    public DiscDTO getDisc(int id) throws NoValuePresentException {
        Optional<DiscEntity> byId = discRepository.findById(id);
        if (byId.isPresent()) {
            return discEntityToDiscDTO(byId.get());
        }
        throw new NoValuePresentException("No disc with id=" + id);
    }
}
