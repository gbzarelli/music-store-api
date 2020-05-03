package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreCashbackRepository extends CrudRepository<GenreCashbackEntity, Integer> {
    GenreCashbackEntity findByGenreEntityIdAndWeekdayAndEnableTrue(final int genreId,final  int weekday);

    List<GenreCashbackEntity> findByWeekdayAndEnableTrue(final int weekday);

}