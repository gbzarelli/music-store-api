package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.controller.dto.SaleRequestDTO;
import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.IllegalDateException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface SaleController {

    SaleResponseDTO registerOrder(SaleRequestDTO request) throws NoValuePresentException;

    SaleResponseDTO getOrderByOrderNumber(String orderNumber) throws NoValuePresentException;

    Page<SaleResponseDTO> getOrdersByDateFilter(LocalDate startDate, LocalDate endDate, Pageable pageable) throws IllegalDateException;
}
