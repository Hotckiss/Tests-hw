import hse.kirilenko.objects.LoginPage;
import hse.kirilenko.objects.NewUserForm;
import hse.kirilenko.objects.UsersPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestYouTrack {
    private static final String host = "http://localhost:8080";

    @Test
    public void oneUserTest() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "xxxxxxx";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertTrue(page.getUsers().contains(login));
        page.deleteUser(login);
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void manyUsersTest() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        final WebDriver driver = new FirefoxDriver();
        new LoginPage(driver).login("root", "1234");
        final UsersPage page = new UsersPage(driver, host);
        final List<String> logins = new ArrayList<>();

        for (int i = 0; i < 3 ; i++) {
            final String login = "xxx" + i;
            final NewUserForm form = page.open();
            form.setLogin(login);
            form.setPassword("111");
            form.setPasswordConfirm("111");
            form.ok();
            Thread.sleep(300);
            page.refresh();
            logins.add(login);
        }

        assertTrue(page.getUsers().containsAll(logins));
        logins.forEach(login -> {
            page.deleteUser(login);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            page.refresh();
            assertFalse(page.getUsers().contains(login));
        });

        driver.quit();
    }

    @Test
    public void passwordsDoNotMatchTest() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();
        new LoginPage(driver).login("root", "1234");
        final UsersPage page = new UsersPage(driver, host);
        final String login = "xxxx";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void cancelTest() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");
        final WebDriver driver = new FirefoxDriver();
        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "xxxx";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("xxx");
        form.setPasswordConfirm("xxx");
        form.cancel();
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void existingUserTest() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();
        new LoginPage(driver).login("root", "1234");
        final UsersPage page = new UsersPage(driver, host);
        final String login = "xxxx";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        final List<String> logins1 = page.getUsers();

        final NewUserForm form2 = page.open();
        form2.setLogin(login);
        form2.setPassword("111");
        form2.setPasswordConfirm("111");
        form2.ok();
        Thread.sleep(300);
        page.refresh();

        final List<String> logins2 = page.getUsers();
        assertEquals(logins1.size(), logins2.size());

        page.deleteUser(login);
        driver.quit();
    }

    @Test
    public void incorrectLoginTest1() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "xxxxxxx kek";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void incorrectLoginTest2() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void incorrectLoginTest3() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "**/**";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertFalse(page.getUsers().contains(login));
        driver.quit();
    }

    @Test
    public void correctLoginTest1() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "2435098215014687143_6_";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertTrue(page.getUsers().contains(login));
        page.deleteUser(login);
        Thread.sleep(300);
        page.refresh();
        driver.quit();
    }

    @Test
    public void correctLoginTest2() throws Exception {
        System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
        // spend 2 hrs to solve this, PATH do not help
        System.setProperty("webdriver.firefox.bin",
                "F:\\Program Files\\Mozilla Firefox\\firefox.exe");

        final WebDriver driver = new FirefoxDriver();

        new LoginPage(driver).login("root", "1234");

        final UsersPage page = new UsersPage(driver, host);
        final String login = "*****";
        final NewUserForm form = page.open();
        form.setLogin(login);
        form.setPassword("111");
        form.setPasswordConfirm("111");
        form.ok();
        Thread.sleep(300);
        page.refresh();
        assertTrue(page.getUsers().contains(login));
        page.deleteUser(login);
        Thread.sleep(300);
        page.refresh();
        driver.quit();
    }
}
