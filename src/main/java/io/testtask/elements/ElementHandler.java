package io.testtask.elements;

import org.openqa.selenium.By;

public class ElementHandler extends BaseElement {
    public ElementHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }
}
