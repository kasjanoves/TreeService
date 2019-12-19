package treeservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "treeservice")
public class SpringBootWebApplication {

    public static void main(String[] args) {
	SpringApplication.run(SpringBootWebApplication.class, args);
    }

}
