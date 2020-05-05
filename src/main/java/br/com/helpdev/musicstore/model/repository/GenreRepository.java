package br.com.helpdev.musicstore.model.repository;

import br.com.helpdev.musicstore.model.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Integer> {
}