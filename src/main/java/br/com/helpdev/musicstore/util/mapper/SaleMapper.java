package br.com.helpdev.musicstore.util.mapper;

import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.model.entity.SaleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleMapper {

    public static SaleResponseDTO saleEntityToSaleResponseDTO(final SaleEntity entity) {
        final var response = new SaleResponseDTO();
        response.setOrderNumber(entity.getUuid());
        response.setCashbackPrice(entity.getCashback_price());
        response.setTotalPrice(entity.getTotal_price());
        response.setProducts(DiscSaleMapper.discsSalesEntitiesToDiscsDTO(entity.getDiscSaleEntities()));
        return response;
    }

    public static Page<SaleResponseDTO> saleListEntityToSaleResponseList(final Page<SaleEntity> list) {
        return list.map(SaleMapper::saleEntityToSaleResponseDTO);
    }
}
