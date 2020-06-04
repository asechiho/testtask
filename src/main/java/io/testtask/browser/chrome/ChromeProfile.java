package io.testtask.browser.chrome;

import io.testtask.utils.yaml.YamlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@AllArgsConstructor
@ToString(of = {"name"})
class ChromeProfile {
    @Getter
    private final String name;
    @Getter
    private final List<String> args;
    @Getter
    private final Map<String, Object> capabilities;
    @Getter
    private final Map<String, Object> options;

    private ChromeProfile() {
        this("chrome", Collections.emptyList(), Collections.emptyMap(), Collections.emptyMap());
    }

    static ChromeProfile downloadProfileFromYaml() {
        return YamlReader.readYamlConfig("browsers/chrome.yaml", ChromeProfile.class);
    }

    ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        executeIfValueNotNull(getArgs(), options::addArguments);
        executeIfValueNotNull(getCapabilities(), caps -> caps.forEach(options::setCapability));
        options.setCapability(ChromeOptions.CAPABILITY, getOptions());
        return options;
    }

    private <T> void executeIfValueNotNull(List<T> value, Consumer<List<T>> lambda) {
        if (value != null) {
            lambda.accept(value);
        }
    }

    private <T> void executeIfValueNotNull(Map<String, Object> value, Consumer<Map<String, Object>> lambda) {
        if (value != null) {
            lambda.accept(value);
        }
    }
}
