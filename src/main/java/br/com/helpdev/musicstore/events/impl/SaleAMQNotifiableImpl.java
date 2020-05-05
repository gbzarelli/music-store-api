package br.com.helpdev.musicstore.events.impl;

import br.com.helpdev.musicstore.events.SaleNotifiable;
import br.com.helpdev.musicstore.util.BeansConst;
import br.com.helpdev.musicstore.util.ResourceConst;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class SaleAMQNotifiableImpl implements SaleNotifiable {

    @Value(ResourceConst.KEY_APPLICATION_MQ_SALE_ROUTING_KEY)
    private String exchangeRoutingKey;

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    @Autowired
    SaleAMQNotifiableImpl(final RabbitTemplate rabbitTemplate,
                          final @Qualifier(BeansConst.BEAN_SALE_EXCHANGE) TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void notifyNewOrder(final String order) throws AmqpException {
        rabbitTemplate.convertAndSend(topicExchange.getName(), exchangeRoutingKey, order);
    }

}
