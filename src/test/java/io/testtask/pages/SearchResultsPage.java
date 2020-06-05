package io.testtask.pages;

import io.testtask.annotations.PageInfo;
import io.testtask.base.BasePage;
import io.testtask.elements.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".SearchResults", pageName = "Page: Search Results")
public class SearchResultsPage extends BasePage {

    public ElementHandler getPaginationNextButton() {
        return new ElementHandler(By.cssSelector("[class*=resultCount] .Pagination-next"), "Pagination next button");
    }

    public ElementHandler getPackageLink(String packageName) {
        return new ElementHandler(By.xpath(String.format("//*[contains(@class, 'SearchSnippet')]//a[text() = '%s']/..", packageName)),
                "Link " + packageName);
    }

    public ElementHandler getPackageLinks() {
        return new ElementHandler(By.cssSelector(".SearchSnippet a"),"Packages links");
    }
}
