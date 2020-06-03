package io.testtask.elements;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.Predicate;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ElementState {
    EXISTS(Objects::nonNull),
    VISIBLE(WebElement::isDisplayed);

    @Getter
    private final Predicate<WebElement> predicate;
}
