package io.testtask;

import io.testtask.base.BasePage;
import io.testtask.browser.BrowserContainer;
import io.testtask.elements.BaseElement;
import io.testtask.elements.interfaces.Clickable;
import io.testtask.elements.interfaces.Settable;
import io.testtask.elements.interfaces.Visible;
import io.testtask.utils.Assert;
import io.testtask.utils.ConditionWait;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElementSteps {
    private static final String LOG_STEP_MESSAGE = "STEP: %s by %s";
    private static ElementSteps instance;

    public static synchronized ElementSteps steps() {
        if (instance == null) {
            instance = new ElementSteps();
        }
        return instance;
    }

    public <T extends BaseElement> void waitForElementsAreLoaded(T element, WebDriver webDriver) {
        AtomicInteger index = new AtomicInteger(3);
        AtomicInteger count = new AtomicInteger(element.count());
        ConditionWait.waitForTrue(driver -> {
            BrowserContainer.setWebDriver(webDriver);
            if (count.get() == element.count() && index.decrementAndGet() == 0) return true;
            else count.set(element.count());
            return false;
        });
    }

    public <T extends BaseElement & Clickable> void click(T element) {
        executeMethodWithLogStep("Click", element, Clickable::click);
    }

    public <T extends BaseElement & Settable> void setValue(T element, String value) {
        executeMethodWithLogStep("Set Value - " + value, element, el -> el.setValue(value));
    }

    public <T extends BaseElement> String getText(T element) {
        return executeFunctionWithLogStep("Get Text", element, BaseElement::getText);
    }

    public void assertPageIsDisplayed(BasePage page) {
        executeMethodWithLogStep("Assert Page Is Displayed", page,
                basePage -> Assert.getAssert().getHardAssert().assertTrue(basePage.isPagePresent(), String.format("Assert fail: %s is not displayed", basePage))
        );
    }

    public <T extends BaseElement & Visible> void assertElementIsDisplayed(T visible) {
        executeMethodWithLogStep("Assert Element Is Displayed", visible,
                element -> Assert.getAssert().getSoftAssert().assertTrue(element.isPresent(), String.format("Assert fail: %s is not displayed", element))
        );
    }

    private <T> void executeMethodWithLogStep(String message, T element, Consumer<T> consumer) {
        log.info(String.format(LOG_STEP_MESSAGE, message, element));
        consumer.accept(element);
    }

    private <T, R> R executeFunctionWithLogStep(String message, T element, Function<T, R> function) {
        log.info(String.format(LOG_STEP_MESSAGE, message, element));
        return function.apply(element);
    }
}
