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

public class LogoTest {
    private WebDriver driver;
    private static User user;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        userApi = new UserApi();
        user = User.getGeneratedUser();
        ValidatableResponse createResponse = userApi.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
    }

    @Test
    @DisplayName("Navigate by click to Constructor")
    public void testConstructorLogo() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickConstructorButton();
        assertTrue("Неуспешных переход на Конструктор", mainPage.checkPlaceOrder());
    }

    @Test
    @DisplayName("Navigate by click to Stellar Burgers logo")
    public void testStellarBurgersLogo() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMainPageLogin();
        Login loginPage = new Login(driver);
        loginPage.positiveLogin(user);
        mainPage.clickPersonAccount();
        Account account = new Account(driver);
        account.clickStellarBurgerLogo();

        assertTrue("Неуспешных переход на логотип Stellar Burgers", mainPage.checkPlaceOrder());
    }


    @After
    public void teardown() {
        userApi.deleteUser(accessToken);
        driver.quit();
    }
}
