package login;

import browser.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.Login;
import pageObject.MainPage;
import pageObject.Registration;
import user.User;
import user.UserApi;

import static org.junit.Assert.assertTrue;

public class EntryButtonToLogInTest {
    private WebDriver driver;
    private UserApi userApi;
    private static User user;
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
    @DisplayName("Entry with using a button 'Войти в аккаунт'")
    public void testEntryLogInButton() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);

        assertTrue("Неуспешных вход в приложение через 'Войти в аккаунт'",
                loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Entry with using a button 'Личный кабинет'")
    public void testEntryThrowPersonalAccount() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonAccount();
        Login loginPage = new Login(driver);

        assertTrue("Неуспешных вход в приложение через 'Личный кабинет'", loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Entry with using a registration page")
    public void testEntryThrowLogInRegistration() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRegistrationPath();
        Registration registerPage = new Registration(driver);
        registerPage.clickEnterButton();

        assertTrue("Неуспешных вход в приложение через форму регистрации", loginPage.positiveLogin(user));
    }

    @Test
    @DisplayName("Entry with using a recovery form")
    public void testEntryThrowRecoveryForm() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.clickRecoverPasswordLink();
        loginPage.clickRecoverPasswordButton();

        assertTrue("Неуспешных вход в приложение через восстановление пароля",
                loginPage.positiveLogin(user));
    }

    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}
