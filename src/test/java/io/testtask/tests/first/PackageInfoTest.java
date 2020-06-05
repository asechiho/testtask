package io.testtask.tests.first;

import io.testtask.base.BaseTest;
import io.testtask.models.PackageInfo;
import io.testtask.steps.HomePageSteps;
import io.testtask.steps.PackageInfoPageSteps;
import io.testtask.steps.SearchResultsPageSteps;
import io.testtask.utils.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.Collection;

public class PackageInfoTest extends BaseTest {

    @Test
    public void checkProtocGenPackageInfo() {
        PackageInfo packageInfo = new PackageInfo("Apr 13, 2020", "None detected not legal advice",
                "github.com/mikekonan/protoc-gen-setter");
        Collection<String> packageAvailableTabs = Lists.newArrayList("Doc", "Overview", "Subdirectories", "Versions", "Imports", "Imported By", "Licenses");

        HomePageSteps.steps().searchPackageByName("setter");
        SearchResultsPageSteps.steps().findPackage(packageInfo.getModule());
        SearchResultsPageSteps.steps().openPackage(packageInfo.getModule());
        PackageInfoPageSteps.steps().checkTabsAreDisplayed(packageAvailableTabs);
        PackageInfoPageSteps.steps().checkPackageInfo(packageInfo);
        Assert.assertAll();
    }
}
