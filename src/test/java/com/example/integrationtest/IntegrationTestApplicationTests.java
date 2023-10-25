package com.example.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.test.context.*;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SpringIntegrationTest(noAutoStartup = {"source-detector"})
class IntegrationTestApplicationTests {

    @Autowired
    private MockIntegrationContext context;

    @Autowired
    private PollableChannel dstChannel;


    @Test
    void contextLoads() {
        MessageSource<Set<String>> messageSource = () -> new GenericMessage<>(Set.of("foo", "bar", "baz"));
        context.substituteMessageSourceFor("source-detector", messageSource);
        assertNotNull(dstChannel.receive(4000));
    }

}
