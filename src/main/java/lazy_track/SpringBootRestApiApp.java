package lazy_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"lazy_track"})
// same as
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan combined
public class SpringBootRestApiApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
