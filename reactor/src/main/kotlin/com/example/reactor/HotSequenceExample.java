package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

import static java.lang.Thread.sleep;

@Slf4j
public class HotSequenceExample {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> hotFlux = Flux.fromIterable(Arrays.asList("hi", "hello", "hoho"))
                .map(String::toUpperCase)
                .delayElements(Duration.ofSeconds(1)).share();
        hotFlux.subscribe(country -> log.info("# Subscriber1: {}", country));

        sleep(1500);

        hotFlux.subscribe(country -> log.info("# Subscriber2: {}", country));

        sleep(2000);
    }
}
