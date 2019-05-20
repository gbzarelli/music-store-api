package br.com.beblue.musicstore.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = DiscEntity.TABLE_NAME)
public class DiscEntity {
    static final String TABLE_NAME = "discs_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String artist;
    private String spotify_href;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private GenreEntity genreEntity;

    private double price;
    private Date date_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSpotify_href() {
        return spotify_href;
    }

    public void setSpotify_href(String spotify_href) {
        this.spotify_href = spotify_href;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    @Override
    public String toString() {
        return "DiscEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", spotify_href='" + spotify_href + '\'' +
                ", genreEntity=" + genreEntity +
                ", price=" + price +
                ", date_time=" + date_time +
                '}';
    }
}
