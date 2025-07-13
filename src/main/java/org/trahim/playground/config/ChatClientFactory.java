package org.trahim.playground.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChatClientFactory {
    private final Map<String, ChatClient> clients;
    private final ChatClient defaultClient;

    public ChatClientFactory(
            @Qualifier("openAiChatClient") ChatClient openAi,
            @Qualifier("ollamaChatClient") ChatClient ollama
    ) {

        this.clients = Map.of(
                ChatClientConfig.ENGINE_OPENAI, openAi,
                ChatClientConfig.ENGINE_OLLAMA, ollama
        );
        this.defaultClient = ollama;
    }

    public ChatClient getClient(String engine) {
        return clients.getOrDefault(engine, defaultClient);
    }
}
