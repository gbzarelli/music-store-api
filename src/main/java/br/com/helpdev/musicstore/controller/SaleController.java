package br.com.helpdev.musicstore.controller;

import br.com.helpdev.musicstore.controller.dto.SaleRequestDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.exception.IllegalDateException;
import br.com.helpdev.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface SaleController {

    SaleResponseDTO registerOrder(final SaleRequestDTO request) throws NoValuePresentException;

    SaleResponseDTO getOrderByOrderNumber(final String orderNumber) throws NoValuePresentException;

    Page<SaleResponseDTO> getOrdersByDateFilter(final LocalDate startDate, final LocalDate endDate, final Pageable pageable) throws IllegalDateException;
}
