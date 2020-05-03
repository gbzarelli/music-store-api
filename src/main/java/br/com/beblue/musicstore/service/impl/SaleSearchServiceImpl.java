package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.IllegalDateException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.repository.SaleRepository;
import br.com.beblue.musicstore.service.SaleSearchService;
import br.com.beblue.musicstore.util.mapper.SaleMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class SaleSearchServiceImpl implements SaleSearchService {

    private final SaleRepository saleRepository;

    public SaleResponseDTO getOrderByNumber(final String orderNumber) throws NoValuePresentException {
        final var optional = saleRepository.findByUuid(orderNumber);
        if (optional.isEmpty()) throw new NoValuePresentException("No sale with order number: " + orderNumber);
        final var saleEntity = optional.get();
        return SaleMapper.saleEntityToSaleResponseDTO(saleEntity);
    }

    public Page<SaleResponseDTO> getOrdersByDate(final LocalDate startDate, final LocalDate endDate, final Pageable pageable) throws IllegalDateException {
        checkDates(startDate, endDate);
        final var entities = saleRepository.findAllBySaleDateTimeBetween(Date.valueOf(startDate), Date.valueOf(endDate), pageable);
        return SaleMapper.saleListEntityToSaleResponseList(entities);
    }

    private void checkDates(final LocalDate startDate, final LocalDate endDate) throws IllegalDateException {
        if (startDate.isAfter(endDate))
            throw new IllegalDateException("start date can't be greater than end date");
        if (endDate.isAfter(LocalDate.now()))
            throw new IllegalDateException("end date can't be greater than current date");
    }
}
