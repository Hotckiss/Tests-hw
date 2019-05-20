package hse.kirilenko.objects;

import hse.kirilenko.elements.WebButton;
import hse.kirilenko.elements.WebTextInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UsersPage {
    private final WebDriver driver;
    private final String host;
    private WebButton createUserButton;

    public UsersPage(WebDriver driver, String host) {
        this.driver = driver;
        this.host = host;
        refresh();
    }

    public void refresh() {
        driver.get(host + "/users");
        createUserButton = new WebButton(driver.findElement(By.id( "id_l.U.createNewUser")));
    }

    public NewUserForm open() {
        createUserButton.click();
        final WebTextInput login = new WebTextInput(driver.findElement(By.id("id_l.U.cr.login")));
        final WebTextInput password = new WebTextInput(driver.findElement(By.id("id_l.U.cr.password")));
        final WebTextInput confirmPassword = new WebTextInput(driver.findElement(By.id("id_l.U.cr.confirmPassword")));
        final WebButton create = new WebButton(driver.findElement(By.id("id_l.U.cr.createUserOk")));
        final WebButton cancel = new WebButton(driver.findElement(By.id("id_l.U.cr.createUserCancel")));
        return new NewUserForm(create, cancel, login, password, confirmPassword);
    }

    public List<String> getUsers() {
        final WebElement table = driver.findElement(By.id("id_l.U.usersList.usersList"));
        final WebElement tableBody = table.findElement(By.tagName("tbody"));
        final List<String> users = new ArrayList<>();
        for (WebElement panel : tableBody.findElements(By.tagName("tr"))) {
            final WebElement labelElement = panel.findElement(By.xpath(".//*[@cn='l.U.usersList.UserLogin.editUser']"));
            final String login = labelElement.getAttribute("title");
            users.add(login);
        }

        return users;
    }

    public void deleteUser(String name) {
        final WebElement table = driver.findElement(By.id("id_l.U.usersList.usersList"));
        final WebElement tableBody = table.findElement(By.tagName("tbody"));

        for (WebElement panel : tableBody.findElements(By.tagName("tr"))) {
            final WebElement labelElement = panel.findElement(By.xpath(".//*[@cn='l.U.usersList.UserLogin.editUser']"));
            if (labelElement.getAttribute("title").equals(name)) {
                final List<WebElement> buttons = panel.findElements(By.xpath(".//*[@cn='l.U.usersList.deleteUser']"));
                if (buttons.size() == 1) {
                    buttons.get(0).click();
                    driver.switchTo().alert().accept();
                }
                break;
            }
        }
    }
}
