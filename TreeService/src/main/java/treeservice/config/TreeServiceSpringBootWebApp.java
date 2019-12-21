package treeservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "treeservice")
@EntityScan(basePackages = { "treeservice.domain" })
public class TreeServiceSpringBootWebApp {

    public static void main(String[] args) {
	SpringApplication.run(TreeServiceSpringBootWebApp.class, args);
    }

}
