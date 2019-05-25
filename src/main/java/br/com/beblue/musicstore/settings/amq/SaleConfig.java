package br.com.beblue.musicstore.settings.amq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import static br.com.beblue.musicstore.util.BeanConst.BEAN_SALE_QUEUE;
import static br.com.beblue.musicstore.util.BeanConst.BEAN_SALE_EXCHANGE;
import static br.com.beblue.musicstore.util.ResourceConst.*;

@Configuration
public class SaleConfig {

    @Value(KEY_APPLICATION_MQ_SALE_QUEUE_NAME)
    private String queueName;

    @Value(KEY_APPLICATION_MQ_SALE_EXCHANGE_NAME)
    private String exchangeName;

    @Value(KEY_APPLICATION_MQ_SALE_ROUTING_KEY)
    private String exchangeRoutingKey;

    @Bean(BEAN_SALE_QUEUE)
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean(BEAN_SALE_EXCHANGE)
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    @DependsOn({BEAN_SALE_QUEUE, BEAN_SALE_EXCHANGE})
    Binding binding(@Qualifier(BEAN_SALE_QUEUE) Queue queue, @Qualifier(BEAN_SALE_EXCHANGE) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(exchangeRoutingKey);
    }

}
