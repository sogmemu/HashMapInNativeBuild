package test.properties;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;

@Validated
@Data
@ConfigurationProperties(prefix = "test-props")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestProperties {

    @NotEmpty
    HashMap<String, PermissionsConfig> datagroups;


    // Referenze this classes also here, will create needed hints
    //PermissionsConfig testConfig;
    //Permission testPermission;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PermissionsConfig {

        Permission read;
        Permission history;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Permission {
        String code;
        String requiredCode;
        boolean simulation = true;
        ArrayList<String> urlPattern;

    }
}
