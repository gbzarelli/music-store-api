package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends CrudRepository<DiscEntity, Integer> {
}