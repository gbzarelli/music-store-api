package br.com.beblue.musicstore.model.entity;

import javax.persistence.*;

@Entity(name = DiscSaleEntity.TABLE_NAME)
public class DiscSaleEntity {
    static final String TABLE_NAME = "discs_sales_tbl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_disc")
    private DiscEntity discEntity;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private SaleEntity saleEntity;

    private int cashback;
    private double price_cashback;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DiscEntity getDiscEntity() {
        return discEntity;
    }

    public void setDiscEntity(DiscEntity discEntity) {
        this.discEntity = discEntity;
    }

    public SaleEntity getSaleEntity() {
        return saleEntity;
    }

    public void setSaleEntity(SaleEntity saleEntity) {
        this.saleEntity = saleEntity;
    }

    public int getCashback() {
        return cashback;
    }

    public void setCashback(int cashback) {
        this.cashback = cashback;
    }

    public double getPrice_cashback() {
        return price_cashback;
    }

    public void setPrice_cashback(double price_cashback) {
        this.price_cashback = price_cashback;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DiscSaleEntity{" +
                "id=" + id +
                ", discEntity=" + discEntity +
                ", saleEntity=" + saleEntity +
                ", cashback=" + cashback +
                ", price_cashback=" + price_cashback +
                ", price=" + price +
                '}';
    }
}
