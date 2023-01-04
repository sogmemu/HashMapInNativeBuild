package test.properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Validated
@Data
@Component
@ConfigurationProperties(prefix = "test-props")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestProperties {

    @NotEmpty
    HashMap<String, PermissionsConfig> datagroups;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PermissionsConfig {

        Permission read;
        Permission history;

        ArrayList<GenericFilterConfiguration> contentFilterConfiguration;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Permission {

        String code;
        String requiredCode;

        @Builder.Default
        boolean simulation = true;

        ArrayList<String> urlPattern;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class GenericFilterConfiguration {

        String filterName;
        ContentFilterType type;
        String targetPath;

        String filterPath;
        String valueCheck;
        ArrayList<TupleFilter> values;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class TupleFilter {
            String name;
            @Nullable
            String valueCheck;
            String filterPath;
        }
    }

    public enum ContentFilterType {
        SIMPLE,
        TUPLE;

        @JsonValue
        @Override
        public String toString() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static ContentFilterType fromString(String type) {
            return ContentFilterType.valueOf(type.toUpperCase());
        }
    }
}
