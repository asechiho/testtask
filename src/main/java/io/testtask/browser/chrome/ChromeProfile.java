package io.testtask.browser.chrome;

import io.testtask.utils.yaml.YamlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        options.addArguments(getArgs());
        options.setCapability(ChromeOptions.CAPABILITY, getOptions());
        getCapabilities().forEach(options::setCapability);
        return options;
    }
}
