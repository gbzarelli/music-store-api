package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.settings.DiscsAppConfigurationsTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.helpdev.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;

@SpringBootTest(classes = DiscsAppConfigurationsTests.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class DiscServiceTest {

    @Autowired
    DiscServiceImpl discService;


}
