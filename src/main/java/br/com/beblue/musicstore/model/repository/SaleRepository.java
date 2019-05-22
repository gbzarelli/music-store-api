package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<SaleEntity, Integer> {
    Page<SaleEntity> findByIdIn(List<Integer> ids, Pageable pageable);

    Page<SaleEntity> findAllBySaleDateTimeBetween(Date start, Date end, Pageable pageable);

    Optional<SaleEntity> findByUuid(String uuid);
}
