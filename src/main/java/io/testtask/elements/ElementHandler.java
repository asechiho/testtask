package io.testtask.elements;

import io.testtask.elements.interfaces.Settable;
import org.openqa.selenium.By;

public class ElementHandler extends BaseElement implements Settable {
    public ElementHandler(By locator, String name) {
        super(locator, name, ElementState.EXISTS);
    }

    @Override
    public void setValue(String value) {
        findElement().clear();
        findElement().sendKeys(value);
    }
}
