package io.testtask.configuration;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("selenium.properties")
public interface SeleniumConfig {
    static SeleniumConfig seleniumConfig() {
        return PropertyLoader.newInstance().populate(SeleniumConfig.class);
    }

    @Property("browser")
    String browser();

    @Property("start.url")
    String startUrl();

    @Property("condition.timeout.mills")
    long conditionTimeoutMills();

    @Property("polling.timeout.mills")
    long pollingTimeoutMills();
}
