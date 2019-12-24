package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.events.SaleNotifiable;
import br.com.beblue.musicstore.controller.dto.SaleRequestDTO;
import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.SaleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class SaleServiceTest {

    @Autowired
    SaleServiceImpl saleService;

    @MockBean
    DiscRepository discRepository;

    @MockBean
    SaleRepository saleRepository;

    @MockBean
    CashbackServiceImpl cashbackService;

    @MockBean
    SaleNotifiable saleNotifiable;

    @Test
    void test_no_value_present_exception_with_empty_list() {
        Assertions.assertThrows(NoValuePresentException.class, () -> {
            SaleRequestDTO saleRequestDTO = new SaleRequestDTO();
            saleService.registerOrder(saleRequestDTO);
        });
    }

    @Test
    void test_no_value_present_exception_without_discs_ids_in_database() {
        Assertions.assertThrows(NoValuePresentException.class, () -> {
            SaleRequestDTO saleRequestDTO = new SaleRequestDTO();
            saleRequestDTO.setDiscsIds(Arrays.asList(1, 2));
            saleService.registerOrder(saleRequestDTO);
        });
    }

    @Test
    void test_register_order_and_check_cashback() throws NoValuePresentException {
        List<Integer> discIds = Arrays.asList(1, 2);
        when(discRepository.findByIdIn(discIds)).thenReturn(getDiscsEntitiesSamples(discIds));
        when(cashbackService.getCashbacks()).thenReturn(getCashBackListSample());
        SaleRequestDTO request = new SaleRequestDTO();
        request.setDiscsIds(discIds);
        SaleResponseDTO response = saleService.registerOrder(request);
        assertEquals(request.getDiscsIds().size(), response.getProducts().size());
        assertEquals(response.getTotalPrice(), 20);
        assertEquals(response.getCashbackPrice(), 2);
    }

    @Test
    void test_no_value_present_in_database() throws NoValuePresentException {
        List<Integer> discIdsRepo = Arrays.asList(1, 2);
        List<Integer> discIds = Arrays.asList(1, 2, 3);
        when(discRepository.findByIdIn(discIds)).thenReturn(getDiscsEntitiesSamples(discIdsRepo));
        when(cashbackService.getCashbacks()).thenReturn(getCashBackListSample());
        SaleRequestDTO request = new SaleRequestDTO();
        request.setDiscsIds(discIds);
        assertThrows(NoValuePresentException.class, () -> saleService.registerOrder(request));
    }

    private List<GenreCashbackEntity> getCashBackListSample() {
        GenreCashbackEntity entity = new GenreCashbackEntity();
        entity.setCashback(10);
        entity.setEnable(true);
        entity.setGenreEntity(getGenreEntitySample());
        return Collections.singletonList(entity);
    }

    private List<DiscEntity> getDiscsEntitiesSamples(List<Integer> discIds) {
        return discIds.stream().map(this::getDiscEntity).collect(Collectors.toList());
    }

    private DiscEntity getDiscEntity(Integer id) {
        DiscEntity discEntity = new DiscEntity();
        discEntity.setId(id);
        discEntity.setName("Name-" + id);
        discEntity.setArtist("Artist-" + id);
        discEntity.setGenreEntity(getGenreEntitySample());
        discEntity.setPrice(10);
        return discEntity;
    }

    private GenreEntity getGenreEntitySample() {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1);
        return genreEntity;
    }

}