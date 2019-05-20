package br.com.beblue.musicstore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * CREATE TABLE sales_tbl
 * (
 * id             INT          NOT NULL AUTO_INCREMENT,
 * uuid           varchar(255) NOT NULL,
 * price          DOUBLE       NOT NULL,
 * price_cashback DOUBLE       NOT NULL,
 * date_time      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * PRIMARY KEY (id),
 * UNIQUE KEY UK_sales_name (uuid)
 * ) ENGINE = InnoDB
 * DEFAULT CHARSET = utf8;
 */
@Entity(name = SaleEntity.TABLE_NAME)
public class SaleEntity {
    static final String TABLE_NAME = "sales_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private double price;
    private double price_cashback;
    private Date date_time;
}
