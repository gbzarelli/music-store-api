package br.com.beblue.musicstore.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceConst {
    public final String ACTIVE_PROFILES_TEST_VALUE = "test";
    public final String KEY_SPOTIFY_CLIENT_ID = "${spotify.client.id}";
    public final String KEY_SPOTIFY_CLIENT_SECRET = "${spotify.client.secret}";
    public final String KEY_APPLICATION_ENV = "${application.environment}";
    public final String KEY_APPLICATION_MQ_SALE_QUEUE_NAME = "${application.mq.sale.queue.name}";
    public final String KEY_APPLICATION_MQ_SALE_EXCHANGE_NAME = "${application.mq.sale.exchange.name}";
    public final String KEY_APPLICATION_MQ_SALE_ROUTING_KEY = "${application.mq.sale.exchange.routing-key}";
}
