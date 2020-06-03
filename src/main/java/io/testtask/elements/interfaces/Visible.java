package io.testtask.elements.interfaces;

import java.time.Duration;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

public interface Visible {
    boolean isPresent(Duration duration);

    default boolean isPresent() {
        return isPresent(Duration.ofMillis(seleniumConfig().conditionTimeoutMills()));
    }
}
