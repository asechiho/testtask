package io.testtask.base;

import io.testtask.annotations.PageInfo;
import io.testtask.elements.ElementHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

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
