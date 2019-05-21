package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.DiscSaleEntity;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import br.com.beblue.musicstore.model.entity.SaleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.beblue.musicstore.util.DiscsGenerator.generateDiscsByGenres;
import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = GenreRepository.class)
@EntityScan(basePackageClasses = GenreEntity.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class SaleRepositoryTest {

    private static final int QTD_DISC_SAVE = 5;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    DiscRepository discRepository;

    @Test
    void test_insert_sale() {
        generateDiscsByGenres(genreRepository, discRepository);
        SaleEntity saleEntity = getSaleEntity();
        List<DiscSaleEntity> listDiscEntities = getDiscSaleEntities(saleEntity);
        saleEntity.setDiscSaleEntities(listDiscEntities);
        int id = persistEntities(saleEntity);
        assertInsertSaleEntity(id, saleEntity);
    }

    private int persistEntities(SaleEntity saleEntity) {
        return saleRepository.save(saleEntity).getId();
    }

    private void assertInsertSaleEntity(int id, SaleEntity persistedObject) {
        SaleEntity entity = saleRepository.findById(id).get();
        assertEquals(entity.getUuid(), persistedObject.getUuid());
        assertEquals(entity.getDiscSaleEntities().size(), QTD_DISC_SAVE);
        entity.getDiscSaleEntities().forEach(discSale -> assertEquals(discSale.getSaleEntity().getUuid(), entity.getUuid()));
    }

    private List<DiscSaleEntity> getDiscSaleEntities(SaleEntity saleEntity) {
        List<DiscSaleEntity> listDiscEntities = new ArrayList<>();
        for (int i = 1; i <= QTD_DISC_SAVE; i++) {
            DiscSaleEntity discSaleEntity = new DiscSaleEntity();
            DiscEntity discEntity = new DiscEntity();
            discEntity.setId(i);
            discSaleEntity.setDiscEntity(discEntity);
            discSaleEntity.setCashback(10);
            discSaleEntity.setPrice_cashback(100);
            discSaleEntity.setSaleEntity(saleEntity);
            listDiscEntities.add(discSaleEntity);
        }
        return listDiscEntities;
    }

    private SaleEntity getSaleEntity() {
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setTotal_price(200);
        saleEntity.setCashback_price(100);
        saleEntity.setUuid(UUID.randomUUID().toString());
        return saleEntity;
    }

}