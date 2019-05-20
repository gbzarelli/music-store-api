package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreCashbackRepository extends CrudRepository<GenreCashbackEntity, Integer> {
}