package br.com.beblue.musicstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class MusicStoreApplicationTests {


    @Test
    @Autowired
    void contextLoads() {
        assert (true);
    }

}
