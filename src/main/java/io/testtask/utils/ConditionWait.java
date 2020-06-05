package io.testtask.utils;

import com.google.common.collect.ImmutableList;
import io.testtask.browser.BrowserContainer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

@Slf4j
public class ConditionWait {
    private static Waiter getWaiter(Duration conditionDuration, Duration pollingDuration) {
        return new Waiter(conditionDuration, pollingDuration);
    }

    public static void waitForTrue(Duration timeOut, Duration polling, Function<WebDriver, Boolean> condition) {
        until(timeOut, polling, condition);
    }

    public static void waitForTrue(Function<WebDriver, Boolean> condition) {
        until(Duration.ofMillis(seleniumConfig().conditionTimeoutMills()), Duration.ofMillis(seleniumConfig().pollingTimeoutMills()), condition);
    }

    private static <T> T until(Duration timeOut, Duration polling, Function<WebDriver, T> condition) {
        try {
            log.debug("Wait for function '{}' with timeout {} mills and polling {} mills", condition.toString(), timeOut.toMillis(), polling.toMillis());
            return getWaiter(timeOut, polling)
                    .until(condition);
        } catch (TimeoutException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    private static class Waiter extends FluentWait<WebDriver> {
        private Waiter(Duration timeout, Duration pollingInterval) {
            super(BrowserContainer.getWebDriver());
            withTimeout(timeout);
            pollingEvery(pollingInterval);
            ignoreAll(ImmutableList.of(StaleElementReferenceException.class, NullPointerException.class, ScriptTimeoutException.class));
        }
    }
}
