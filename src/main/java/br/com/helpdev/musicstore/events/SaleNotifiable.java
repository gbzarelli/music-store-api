package br.com.helpdev.musicstore.events;

import org.springframework.amqp.AmqpException;

public interface SaleNotifiable {
    void notifyNewOrder(final String order) throws AmqpException;
}
