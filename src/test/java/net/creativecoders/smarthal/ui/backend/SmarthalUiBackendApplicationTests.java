package net.creativecoders.smarthal.ui.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.assertj.core.api.WithAssertions;

@SpringBootTest
class SmarthalUiBackendApplicationTests implements WithAssertions {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

}
