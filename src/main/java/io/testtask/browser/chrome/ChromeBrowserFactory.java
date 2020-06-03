package io.testtask.browser.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.testtask.browser.IBrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowserFactory implements IBrowserFactory {

    @Override
    public WebDriver getWebDriver() {
        ChromeProfile chromeProfile = ChromeProfile.downloadProfileFromYaml();
        return getLocalWebDriver(chromeProfile);
    }

    private WebDriver getLocalWebDriver(ChromeProfile profile) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(profile.chromeOptions());
    }
}