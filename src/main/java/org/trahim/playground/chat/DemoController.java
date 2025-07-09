package org.trahim.playground.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.trahim.playground.config.ChatClientConfig.ENGINE_OLLAMA;
import static org.trahim.playground.config.ChatClientConfig.ENGINE_OPENAI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class DemoController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;


    @GetMapping("/ai")
    public String chat(@RequestParam String input,
                       @RequestParam(defaultValue = ENGINE_OLLAMA) String engine) {
        ChatClient client = resolveClient(engine);
        return client.prompt()
                .user(input)
                .call()
                .content();
    }

    private ChatClient resolveClient(String engine) {
        return switch (engine) {
            case ENGINE_OLLAMA -> ollamaChatClient;
            case ENGINE_OPENAI -> openAiChatClient;
            default -> {
                log.warn("Unknown engine '{}', falling back to default: {}", engine, ENGINE_OLLAMA);
                yield ollamaChatClient;
            }
        };
    }
}
