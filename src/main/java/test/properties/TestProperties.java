package test.properties;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;

@Validated
@ConfigurationProperties(prefix = "test-props")
public class TestProperties {

    @NotEmpty
    private HashMap<String, PermissionsConfig> datagroups = new HashMap<>();

    public HashMap<String, PermissionsConfig> getDatagroups() {
        return datagroups;
    }

    public void setDatagroups(HashMap<String, PermissionsConfig> datagroups) {
        this.datagroups = datagroups;
    }

    public static class PermissionsConfig {

        private Permission read;
        private Permission history;

        public Permission getRead() {
            return read;
        }

        public Permission getHistory() {
            return history;
        }

        public void setRead(Permission read) {
            this.read = read;
        }

        public void setHistory(Permission history) {
            this.history = history;
        }
    }

    public static class Permission {
        private  String code;
        private String requiredCode;
        private boolean simulation = true;
        private ArrayList<String> urlPattern;


        public String getRequiredCode() {
            return requiredCode;
        }

        public String getCode() {
            return code;
        }

        public ArrayList<String> getUrlPattern() {
            return urlPattern;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setRequiredCode(String requiredCode) {
            this.requiredCode = requiredCode;
        }

        public boolean isSimulation() {
            return simulation;
        }

        public void setSimulation(boolean simulation) {
            this.simulation = simulation;
        }

        public void setUrlPattern(ArrayList<String> urlPattern) {
            this.urlPattern = urlPattern;
        }
    }
}
