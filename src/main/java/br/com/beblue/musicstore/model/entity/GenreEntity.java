package br.com.beblue.musicstore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = GenreEntity.TABLE_NAME)
public class GenreEntity {
    static final String TABLE_NAME = "genres_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
