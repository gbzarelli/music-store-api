package br.com.beblue.musicstore.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = SaleEntity.TABLE_NAME)
public class SaleEntity {
    static final String TABLE_NAME = "sales_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private double total_price;
    private double cashback_price;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sale")
    private List<DiscSaleEntity> discSaleEntities;

    private Date date_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getCashback_price() {
        return cashback_price;
    }

    public void setCashback_price(double cashback_price) {
        this.cashback_price = cashback_price;
    }

    public List<DiscSaleEntity> getDiscSaleEntities() {
        return discSaleEntities;
    }

    public void setDiscSaleEntities(List<DiscSaleEntity> discSaleEntities) {
        this.discSaleEntities = discSaleEntities;
    }

    public void addDiscSaleEntity(DiscSaleEntity discSaleEntity) {
        if (discSaleEntities == null) discSaleEntities = new ArrayList<>();
        discSaleEntities.add(discSaleEntity);
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }
}
