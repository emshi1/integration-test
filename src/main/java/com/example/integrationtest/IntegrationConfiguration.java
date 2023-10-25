package com.example.integrationtest;

import org.springframework.context.annotation.*;
import org.springframework.integration.dsl.*;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.*;
import org.springframework.scheduling.support.CronTrigger;

/**
 * created by MakarovaVV on 25.10.2023
 */
@Configuration
public class IntegrationConfiguration {

    @Bean
    public MessageChannel dstChannel() {
        return MessageChannels.queue("dstChannel").getObject();
    }

    @Bean
    public IntegrationFlow sourceFlow() {
        return IntegrationFlow.from(new MsgSource(),
                e -> e.poller(Pollers.trigger(new CronTrigger("*/3 * * * * *"))).id("source-detector"))
            .split()
            .channel("dstChannel")
            .get();
    }

    @Bean
    public IntegrationFlow dstFlow() {
        return IntegrationFlow.from("dstChannel")
            .log(LoggingHandler.Level.INFO, "", "payload")
            .handle(Message::getHeaders)
            .get();
    }
}
