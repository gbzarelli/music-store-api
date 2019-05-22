package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.dto.SaleResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleSearchService {
    public SaleResponseDTO getOrderByNumber(String orderNumber) {
        return null;
    }

    public List<SaleResponseDTO> getOrdersByDate(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return null;
    }
}
