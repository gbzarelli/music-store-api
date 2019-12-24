package br.com.beblue.musicstore.events.impl;

import br.com.beblue.musicstore.events.SaleNotifiable;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static br.com.beblue.musicstore.util.BeansConst.BEAN_SALE_EXCHANGE;
import static br.com.beblue.musicstore.util.ResourceConst.KEY_APPLICATION_MQ_SALE_ROUTING_KEY;

@Component
class SaleAMQNotifiableImpl implements SaleNotifiable {

    @Value(KEY_APPLICATION_MQ_SALE_ROUTING_KEY)
    private String exchangeRoutingKey;

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    @Autowired
    SaleAMQNotifiableImpl(final RabbitTemplate rabbitTemplate,
                          final @Qualifier(BEAN_SALE_EXCHANGE) TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void notifyNewOrder(String order) throws AmqpException {
        rabbitTemplate.convertAndSend(topicExchange.getName(), exchangeRoutingKey, order);
    }

}
