package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportRuntimeHints;
import test.hints.TestHints;
import test.properties.TestProperties;

@SpringBootApplication
@EnableConfigurationProperties({TestProperties.class})
@ImportRuntimeHints({TestHints.class})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
