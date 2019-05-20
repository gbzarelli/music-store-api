package br.com.beblue.musicstore;

import br.com.beblue.musicstore.settings.DefaultAppConfigurationsTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static br.com.beblue.musicstore.util.ResourceConst.KEY_APPLICATION_ENV;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DefaultAppConfigurationsTests.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class ResourcesTest {

    @Value(KEY_APPLICATION_ENV)
    private String applicationEnv;

    @Test
    void check_application_test_is_loaded_with_success() {
        assertEquals(applicationEnv, ACTIVE_PROFILES_TEST_VALUE);
    }

}
