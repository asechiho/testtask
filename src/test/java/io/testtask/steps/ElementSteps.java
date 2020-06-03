package io.testtask.steps;

import io.testtask.base.BasePage;
import io.testtask.elements.BaseElement;
import io.testtask.elements.interfaces.Visible;
import io.testtask.utils.Assert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElementSteps {
    private static final String LOG_STEP_MESSAGE = "STE: %s by element[%s]";
    private static ElementSteps instance;

    public static synchronized ElementSteps steps() {
        if (instance == null) {
            instance = new ElementSteps();
        }
        return instance;
    }

    public void assertPageIsDisplayed(BasePage page) {
        executeMethodWithLogStep("Assert Page Is Displayed", page,
                basePage -> Assert.getAssert().getSoftAssert().assertTrue(basePage.isPagePresent(), String.format("Assert fail: %s is not displayed", basePage))
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
}
