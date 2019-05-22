package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.dto.SaleRequestDTO;
import br.com.beblue.musicstore.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.IllegalDateException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.service.SaleSearchService;
import br.com.beblue.musicstore.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static br.com.beblue.musicstore.controller.SaleController.ROOT_PATH;

@RestController
@RequestMapping(ROOT_PATH)
public class SaleController {
    static final String ROOT_PATH = "/sale";
    static final String PATH_BY_ORDER_NUMBER = "/{orderNumber}";
    static final String PATH_BY_DATE = "/start_date/{startDate}/end_date/{endDate}";

    private final SaleService saleService;
    private final SaleSearchService saleSearchService;

    @Autowired
    public SaleController(SaleService saleService, SaleSearchService saleSearchService) {
        this.saleService = saleService;
        this.saleSearchService = saleSearchService;
    }

    @PostMapping()
    public SaleResponseDTO postOrder(@RequestBody SaleRequestDTO request) throws NoValuePresentException {
        return saleService.registerOrder(request);
    }

    @GetMapping(value = PATH_BY_ORDER_NUMBER)
    public SaleResponseDTO getOrder(@PathVariable String orderNumber) throws NoValuePresentException {
        return saleSearchService.getOrderByNumber(orderNumber);
    }

    @GetMapping(value = PATH_BY_DATE)
    public Page<SaleResponseDTO> getOrders(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate startDate,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate endDate,
            Pageable pageable) throws IllegalDateException {
        return saleSearchService.getOrdersByDate(startDate, endDate, pageable);
    }
}
