package br.com.helpdev.musicstore;

import br.com.helpdev.musicstore.settings.DefaultAppConfigurationsTests;
import br.com.helpdev.musicstore.util.ResourceConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DefaultAppConfigurationsTests.class)
@ActiveProfiles(ResourceConst.ACTIVE_PROFILES_TEST_VALUE)
class ResourcesTest {

    @Value(ResourceConst.KEY_APPLICATION_ENV)
    private String applicationEnv;

    @Test
    void check_application_test_is_loaded_with_success() {
        Assertions.assertEquals(applicationEnv, ResourceConst.ACTIVE_PROFILES_TEST_VALUE);
    }

}
