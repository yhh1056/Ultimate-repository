package com.example.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static java.lang.Thread.sleep;

@Slf4j
public class BackpressureExample {

    public static void main(String[] args) throws InterruptedException {
//        backpressure();

        // 버퍼에 가득 찰 경우 예외
//        backpressureOnError();

        // 버퍼에 가득 찰 경우 먼저 대기하는 emit 된 데이터 Drop
//        backpressureOnErrorDrop();

        // 버퍼에 가득 찰 경우 최근에 emit 된 데이터 앞의 데이터 삭제
        backpressureOnErrorLatest();
    }

    private static void backpressure() {
        Flux.range(1, 5)
                .doOnNext(it -> log.info("doOnNext {}", it.toString()))
                .doOnRequest(value -> log.info("doOnRequest {}", value))
                .doOnComplete(()-> log.info("끝"))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(@NotNull Subscription subscription) {
                        request(1);
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(@NotNull Integer value) {
                        sleep(5000);
                        log.info("hookOnNext value {}", value);
                        request(1);
                    }
                });
    }

    private static void backpressureOnError() throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(it -> log.info("emit {}", it.toString()))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                sleep(5);
                                log.info("처리중 {}", data);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        error -> log.error(error.getMessage(), error)
                );
        sleep(3000);
    }

    private static void backpressureOnErrorDrop() throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop(drop -> log.info("drop data {}", drop))
                .doOnNext(it -> log.info("emit {}", it.toString()))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                sleep(5);
                                log.info("처리중 {}", data);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        error -> log.error(error.getMessage(), error)
                );
        sleep(3000);
    }

    private static void backpressureOnErrorLatest() throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
                .doOnNext(it -> log.info("emit {}", it.toString()))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                sleep(5);
                                log.info("처리중 {}", data);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        error -> log.error(error.getMessage(), error)
                );
        sleep(3000);
    }
}
