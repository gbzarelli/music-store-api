package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.events.SaleNotifiable;
import br.com.beblue.musicstore.controller.dto.SaleRequestDTO;
import br.com.beblue.musicstore.controller.dto.SaleResponseDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.DiscSaleEntity;
import br.com.beblue.musicstore.model.entity.GenreCashbackEntity;
import br.com.beblue.musicstore.model.entity.SaleEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.SaleRepository;
import br.com.beblue.musicstore.service.CashbackService;
import br.com.beblue.musicstore.service.SaleService;
import br.com.beblue.musicstore.util.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final DiscRepository discRepository;
    private final CashbackService cashbackService;
    private final SaleNotifiable saleNotifiable;

    @Autowired
    SaleServiceImpl(final SaleRepository saleRepository,
                    final DiscRepository discRepository,
                    final CashbackService cashbackService,
                    final SaleNotifiable saleNotifiable) {
        this.saleRepository = saleRepository;
        this.discRepository = discRepository;
        this.cashbackService = cashbackService;
        this.saleNotifiable = saleNotifiable;
    }

    public SaleResponseDTO registerOrder(SaleRequestDTO item) throws NoValuePresentException {

        List<DiscEntity> entities = validateAndGetEntities(item);
        Map<Integer, Integer> cashbackMap = genreCashbackEntitiesToMap(cashbackService.getCashbacks());

        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setUuid(UUID.randomUUID().toString());

        entities.forEach(entity -> {
            Integer cashback = cashbackMap.get(entity.getGenreEntity().getId());
            double cashbackPrice = getCashbackPrice(entity.getPrice(), cashback);
            DiscSaleEntity discSaleEntity = new DiscSaleEntity();
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
        Map<Integer, Integer> map = new HashMap<>();
        cashbackList.forEach(item -> map.put(item.getGenreEntity().getId(), item.getCashback()));
        return map;
    }

    private List<DiscEntity> validateAndGetEntities(SaleRequestDTO items) throws NoValuePresentException {
        checkInput(items);
        List<DiscEntity> discEntities = findDiscs(items);
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
        List<Integer> noPresentValues = new ArrayList<>();
        discEntities.forEach(discEntity -> {
            List<Integer> l = items.getDiscsIds();
            if (l.stream().anyMatch(integer -> integer == discEntity.getId())) {
                noPresentValues.add(discEntity.getId());
            }
        });
        throw new NoValuePresentException("Discs not available: " + noPresentValues.toString());
    }
}