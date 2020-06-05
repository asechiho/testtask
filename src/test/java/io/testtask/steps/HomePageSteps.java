package io.testtask.steps;

import io.testtask.base.BasePageSteps;
import io.testtask.pages.HomePage;

public class HomePageSteps extends BasePageSteps<HomePage> {
    private static HomePageSteps instance;

    private HomePageSteps(HomePage homePage) {
        super(homePage);
    }

    public static synchronized HomePageSteps steps() {
        if (instance == null) {
            instance = new HomePageSteps(new HomePage());
        }
        return instance;
    }

    public void searchPackageByName(String packageName) {
        executeMethodsWithLogStep(String.format("Search the package '%s'", packageName), page -> {
            steps.assertPageIsDisplayed(page);
            steps.setValue(page.getSearchInput(), packageName);
            steps.click(page.getSearchButton());
        });
    }
}
