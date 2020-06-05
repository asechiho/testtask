package io.testtask.steps;

import io.testtask.base.BasePageSteps;
import io.testtask.browser.BrowserContainer;
import io.testtask.pages.SearchResultsPage;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class SearchResultsPageSteps extends BasePageSteps<SearchResultsPage> {
    private static SearchResultsPageSteps instance;

    private SearchResultsPageSteps(SearchResultsPage page) {
        super(page);
    }

    public static synchronized SearchResultsPageSteps steps() {
        if (instance == null) {
            instance = new SearchResultsPageSteps(new SearchResultsPage());
        }
        return instance;
    }

    public void openPackage(String fullPackageName) {
        executeMethodsWithLogStep(String.format("Open the package '%s'", fullPackageName), page -> {
            steps.assertPageIsDisplayed(page);
            steps.click(page.getPackageLink(fullPackageName));
        });
    }

    public void findPackage(String fullPackageName) {
        executeMethodsWithLogStep(String.format("Find the package '%s' in the results", fullPackageName), page -> findPackage(page, fullPackageName));
    }

    private void findPackage(SearchResultsPage page, String fullPackageName) {
        steps.assertPageIsDisplayed(page);
        while (!page.getPackageLink(fullPackageName).isPresent(Duration.ofSeconds(5))) {
            steps.click(page.getPaginationNextButton());
            steps.waitForElementsAreLoaded(page.getPackageLinks(), BrowserContainer.getWebDriver());
        }
    }
}
