package io.testtask.base;

import io.testtask.annotations.PageInfo;
import io.testtask.elements.ElementHandler;
import io.testtask.utils.ConditionWait;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.NoSuchElementException;

import static io.testtask.configuration.SeleniumConfig.seleniumConfig;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BasePage {
    protected final By locator;
    protected final String pageName;

    protected BasePage() {
        PageInfo pageInfo = this.getClass().getAnnotation(PageInfo.class);
        this.pageName = pageInfo.pageName();
        this.locator = this.getLocatorFromPageInfo(pageInfo);
    }

    private By getLocatorFromPageInfo(PageInfo pageInfo) {
        log.debug("Get page locator from {}", pageInfo.toString());
        if (!"".equals(pageInfo.xpath())) {
            return By.xpath(pageInfo.xpath());
        } else if (!"".equals(pageInfo.id())) {
            return By.id(pageInfo.id());
        } else if (!"".equals(pageInfo.css())) {
            return By.cssSelector(pageInfo.css());
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean isPagePresent(Duration timeout) {
        ConditionWait.waitForTrue(driver -> (boolean) ((RemoteWebDriver) driver).executeScript("return document.readyState == 'complete'"));
        return new ElementHandler(locator, pageName).isPresent(timeout);
    }

    public boolean isPagePresent() {
        return isPagePresent(Duration.ofMillis(seleniumConfig().conditionTimeoutMills()));
    }

    @Override
    public String toString() {
        return pageName;
    }
}
