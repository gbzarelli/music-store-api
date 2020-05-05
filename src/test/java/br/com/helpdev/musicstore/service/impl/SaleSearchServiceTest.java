package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.controller.dto.DiscDTO;
import br.com.helpdev.musicstore.controller.dto.GenreDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.exception.IllegalDateException;
import br.com.helpdev.musicstore.exception.NoValuePresentException;
import br.com.helpdev.musicstore.model.entity.DiscEntity;
import br.com.helpdev.musicstore.model.entity.DiscSaleEntity;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import br.com.helpdev.musicstore.model.entity.SaleEntity;
import br.com.helpdev.musicstore.model.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static br.com.helpdev.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class SaleSearchServiceTest {

    private static final int ID_SALE_ENTITY = 55;
    private static final int ID_DISC_SALE_ENTITY = 1;
    private static final int ID_DISC_ENTITY = 6;
    private static final int ID_GENRE_ENTITY = 4;
    private static final String UUID = "12456";

    @Autowired
    SaleSearchServiceImpl saleSearchService;

    @MockBean
    SaleRepository saleRepository;

    @Test
    void test_parameter_and_convert_data_with_search_by_date() throws IllegalDateException {
        SaleEntity saleEntity = getSaleEntitySample(UUID);
        Page<SaleEntity> page = new PageImpl<>(Collections.singletonList(saleEntity));
        when(saleRepository.findAllBySaleDateTimeBetween(any(), any(), any())).thenReturn(page);
        Page<SaleResponseDTO> ordersByDate = saleSearchService.getOrdersByDate(LocalDate.now().minusDays(1), LocalDate.now(), null);
        assertEquals(ordersByDate.getTotalElements(), 1);
        assertSale(ordersByDate.getContent().get(0), saleEntity);
    }

    @Test
    void test_start_date_after_end_date_with_IllegalDateException() {
        assertThrows(IllegalDateException.class, () -> {
            saleSearchService.getOrdersByDate(LocalDate.now(), LocalDate.now().minusDays(1), null);
        });
    }

    @Test
    void test_end_date_before_current_date_with_IllegalDateException() {
        assertThrows(IllegalDateException.class, () -> {
            saleSearchService.getOrdersByDate(LocalDate.now(), LocalDate.now().plusDays(1), null);
        });
    }

    @Test
    void test_no_value_order_by_number() {
        assertThrows(NoValuePresentException.class, () -> {
            when(saleRepository.findByUuid(UUID)).thenReturn(Optional.empty());
            saleSearchService.getOrderByNumber(UUID);
        });
    }

    @Test
    void test_get_order_by_number() throws NoValuePresentException {
        SaleEntity saleEntity = getSaleEntitySample(UUID);
        when(saleRepository.findByUuid(UUID)).thenReturn(Optional.of(saleEntity));
        SaleResponseDTO response = saleSearchService.getOrderByNumber(UUID);
        assertSale(response, saleEntity);
    }

    private void assertSale(SaleResponseDTO response, SaleEntity saleEntity) {
        assertEquals(response.getOrderNumber(), saleEntity.getUuid());
        assertEquals(response.getCashbackPrice(), saleEntity.getCashback_price());
        assertEquals(response.getTotalPrice(), saleEntity.getTotal_price());
        assertDiscsSales(response.getProducts(), saleEntity.getDiscSaleEntities());
    }

    private void assertDiscsSales(List<DiscDTO> products, List<DiscSaleEntity> discSaleEntities) {
        assertEquals(products.size(), discSaleEntities.size());
        for (int i = 0; i < products.size(); i++) {
            assertDiscSale(products.get(i), discSaleEntities.get(i));
        }
    }

    private void assertDiscSale(DiscDTO discDTO, DiscSaleEntity discSaleEntity) {
        assertEquals(discDTO.getCashback(), discSaleEntity.getCashback());
        assertEquals(discDTO.getArtist(), discSaleEntity.getDiscEntity().getArtist());
        assertEquals(discDTO.getPrice(), discSaleEntity.getDiscEntity().getPrice());
        assertEquals(discDTO.getId(), discSaleEntity.getDiscEntity().getId());
        assertGenre(discDTO.getGenre(), discSaleEntity.getDiscEntity().getGenreEntity());
    }

    private void assertGenre(GenreDTO genre, GenreEntity genreEntity) {
        assertEquals(genre.getId(), genreEntity.getId());
        assertEquals(genre.getName(), genreEntity.getName());
    }

    private SaleEntity getSaleEntitySample(String uuid) {
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setId(ID_SALE_ENTITY);
        saleEntity.setUuid(uuid);
        saleEntity.setTotal_price(20);
        saleEntity.setCashback_price(10);
        saleEntity.setSaleDateTime(new Date());
        saleEntity.setDiscSaleEntities(Collections.singletonList(getDiscSaleEntitySample()));
        return saleEntity;
    }

    private DiscSaleEntity getDiscSaleEntitySample() {
        DiscSaleEntity discSaleEntity = new DiscSaleEntity();
        discSaleEntity.setId(ID_DISC_SALE_ENTITY);
        discSaleEntity.setPrice_cashback(10);
        discSaleEntity.setPrice(20);
        discSaleEntity.setCashback(50);
        discSaleEntity.setDiscEntity(getDiscEntitySample());
        return discSaleEntity;
    }

    private DiscEntity getDiscEntitySample() {
        DiscEntity discEntity = new DiscEntity();
        discEntity.setId(ID_DISC_ENTITY);
        discEntity.setName("Disc1");
        discEntity.setPrice(20);
        discEntity.setGenreEntity(getGenreEntitySample());
        return discEntity;
    }

    private GenreEntity getGenreEntitySample() {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(ID_GENRE_ENTITY);
        genreEntity.setName("Genre1");
        return genreEntity;
    }

}