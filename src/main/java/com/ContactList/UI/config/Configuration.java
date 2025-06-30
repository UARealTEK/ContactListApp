package com.ContactList.UI.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:allure.properties", "classpath:config.properties"})
public interface Configuration extends Config {

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
