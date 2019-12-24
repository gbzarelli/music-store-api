package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.controller.dto.SaleRequestDTO;
import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;

public interface SaleService {
    SaleResponseDTO registerOrder(SaleRequestDTO request) throws NoValuePresentException;
}
