package io.testtask.base;

import io.testtask.browser.BrowserContainer;
import io.testtask.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@Slf4j
public abstract class BaseTest {

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        BrowserContainer.open();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(ITestContext testContext) {
        try {
            BrowserContainer.close();
        } finally {
            Assert.clear();
        }
    }
}
