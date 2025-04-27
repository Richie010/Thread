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
        return new RequestContextListener();
    }


}
