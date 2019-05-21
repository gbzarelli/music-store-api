package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscSaleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscSaleRepository extends CrudRepository<DiscSaleEntity, Integer> {
}