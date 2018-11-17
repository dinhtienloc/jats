package vn.locdt.jats.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "vn.locdt.jats")
public class NewMain {
    public static void main(String[] args) {
        SpringApplication.run(NewMain.class, args);
    }
}
