package io.testtask.steps;

import io.testtask.base.BasePageSteps;
import io.testtask.models.PackageInfo;
import io.testtask.pages.PackageInfoPage;
import io.testtask.utils.Assert;

import java.util.Collection;

public class PackageInfoPageSteps extends BasePageSteps<PackageInfoPage> {
    private static PackageInfoPageSteps instance;

    private PackageInfoPageSteps(PackageInfoPage page) {
        super(page);
    }

    public static synchronized PackageInfoPageSteps steps() {
        if (instance == null) {
            instance = new PackageInfoPageSteps(new PackageInfoPage());
        }
        return instance;
    }

    public void checkPackageInfo(PackageInfo expectedPackageInfo) {
        executeMethodsWithLogStep(String.format("Check package info equals '%s'", expectedPackageInfo), page -> {
            steps.assertPageIsDisplayed(page);
            String published = steps.getText(page.getPublishedInfoLabel());
            String licenses = steps.getText(page.getLicensesInfoLabel());
            String module = steps.getText(page.getModuleInfoLabel());
            Assert.getAssert().getSoftAssert().assertEquals(new PackageInfo(published, licenses, module), expectedPackageInfo);
        });
    }

    public void checkTabsAreDisplayed(Collection<String> tabNames) {
        executeMethodsWithLogStep(String.format("Check Tabs '%s' Are Displayed", tabNames), page -> {
            steps.assertPageIsDisplayed(page);
            tabNames.forEach(tab -> steps.assertElementIsDisplayed(page.getDetailsTabButton(tab)));
        });
    }
}
