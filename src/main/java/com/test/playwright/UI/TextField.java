package com.test.playwright.UI;

import com.microsoft.playwright.Page;

public class TextField {
    public static String selectText(Page page, String selector) {
        return page.locator(selector).innerText();
    }
}
