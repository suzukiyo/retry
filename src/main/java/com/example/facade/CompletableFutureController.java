package com.example.facade;

import com.github.rholder.retry.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class CompletableFutureController {

    @RequestMapping("/completable")
    public String example() throws InterruptedException, ExecutionException, TimeoutException {

        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.exponentialWait(100, 5, TimeUnit.MINUTES))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return retryer.call(main());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (RetryException e) {
                e.printStackTrace();
            }
            return null;
        });
        return completableFuture.get(10, TimeUnit.SECONDS);
    }

    private Callable<String> main() {
        System.out.println("---start---");
        Integer test = new Integer(100);
        return () -> test.toString();
    }

}
