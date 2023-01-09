package test.runner;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import test.properties.TestProperties;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TestRunner implements ApplicationRunner {

    TestProperties testProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (testProperties.getDatagroups() == null) {
            throw new RuntimeException("property not binded");
        }

        log.info("booelan {} ", testProperties.getDatagroups().get("data").getRead().isSimulation());

        log.info("property {}", testProperties);

    }
}
