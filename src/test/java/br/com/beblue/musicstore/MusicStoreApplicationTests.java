package br.com.beblue.musicstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;

@SpringBootTest
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class MusicStoreApplicationTests {

    @Test
    @Autowired
    void contextLoads() {
        assert (true);
    }

}
