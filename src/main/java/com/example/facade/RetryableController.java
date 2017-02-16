package com.example.facade;

import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class RetryableController {

    @RequestMapping("/retryable")
    @Retryable(interceptor = "retryAdvice")
    public String facade() throws InterruptedException, ExecutionException, TimeoutException {
        return execute();
    }

    private String execute() throws TimeoutException, ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit( () -> main() );
        return future.get(5000, TimeUnit.MILLISECONDS);
    }

    private String main() {
        System.out.println("---start---");
        Integer test = new Integer(100);
        return test.toString();
    }

}
