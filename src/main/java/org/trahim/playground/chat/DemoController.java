package org.trahim.playground.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trahim.playground.config.ChatClientFactory;

import static org.trahim.playground.config.ChatClientConfig.ENGINE_OLLAMA;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class DemoController {

    private final ChatClientFactory chatClientFactory;


    @GetMapping("/ai")
    public String chat(@RequestParam String input,
                       @RequestParam(defaultValue = ENGINE_OLLAMA) String engine) {
        ChatClient client = chatClientFactory.getClient(engine);
        return client.prompt()
                .user(input)
                .call()
                .content();
    }
}
