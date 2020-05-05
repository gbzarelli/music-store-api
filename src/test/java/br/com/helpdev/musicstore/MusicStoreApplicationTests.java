package br.com.helpdev.musicstore;

import br.com.helpdev.musicstore.util.ResourceConst;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(ResourceConst.ACTIVE_PROFILES_TEST_VALUE)
class MusicStoreApplicationTests {

    @Test
    @Autowired
    void contextLoads() {
        assert (true);
    }

}
