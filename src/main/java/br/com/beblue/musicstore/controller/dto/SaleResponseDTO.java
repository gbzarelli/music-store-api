package br.com.beblue.musicstore.controller.dto;

import java.util.List;

public class SaleResponseDTO {

    private String orderNumber;
    private double totalPrice;
    private double cashbackPrice;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    private List<DiscDTO> products;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCashbackPrice() {
        return cashbackPrice;
    }

    public void setCashbackPrice(double cashbackPrice) {
        this.cashbackPrice = cashbackPrice;
    }

    public List<DiscDTO> getProducts() {
        return products;
    }

    public void setProducts(List<DiscDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "SaleResponseDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", cashbackPrice=" + cashbackPrice +
                ", products=" + products +
                '}';
    }
}
