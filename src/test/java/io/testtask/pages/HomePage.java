package io.testtask.pages;

import io.testtask.annotations.PageInfo;
import io.testtask.base.BasePage;
import io.testtask.elements.ElementHandler;
import org.openqa.selenium.By;

import static java.lang.String.format;

@PageInfo(css = ".Hero", pageName = "Page: GoDev Main Page")
public class HomePage extends BasePage {

    public ElementHandler getNavigationButton(String buttonName) {
        return new ElementHandler(By.xpath(format("//*[contains(@class, 'Header-menu')]/*[text() = '%s']", buttonName)), "Navigation button: " + buttonName);
    }
}
