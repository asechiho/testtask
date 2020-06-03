package io.testtask.browser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

/**
 * The static class works with WebDriver.
 * WebDriver is thread safe value (ThreadLocal). One thread = one WebDriver
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BrowserContainer {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_CONTAINER = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        if (WEB_DRIVER_CONTAINER.get() != null) {
            return WEB_DRIVER_CONTAINER.get();
        }
        throw new IllegalStateException(logBrowserHasNotStarted());
    }

    public static void open(String url) {
        synchronized (BrowserContainer.class) {
            WebDriver webDriver = Browsers.getFactory().getWebDriver();
            WEB_DRIVER_CONTAINER.set(webDriver);
        }
        getWebDriver().get(url);
    }

    public static void open() {
        open(seleniumConfig().startUrl());
    }

    public static void close() {
        if (hasWebDriverStarted()) {
            getWebDriver().quit();
        }
    }

    public static boolean hasWebDriverStarted() {
        return getWebDriver() != null && ((RemoteWebDriver) getWebDriver()).getSessionId() != null;
    }

    private static String logBrowserHasNotStarted() {
        return String.format("WebDriver has not started for browser: %s", seleniumConfig().browser());
    }
}
