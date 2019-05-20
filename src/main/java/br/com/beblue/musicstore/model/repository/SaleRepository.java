package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.SaleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<SaleEntity, Integer> {
}
