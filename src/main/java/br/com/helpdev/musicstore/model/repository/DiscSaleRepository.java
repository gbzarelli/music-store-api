package br.com.helpdev.musicstore.model.repository;

import br.com.helpdev.musicstore.model.entity.DiscSaleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscSaleRepository extends CrudRepository<DiscSaleEntity, Integer> {
}