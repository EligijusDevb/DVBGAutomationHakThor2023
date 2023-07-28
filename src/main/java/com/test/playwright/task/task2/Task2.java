package com.test.playwright.task.task2;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.test.playwright.UI.Input;
import com.test.playwright.CustomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.test.playwright.UI.Button.clickButton;

public class Task2 {
    public static void checkActionCheckBoxes(Page page) {
        BoundingBox box = page.locator("//div[@class='noUi-handle noUi-handle-lower']").boundingBox();
        page.mouse().move(box.x + box.width / 2, box.y + box.height / 2);
        page.mouse().down();
        BoundingBox box2 = page.locator("//div[@class='noUi-base']").boundingBox();
        page.mouse().move(box2.x + box2.width + 10, box2.y + box2.height / 2);
        page.mouse().up();
        clickButton(page, "//input[@value='mul']");
        clickButton(page, "//input[@value='div']");
        clickButton(page, "//input[@value='sub']");
        clickButton(page, "//input[@value='pow']");
        clickButton(page, "//input[@value='sqrt']");
    }
    public static Integer calculateAnswer(String expression) {
        List<String> numberActionList = CustomStringUtils.splitString(expression, " ");
        return calculate(numberActionList);
    }

    public static void submitAnswer(Page page, String answer) {
        String selector = "//input[@id='question-answer']";
        Input.addText(page, selector, answer);
    }

    public static int calculate(List<String> numberActionList) {
        Actions nextAction = null;
        int number = 0;
        if (numberActionList.size() == 1) {
            if (numberActionList.get(0).contains("³")) {
                String numberString = StringUtils.substring(numberActionList.get(0), 0, numberActionList.get(0).length() - 1);
                Integer intNumber = Integer.valueOf(numberString);
                number = new Double(Math.pow(intNumber, 3)).intValue();
            } else if (numberActionList.get(0).contains("²")) {
                String numberString = StringUtils.substring(numberActionList.get(0), 0, numberActionList.get(0).length() - 1);
                Integer intNumber = Integer.valueOf(numberString);
                number = new Double(Math.pow(intNumber, 2)).intValue();
            } else if(numberActionList.get(0).contains("√")) {
                String numberString = StringUtils.substring(numberActionList.get(0), 1, numberActionList.get(0).length());
                Integer intNumber = Integer.valueOf(numberString);
                number = new Double(Math.sqrt(intNumber)).intValue();
            }
            System.out.println(number);
        } else {
            for (int i = 0; i < numberActionList.size(); i++) {
                if (numberActionList.get(i).equals("+")) {
                    nextAction = Actions.ADDITION;
                } else if (numberActionList.get(i).equals("-")) {
                    nextAction = Actions.SUBSTRACTION;
                } else if (numberActionList.get(i).equals("×")) {
                    nextAction = Actions.MULTIPLICATION;
                } else if (numberActionList.get(i).equals("÷")) {
                    nextAction = Actions.DIVISION;
                } else {
                    int currentNumber = Integer.valueOf(numberActionList.get(i));
                    if (nextAction == Actions.ADDITION) {
                        number = number + currentNumber;
                    } else if (nextAction == Actions.SUBSTRACTION) {
                        number = number - currentNumber;
                    } else if (nextAction == Actions.MULTIPLICATION) {
                        number = number * currentNumber;
                    } else if (nextAction == Actions.DIVISION) {
                        number = number / currentNumber;
                    } else {
                        number = number + currentNumber;
                    }
                }
            }
        }
        return number;
    }


}
