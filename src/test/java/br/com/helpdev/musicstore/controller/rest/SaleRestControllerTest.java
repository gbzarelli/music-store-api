package br.com.helpdev.musicstore.controller.rest;

import br.com.helpdev.musicstore.controller.dto.SaleRequestDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.service.SaleSearchService;
import br.com.helpdev.musicstore.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaleRestController.class)
class SaleRestControllerTest {

    private static final String ORDER_NUMBER = "4124154-4124GW-GE32GG3-3G3G";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SaleService saleService;

    @MockBean
    SaleSearchService saleSearchService;

    @Test
    void test_post_sale_method() throws Exception {
        SaleRequestDTO saleRequestDTO = new SaleRequestDTO();
        SaleResponseDTO saleResponseDTO = getSaleResponseTest();
        when(saleService.registerOrder(any())).thenReturn(saleResponseDTO);

        saleRequestDTO.setDiscsIds(Arrays.asList(1, 2));
        mockMvc.perform(post(SaleRestController.ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saleRequestDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(saleResponseDTO)));
    }


    @Test
    void test_get_sale_by_order_number_method() throws Exception {
        SaleResponseDTO saleResponseDTO = getSaleResponseTest();
        when(saleSearchService.getOrderByNumber(ORDER_NUMBER)).thenReturn(saleResponseDTO);
        mockMvc.perform(get(SaleRestController.ROOT_PATH + SaleRestController.PATH_BY_ORDER_NUMBER, ORDER_NUMBER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(saleResponseDTO)));
    }

    @Test
    void test_get_sales_by_date() throws Exception {
        String startDate = "2012-04-21";
        String endDate = "2012-04-22";
        mockMvc.perform(get(SaleRestController.ROOT_PATH + SaleRestController.PATH_BY_DATE, startDate, endDate))
                .andExpect(status().isOk());
    }

    private SaleResponseDTO getSaleResponseTest() {
        SaleResponseDTO saleResponseDTO = new SaleResponseDTO();
        saleResponseDTO.setCashbackPrice(1);
        saleResponseDTO.setTotalPrice(2);
        return saleResponseDTO;
    }
}
