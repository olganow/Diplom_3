package account;


import browser.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.Account;
import pageObject.Login;
import pageObject.MainPage;
import user.User;
import user.UserApi;

import static org.junit.Assert.assertTrue;

public class AccountTest {
    private WebDriver driver;
    private static User user;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        userApi = new UserApi();
        user = User.getGeneratedUser();

        ValidatableResponse response = userApi.createUser(user);
        accessToken = response.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
    }

    @Test
    @DisplayName("Navigate throw 'Личный кабинет'")
    public void testPersonalAccount() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);

        assertTrue("Неуспешных переход через 'Личный кабинет'", account.successfulExitPersonalAccount());
    }

    @Test
    @DisplayName("Exit with using a button 'Личный кабинет'")
    public void testExitFromPersonalAccount() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickExitButton();

        assertTrue("Неуспешных выход через 'Личный кабинет'", loginPage.loginPage());
    }

    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}
