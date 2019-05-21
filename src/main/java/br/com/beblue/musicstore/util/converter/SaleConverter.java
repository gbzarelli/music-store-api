package br.com.beblue.musicstore.util.converter;

import br.com.beblue.musicstore.dto.SaleResponseDTO;
import br.com.beblue.musicstore.model.entity.SaleEntity;

public class SaleConverter {
    public static SaleResponseDTO saleEntityToSaleResponseDTO(SaleEntity entity) {
        SaleResponseDTO response = new SaleResponseDTO();
        response.setOrderNumber(entity.getUuid());
        response.setCashbackPrice(entity.getCashback_price());
        response.setTotalPrice(entity.getTotal_price());
        response.setProducts(DiscSaleConverter.discsSalesEntitiesToDiscsDTO(entity.getDiscSaleEntities()));
        return response;
    }
}
