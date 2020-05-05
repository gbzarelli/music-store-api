package br.com.helpdev.musicstore.settings;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class DefaultAppConfigurationsTests {

    @MockBean
    ApplicationStartup applicationStartup;

}
