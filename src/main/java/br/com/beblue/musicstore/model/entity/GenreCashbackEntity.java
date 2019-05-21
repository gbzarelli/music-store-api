package br.com.beblue.musicstore.model.entity;

import javax.persistence.*;

import static br.com.beblue.musicstore.model.entity.GenreCashbackEntity.TABLE_NAME;

@Entity(name = TABLE_NAME)
public class GenreCashbackEntity {
    static final String TABLE_NAME = "genres_cashback_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private GenreEntity genreEntity;

    private boolean enable;
    private int weekday;
    private int cashback;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getCashback() {
        return cashback;
    }

    public void setCashback(int cashback) {
        this.cashback = cashback;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "GenreCashbackEntity{" +
                "id=" + id +
                ", weekday=" + weekday +
                ", cashback=" + cashback +
                ", genreEntity=" + genreEntity +
                ", enable=" + enable +
                '}';
    }
}
