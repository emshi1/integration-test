package com.example.integrationtest;

import org.springframework.integration.endpoint.AbstractMessageSource;
import org.springframework.integration.support.*;

import java.util.Set;

/**
 * created by MakarovaVV on 25.10.2023
 */
public class MsgSource extends AbstractMessageSource<String> {

    public MsgSource() {
    }

    @Override
    protected AbstractIntegrationMessageBuilder<Set<String>> doReceive() {
        return MessageBuilder.withPayload(Set.of("ok", "warn"));
    }

    @Override
    public String getComponentType() {
        return "msg-source";
    }
}
