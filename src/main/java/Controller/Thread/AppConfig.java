package Controller.Thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AppConfig {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener(); // Listens for the request context
    }

    // Custom Thread Pool Executor for Async Tasks
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Minimum number of threads
        executor.setMaxPoolSize(10); // Maximum number of threads
        executor.setQueueCapacity(25); // Capacity of the queue before new threads are created
        executor.setThreadNamePrefix("Async-Thread-"); // Prefix for thread names
        executor.initialize(); // Initializes the executor
        return executor;
    }
}
