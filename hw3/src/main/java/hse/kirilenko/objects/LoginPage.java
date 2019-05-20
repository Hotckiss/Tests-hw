package hse.kirilenko.objects;

import hse.kirilenko.elements.WebButton;
import hse.kirilenko.elements.WebTextInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebButton loginButton;
    private final WebTextInput loginTextInput;
    private final WebTextInput passwordTextInput;

    public LoginPage(WebDriver driver) {
        driver.get("http://localhost:8080/login");
        this.loginButton = new WebButton(driver.findElement(By.id("id_l.L.loginButton")));
        this.loginTextInput = new WebTextInput(driver.findElement(By.id("id_l.L.login")));
        this.passwordTextInput = new WebTextInput(driver.findElement(By.id("id_l.L.password")));
    }

    public void login(String login, String password) {
        loginTextInput.setText(login);
        passwordTextInput.setText(password);
        loginButton.click();
    }
}