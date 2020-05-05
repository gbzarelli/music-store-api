package br.com.helpdev.musicstore.controller.rest;

import br.com.helpdev.musicstore.controller.SaleController;
import br.com.helpdev.musicstore.controller.dto.SaleRequestDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.exception.IllegalDateException;
import br.com.helpdev.musicstore.exception.NoValuePresentException;
import br.com.helpdev.musicstore.service.SaleSearchService;
import br.com.helpdev.musicstore.service.SaleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(SaleRestController.ROOT_PATH)
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class SaleRestController implements SaleController {

    static final String ROOT_PATH = "/sale";
    static final String PATH_BY_ORDER_NUMBER = "/{orderNumber}";
    static final String PATH_BY_DATE = "/start_date/{startDate}/end_date/{endDate}";

    private final SaleService saleService;
    private final SaleSearchService saleSearchService;

    @PostMapping()
    public SaleResponseDTO registerOrder(@RequestBody final SaleRequestDTO request) throws NoValuePresentException {
        return saleService.registerOrder(request);
    }

    @GetMapping(value = PATH_BY_ORDER_NUMBER)
    public SaleResponseDTO getOrderByOrderNumber(@PathVariable final String orderNumber) throws NoValuePresentException {
        return saleSearchService.getOrderByNumber(orderNumber);
    }

    @GetMapping(value = PATH_BY_DATE)
    public Page<SaleResponseDTO> getOrdersByDateFilter(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
            final Pageable pageable) throws IllegalDateException {
        return saleSearchService.getOrdersByDate(startDate, endDate, pageable);
    }
}
