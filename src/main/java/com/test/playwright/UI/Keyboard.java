package com.test.playwright.UI;

import com.microsoft.playwright.Page;

public class Keyboard {
    public static void clickKeys(Page page, String keys) {
        page.keyboard().press(keys);
    }
}
