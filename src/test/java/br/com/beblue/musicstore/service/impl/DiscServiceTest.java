package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.settings.DiscsAppConfigurationsTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;

@SpringBootTest(classes = DiscsAppConfigurationsTests.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class DiscServiceTest {

    @Autowired
    DiscServiceImpl discService;


}
