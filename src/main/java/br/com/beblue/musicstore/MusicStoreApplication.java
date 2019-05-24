package br.com.beblue.musicstore;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static br.com.beblue.musicstore.util.BeanConst.BEAN_SALE_QUEUE;
import static br.com.beblue.musicstore.util.ResourceConst.KEY_APPLICATION_MQ_QUEUE_SALE_NAME;

@SpringBootApplication
public class MusicStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicStoreApplication.class, args);
    }

    @Value(KEY_APPLICATION_MQ_QUEUE_SALE_NAME)
    private String queueSaleName;

    @Bean(BEAN_SALE_QUEUE)
    Queue getAmqQueue() {
        return new Queue(queueSaleName, true);
    }

}
