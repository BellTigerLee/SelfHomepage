package ugps.myweb.gpsinside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GpsInsideApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpsInsideApplication.class, args);
	}

}
