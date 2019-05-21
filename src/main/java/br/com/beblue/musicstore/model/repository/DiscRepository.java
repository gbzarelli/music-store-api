package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscRepository extends PagingAndSortingRepository<DiscEntity, Integer> {
    Page<DiscEntity> findAllByGenreEntityNameIgnoreCaseOrderByName(String name, Pageable pageable);

    Page<DiscEntity> findAllByOrderByName(Pageable pageable);

    List<DiscEntity> findByIdIn(List<Integer> ids);
}