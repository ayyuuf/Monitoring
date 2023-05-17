package backend.sitemanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"backend.sitemanagment"})
public class SitemanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitemanagmentApplication.class, args);
	}

}
