package io.testtask.pages;

import io.testtask.annotations.PageInfo;
import io.testtask.base.BasePage;
import io.testtask.elements.ElementHandler;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static java.lang.String.format;

@PageInfo(css = ".DetailsContent", pageName = "Page: Package Info")
public class PackageInfoPage extends BasePage {

    public ElementHandler getDetailsTabButton(String tabName) {
        return new ElementHandler(By.xpath(format("//*[contains(@class, 'DetailsNav-tab')]/*[@role = 'tab' and normalize-space(text()) = '%s']", tabName)),
                format("Details tab '%s' button", tabName));
    }

    public ElementHandler getPublishedInfoLabel() {
        return getDetailsHeaderInfo("Published:");
    }

    public ElementHandler getLicensesInfoLabel() {
        return getDetailsHeaderInfo("Licenses:");
    }

    public ElementHandler getModuleInfoLabel() {
        return getDetailsHeaderInfo("Module:");
    }

    private ElementHandler getDetailsHeaderInfo(String title) {
        return new ElementHandler(By.xpath(format("//*[contains(@class, 'infoLabelTitle') and  normalize-space(text()) = '%s']/following-sibling::*[1]", title)),
                StringUtils.substring(title, 0, title.length() - 1) + "info label");
    }
}
