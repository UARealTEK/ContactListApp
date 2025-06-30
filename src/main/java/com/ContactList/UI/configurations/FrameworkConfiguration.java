package com.ContactList.UI.configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:allure.properties", "classpath:config.properties"})
public interface FrameworkConfiguration extends Config {

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("base.url")
    String baseURL();

    String browser();

    boolean headless();

    double timeout();

    @Key("slow.motion")
    int slowMo();
}
