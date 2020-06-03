package io.testtask.elements;

import io.testtask.browser.BrowserContainer;
import io.testtask.elements.interfaces.Visible;
import io.testtask.utils.ConditionWait;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseElement implements Visible {
    protected final By locator;
    protected final String name;
    protected final ElementState state;

    @Override
    public boolean isPresent(Duration duration) {
        return !findElements(duration, ElementState.VISIBLE).isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Element['%s'] with locator: '%s'", this.name, this.locator.toString());
    }

    protected List<WebElement> findElements(Duration duration, ElementState state) {
        return findElements(duration, locator, state);
    }

    protected List<WebElement> findElements(Duration duration, By locator, ElementState state) {
        WebDriver driver = BrowserContainer.getWebDriver();
        List<WebElement> elementsResult = new ArrayList<>();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        ConditionWait.waitForTrue(duration, Duration.ofMillis(seleniumConfig().pollingTimeoutMills()),
                webDriver -> tryToFindElements(webDriver, locator, elementsResult, state));
        return elementsResult;
    }

    protected boolean tryToFindElements(WebDriver webDriver, By locator, List<WebElement> resultElements, ElementState state) {
        List<WebElement> elements = webDriver.findElements(locator);
        if (elements.isEmpty()) {
            return false;
        }
        resultElements.addAll(elements.stream().filter(state.getPredicate()).collect(Collectors.toList()));
        return true;
    }
}
