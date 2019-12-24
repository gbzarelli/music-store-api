package br.com.beblue.musicstore.controller.dto;

public class DiscDTO {

    private int id;
    private String name;
    private String artist;
    private GenreDTO genre;
    private double price;
    private int cashback;

    public DiscDTO(int id, String name, String artist, GenreDTO genre, double price, int cashback) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.price = price;
        this.cashback = cashback;
    }

    public DiscDTO() {
    }

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

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCashback() {
        return cashback;
    }

    public void setCashback(int cashback) {
        this.cashback = cashback;
    }

    @Override
    public String toString() {
        return "DiscDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre=" + genre +
                ", price=" + price +
                ", cashback=" + cashback +
                '}';
    }
}
