package br.com.helpdev.musicstore.settings.amq;

import br.com.helpdev.musicstore.util.BeansConst;
import br.com.helpdev.musicstore.util.ResourceConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
class SaleConfig {

    @Value(ResourceConst.KEY_APPLICATION_MQ_SALE_QUEUE_NAME)
    private String queueName;

    @Value(ResourceConst.KEY_APPLICATION_MQ_SALE_EXCHANGE_NAME)
    private String exchangeName;

    @Value(ResourceConst.KEY_APPLICATION_MQ_SALE_ROUTING_KEY)
    private String exchangeRoutingKey;

    @Bean(BeansConst.BEAN_SALE_QUEUE)
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean(BeansConst.BEAN_SALE_EXCHANGE)
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    @DependsOn({BeansConst.BEAN_SALE_QUEUE, BeansConst.BEAN_SALE_EXCHANGE})
    Binding binding(@Qualifier(BeansConst.BEAN_SALE_QUEUE) Queue queue, @Qualifier(BeansConst.BEAN_SALE_EXCHANGE) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(exchangeRoutingKey);
    }

}
