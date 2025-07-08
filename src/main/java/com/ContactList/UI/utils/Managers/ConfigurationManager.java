package com.ContactList.UI.utils.Managers;

import com.ContactList.UI.configurations.FrameworkConfiguration;
import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    public static FrameworkConfiguration config() {
        return ConfigCache.getOrCreate(FrameworkConfiguration.class);
    }
}
