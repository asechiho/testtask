package io.testtask.tests.first;

import io.testtask.base.BaseTest;
import io.testtask.pages.HomePage;
import io.testtask.utils.Assert;
import io.testtask.ElementSteps;
import org.testng.annotations.Test;

public class NavBarTest extends BaseTest {

    @Test
    public void checkNavBar() {
        HomePage homePage = new HomePage();
        ElementSteps steps = ElementSteps.steps();
        steps.assertPageIsDisplayed(homePage);
        steps.assertElementIsDisplayed(homePage.getNavigationButton("Why Go"));
        steps.assertElementIsDisplayed(homePage.getNavigationButton("Getting Started"));
        steps.assertElementIsDisplayed(homePage.getNavigationButton("Discover Packages"));
        steps.assertElementIsDisplayed(homePage.getNavigationButton("About"));
        Assert.assertAll();
    }
}
