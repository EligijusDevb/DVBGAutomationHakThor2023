package com.test.playwright.task.task4;

import com.microsoft.playwright.Page;
import com.test.playwright.UI.Button;

import java.util.List;

import static com.test.playwright.UI.Button.clickButton;

public class Task4 {

    public static void start(Page page) {
        clickButton(page, "//a[@href='/board']");
    }
    public static void clickButtons(Page page, List<ColorButton> colorButtons) {
        colorButtons.forEach(colorButton -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            if (colorButton == ColorButton.RED) {
                Button.clickButton(page, "//div[@id='red']");
            } else if (colorButton == ColorButton.BLUE) {
                Button.clickButton(page, "//div[@id='blue']");
            } else if (colorButton == ColorButton.GREEN) {
                Button.clickButton(page, "//div[@id='green']");
            } else if (colorButton == ColorButton.YELLOW) {
                Button.clickButton(page, "//div[@id='yellow']");
            }
        });
    }
}
