package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.model.entity.SaleEntity;
import org.springframework.data.domain.Page;

public class SaleMapper {
    private SaleMapper() {
    }

    public static SaleResponseDTO saleEntityToSaleResponseDTO(SaleEntity entity) {
        final var response = new SaleResponseDTO();
        response.setOrderNumber(entity.getUuid());
        response.setCashbackPrice(entity.getCashback_price());
        response.setTotalPrice(entity.getTotal_price());
        response.setProducts(DiscSaleMapper.discsSalesEntitiesToDiscsDTO(entity.getDiscSaleEntities()));
        return response;
    }

    public static Page<SaleResponseDTO> saleListEntityToSaleResponseList(Page<SaleEntity> list) {
        return list.map(SaleMapper::saleEntityToSaleResponseDTO);
    }
}
