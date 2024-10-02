package com.example.demo.controller;

import com.example.demo.modal.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AiController {

      ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "Give me names of five cities in US") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse().getResult().getOutput().getContent();
    }

    @GetMapping("/chatlist")
    public List<String> getchat(@RequestParam(value = "message", defaultValue = "Give me names of five cities in US") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    @GetMapping("/chatmap")
    public Map<String,String> getResponseAsMap(@RequestParam(value = "message", defaultValue = "give me the names of 5 country and their capitals ?") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>() {});

    }

    @GetMapping("/director-movie-service")
    public List<Movie> getDirectorMovieService(@RequestParam(value = "directorName", defaultValue = "Steven Spielberg") String director) {
        String template = """
                "Generate a list of movies directed by {directorName}. If the director is not found, return null.
                Each movie should have a title and a release year. {format}"
                """;

        List<Movie> movieList = chatClient.prompt()
                .user(userSpec -> userSpec.text(template)
                        .param("directorName", director)
                        .param("format", "json"))
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {});
        return movieList;

    }
}
