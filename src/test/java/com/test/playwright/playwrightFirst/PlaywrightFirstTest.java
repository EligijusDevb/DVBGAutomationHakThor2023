package com.test.playwright.playwrightFirst;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.test.playwright.UI.BrowserComponent;
import com.test.playwright.UI.Button;
import com.test.playwright.UI.Input;
import com.test.playwright.UI.Keyboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaywrightFirstTest {
    private Playwright playwright;
    private Page page;

    @BeforeEach
    void setup() {
        playwright = Playwright.create();
        page = BrowserComponent.initializeBrowser(playwright);
    }

    @Test
    void clickButton_test() {
        page.navigate("https://www-archive.mozilla.org/projects/ui/accessibility/unix/testcase/html/#Button_Test_Cases");
        Button.clickButton(page, "//button[@name='B1']");
        System.out.println("end");
    }

    @Test
    void typeText_test() {
        page.navigate("https://www-archive.mozilla.org/projects/ui/accessibility/unix/testcase/html/#Button_Test_Cases");
        Input.addText(page, "//input[@type='text' and @name='test']", "new text");
    }

    @Test
    void clickKeys_test() {
        page.navigate("https://www-archive.mozilla.org/projects/ui/accessibility/unix/testcase/html/#Button_Test_Cases");
        Button.clickButton(page, "//button[@name='B1']");
        Keyboard.clickKeys(page, "Control+A");
    }
}
