package br.com.helpdev.musicstore.service;

import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.exception.IllegalDateException;
import br.com.helpdev.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface SaleSearchService {
    SaleResponseDTO getOrderByNumber(String orderNumber) throws NoValuePresentException;

    Page<SaleResponseDTO> getOrdersByDate(LocalDate startDate, LocalDate endDate, Pageable pageable) throws IllegalDateException;
}
