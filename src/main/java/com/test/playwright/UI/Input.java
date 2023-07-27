package com.test.playwright.UI;

import com.microsoft.playwright.Page;

public class Input {
    public static void addText(Page page, String selector, String text) {
        page.locator(selector).all().get(0).fill(text);
    }
}
