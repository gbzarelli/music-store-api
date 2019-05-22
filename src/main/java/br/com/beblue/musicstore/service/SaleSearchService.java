package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.IllegalDateException;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.SaleEntity;
import br.com.beblue.musicstore.model.repository.SaleRepository;
import br.com.beblue.musicstore.util.converter.SaleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SaleSearchService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleSearchService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleResponseDTO getOrderByNumber(String orderNumber) throws NoValuePresentException {
        Optional<SaleEntity> optional = saleRepository.findByUuid(orderNumber);
        if (!optional.isPresent()) throw new NoValuePresentException("No sale with order number: " + orderNumber);
        SaleEntity saleEntity = optional.get();
        return SaleConverter.saleEntityToSaleResponseDTO(saleEntity);
    }

    public Page<SaleResponseDTO> getOrdersByDate(LocalDate startDate, LocalDate endDate, Pageable pageable) throws IllegalDateException {
        checkDates(startDate, endDate);
        Page<SaleEntity> entities = saleRepository.findAllBySaleDateTimeBetween(Date.valueOf(startDate), Date.valueOf(endDate), pageable);
        return SaleConverter.saleListEntityToSaleResponseList(entities);
    }

    private void checkDates(LocalDate startDate, LocalDate endDate) throws IllegalDateException {
        if (startDate.isAfter(endDate))
            throw new IllegalDateException("start date can't be greater than end date");
        if (endDate.isAfter(LocalDate.now()))
            throw new IllegalDateException("end date can't be greater than current date");
    }
}
