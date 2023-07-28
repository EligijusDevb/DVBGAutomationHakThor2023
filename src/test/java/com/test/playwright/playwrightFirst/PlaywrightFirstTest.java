package com.test.playwright.playwrightFirst;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.test.playwright.task.task2.Task2;
import com.test.playwright.UI.*;
import com.test.playwright.task.task4.ColorButton;
import com.test.playwright.task.task4.Task4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.test.playwright.UI.Button.buttonExists;

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

    @Test
    void task2_test() throws InterruptedException {
        page.navigate("https://www.mathster.com/10secondsmaths/");
        Task2.checkActionCheckBoxes(page);
        while (true) {
            String question = TextField.selectText(page, "//p[@id='question']");
            Integer answer = Task2.calculateAnswer(question);
            Task2.submitAnswer(page, answer.toString());
            if (buttonExists(page, "//p[@id='start-quiz']")) {
                break;
            }
            Keyboard.clickKeys(page, "Enter");
        }
        System.out.println("end");
    }

    @Test
    void task4_test() throws InterruptedException {
        page.navigate("https://weslleyaraujo.github.io/react-simon-says/");
        Task4.start(page);
        String initialRedClass = page.locator("//div[@id='red']").getAttribute("class");
        String initialGreenClass = page.locator("//div[@id='green']").getAttribute("class");
        String initialYellowClass = page.locator("//div[@id='yellow']").getAttribute("class");
        String initialBlueClass = page.locator("//div[@id='blue']").getAttribute("class");
        List<ColorButton> buttons = new ArrayList<>();
        ColorButton currentColor = null;
        int iterationsNotChanged = 0;
        while (true) {
            String currentRed = page.locator("//div[@id='red']").getAttribute("class");
            String currentGreen = page.locator("//div[@id='green']").getAttribute("class");
            String currentYellow = page.locator("//div[@id='yellow']").getAttribute("class");
            String currentBlue = page.locator("//div[@id='blue']").getAttribute("class");
            if (!currentRed.equals(initialRedClass)) {
                if (currentColor != ColorButton.RED) {
                    buttons.add(ColorButton.RED);
                    currentColor = ColorButton.RED;
                }
                System.out.println("Red Changed");
            } else if (!currentGreen.equals(initialGreenClass)) {
                if (currentColor != ColorButton.GREEN) {
                    buttons.add(ColorButton.GREEN);
                    currentColor = ColorButton.GREEN;
                }
                System.out.println("Green Changed");
            } else if (!currentYellow.equals(initialYellowClass)) {
                if (currentColor != ColorButton.YELLOW) {
                    buttons.add(ColorButton.YELLOW);
                    currentColor = ColorButton.YELLOW;
                }
                System.out.println("Yellow Changed");
            } else if (!currentBlue.equals(initialBlueClass)) {
                if (currentColor != ColorButton.BLUE) {
                    buttons.add(ColorButton.BLUE);
                    currentColor = ColorButton.BLUE;
                }
                System.out.println("Blue Changed");
            } else {
                currentColor = null;
                iterationsNotChanged += 1;
            }

            if (iterationsNotChanged >= 200) {
                Task4.clickButtons(page, buttons);
                buttons = new ArrayList<>();
                iterationsNotChanged = 0;
                Thread.sleep(100);
            } else {
                Thread.sleep(10);
            }

        }

        //System.out.println("end");
    }
}
