package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscRepository extends PagingAndSortingRepository<DiscEntity, Integer> {
    Page<DiscEntity> findAllByGenreEntityNameIgnoreCaseOrderByName(final String name, final Pageable pageable);

    Page<DiscEntity> findAllByOrderByName(final Pageable pageable);

    List<DiscEntity> findByIdIn(final List<Integer> ids);
}