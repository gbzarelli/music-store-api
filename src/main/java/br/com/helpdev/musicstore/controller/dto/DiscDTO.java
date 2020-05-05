package br.com.helpdev.musicstore.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscDTO {

    private int id;
    private String name;
    private String artist;
    private GenreDTO genre;
    private double price;
    private int cashback;

}
