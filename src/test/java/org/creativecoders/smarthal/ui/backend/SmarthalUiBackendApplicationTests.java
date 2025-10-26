package org.creativecoders.smarthal.ui.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SmarthalUiBackendApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }

}
