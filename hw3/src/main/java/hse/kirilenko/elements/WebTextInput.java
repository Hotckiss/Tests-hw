package hse.kirilenko.elements;

import org.openqa.selenium.WebElement;

public class WebTextInput {
    private final WebElement element;

    public WebTextInput(WebElement element) {
        this.element = element;
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
    }
}