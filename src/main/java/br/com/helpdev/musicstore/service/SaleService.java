package br.com.helpdev.musicstore.service;

import br.com.helpdev.musicstore.controller.dto.SaleRequestDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.exception.NoValuePresentException;

public interface SaleService {
    SaleResponseDTO registerOrder(SaleRequestDTO request) throws NoValuePresentException;
}
