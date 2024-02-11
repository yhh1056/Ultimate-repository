package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class ColdSequenceExample {

    public static void main(String[] args) {
        Flux<String> coldFLux = Flux.fromIterable(Arrays.asList("hi", "hello", "hoho"))
                .map(String::toUpperCase);
        coldFLux.subscribe(country -> log.info("# Subscriber1: {}", country));
        log.info("-------------------");
        coldFLux.subscribe(country -> log.info("# Subscriber2: {}", country));
    }
}
