package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.controller.dto.SaleRequestDTO;
import br.com.helpdev.musicstore.controller.dto.SaleResponseDTO;
import br.com.helpdev.musicstore.events.SaleNotifiable;
import br.com.helpdev.musicstore.exception.NoValuePresentException;
import br.com.helpdev.musicstore.model.entity.DiscEntity;
import br.com.helpdev.musicstore.model.entity.DiscSaleEntity;
import br.com.helpdev.musicstore.model.entity.GenreCashbackEntity;
import br.com.helpdev.musicstore.model.entity.SaleEntity;
import br.com.helpdev.musicstore.model.repository.DiscRepository;
import br.com.helpdev.musicstore.model.repository.SaleRepository;
import br.com.helpdev.musicstore.service.CashbackService;
import br.com.helpdev.musicstore.service.SaleService;
import br.com.helpdev.musicstore.util.mapper.SaleMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final DiscRepository discRepository;
    private final CashbackService cashbackService;
    private final SaleNotifiable saleNotifiable;

    public SaleResponseDTO registerOrder(SaleRequestDTO item) throws NoValuePresentException {

        final var entities = validateAndGetEntities(item);
        final var cashbackMap = genreCashbackEntitiesToMap(cashbackService.getCashBacks());

        final var saleEntity = new SaleEntity();
        saleEntity.setUuid(UUID.randomUUID().toString());

        entities.forEach(entity -> {
            final var cashback = cashbackMap.get(entity.getGenreEntity().getId());
            final var cashbackPrice = getCashbackPrice(entity.getPrice(), cashback);
            final var discSaleEntity = new DiscSaleEntity();
            discSaleEntity.setSaleEntity(saleEntity);
            discSaleEntity.setCashback(cashback);
            discSaleEntity.setPrice(entity.getPrice());
            discSaleEntity.setDiscEntity(entity);
            discSaleEntity.setPrice_cashback(cashbackPrice);
            saleEntity.setTotal_price(saleEntity.getTotal_price() + entity.getPrice());
            saleEntity.setCashback_price(saleEntity.getCashback_price() + cashbackPrice);
            saleEntity.addDiscSaleEntity(discSaleEntity);
        });

        persist(saleEntity);
        notifyMessageQueue(saleEntity);

        return SaleMapper.saleEntityToSaleResponseDTO(saleEntity);
    }

    private void notifyMessageQueue(SaleEntity saleEntity) {
        // Caso falte comunicação ou de erro de persistencia, o sistema não
        //deve parar. Posteriormente tratar o Log de erro
        try {
            saleNotifiable.notifyNewOrder(saleEntity.getUuid());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void persist(SaleEntity saleEntity) {
        saleRepository.save(saleEntity);
    }

    private double getCashbackPrice(double price, Integer cashback) {
        return (price * cashback) / 100;
    }

    private Map<Integer, Integer> genreCashbackEntitiesToMap(List<GenreCashbackEntity> cashbackList) {
        final var map = new HashMap<Integer, Integer>();
        cashbackList.forEach(item -> map.put(item.getGenreEntity().getId(), item.getCashback()));
        return map;
    }

    private List<DiscEntity> validateAndGetEntities(SaleRequestDTO items) throws NoValuePresentException {
        checkInput(items);
        final var discEntities = findDiscs(items);
        checkNoPresentIds(items, discEntities);
        return discEntities;
    }

    private void checkInput(SaleRequestDTO request) throws NoValuePresentException {
        if (request.getDiscsIds() == null || request.getDiscsIds().isEmpty())
            throw new NoValuePresentException("Empty discs id");
    }

    private List<DiscEntity> findDiscs(SaleRequestDTO request) {
        return discRepository.findByIdIn(request.getDiscsIds());
    }

    private void checkNoPresentIds(SaleRequestDTO items, List<DiscEntity> discEntities) throws NoValuePresentException {
        if (items.getDiscsIds().size() == discEntities.size()) return;
        final var noPresentValues = discEntities.stream()
                .filter(item -> items.getDiscsIds().stream().anyMatch(id -> id == item.getId()))
                .collect(Collectors.toList());
        throw new NoValuePresentException("Discs not available: " + noPresentValues.toString());
    }
}
