package com.ContactList.UI.configurations;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

public enum BrowsersConfiguration {

    CHROMIUM {
        @Override
        public Browser createBrowser(Playwright playwright) {
            return playwright.chromium().launch(getOptions());
        }
    },

    FIREFOX {
      @Override
      public Browser createBrowser(Playwright playwright) {
          return playwright.firefox().launch(getOptions());
      }
    },

    WEBKIT {
      @Override
      public Browser createBrowser(Playwright playwright) {
          return playwright.webkit().launch(getOptions());
      }
    };


    public abstract Browser createBrowser(Playwright playwright);

    private static BrowserType.LaunchOptions getOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(config().headless())
                .setSlowMo(config().slowMo());
    }
}
