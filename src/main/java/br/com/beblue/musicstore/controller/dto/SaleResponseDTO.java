package br.com.beblue.musicstore.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDTO {

    private String orderNumber;
    private double totalPrice;
    private double cashbackPrice;
    private List<DiscDTO> products;

}
