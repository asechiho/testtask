package io.testtask.browser;

import com.google.common.collect.ImmutableMap;
import io.testtask.browser.chrome.ChromeBrowserFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Browsers {
    private static final Map<String, IBrowserFactory> BROWSER_FACTORY = ImmutableMap.of("chrome", new ChromeBrowserFactory());

    public static IBrowserFactory getFactory() {
        IBrowserFactory factory = BROWSER_FACTORY.get(seleniumConfig().browser());
        return factory == null ? new ChromeBrowserFactory() : factory;
    }
}
