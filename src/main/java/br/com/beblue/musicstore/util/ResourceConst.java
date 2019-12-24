package br.com.beblue.musicstore.util;

public class ResourceConst {
    private ResourceConst() {
    }

    public static final String ACTIVE_PROFILES_TEST_VALUE = "test";
    public static final String KEY_SPOTIFY_CLIENT_ID = "${spotify.client.id}";
    public static final String KEY_SPOTIFY_CLIENT_SECRET = "${spotify.client.secret}";
    public static final String KEY_APPLICATION_ENV = "${application.environment}";
    public static final String KEY_APPLICATION_MQ_SALE_QUEUE_NAME = "${application.mq.sale.queue.name}";
    public static final String KEY_APPLICATION_MQ_SALE_EXCHANGE_NAME = "${application.mq.sale.exchange.name}";
    public static final String KEY_APPLICATION_MQ_SALE_ROUTING_KEY = "${application.mq.sale.exchange.routing-key}";
}
