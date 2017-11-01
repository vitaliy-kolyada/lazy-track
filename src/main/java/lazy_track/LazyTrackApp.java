package lazy_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(scanBasePackages = {"lazy_track"})
// same as
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan combined
public class LazyTrackApp {
    public static void main(String[] args) {
        SpringApplication.run(LazyTrackApp.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
