package br.com.helpdev.musicstore.model.repository;

import br.com.helpdev.musicstore.model.entity.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<SaleEntity, Integer> {

    @Query(value = "select * from " + SaleEntity.TABLE_NAME + " sale where date(sale." + SaleEntity.COLUMN_SALE_DATE_TIME + ") between :start and :end", nativeQuery = true)
    Page<SaleEntity> findAllBySaleDateTimeBetween(@Param("start") final Date start, @Param("end") final Date end, final Pageable pageable);

    Optional<SaleEntity> findByUuid(final String uuid);
}
