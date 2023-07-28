package com.test.playwright.UI;

import com.microsoft.playwright.Page;

public class Button {
    public static void clickButton(Page page, String selector) {
        page.locator(selector).all().get(0).click();
    }
    public static boolean buttonExists(Page page, String selector) {
        return page.isVisible(selector);
    }
}
