package pageObject;


import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    private final WebDriver driver;
    private final By registerXPath = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By emailXPath = By.xpath(".//*[@class='input__container']/div/label[text()='Email']/following-sibling::input");
    private final By passwordXPath = By.xpath(".//*[@class='input__container']/div/label[text()='Пароль']/following-sibling::input");
    private final By exitXPath = By.xpath(".//*[text()='Войти']");
    private final By confirmOrderXPath = By.xpath(".//*[text()='Оформить заказ']");
    private final By recoverPasswordExitXPath = By.xpath(".//*[text()='Войти']");
    private final By recoverPasswordХPath = By.xpath(".//*[text() ='Восстановить пароль']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public Login setEmail(String email) {
        driver.findElement(emailXPath).sendKeys(email);
        return this;
    }

    public Login setPassword(String password) {
        driver.findElement(passwordXPath).sendKeys(password);
        return this;
    }

    public void clickEnterButton() {
        driver.findElement(exitXPath).click();
    }

    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordХPath).click();
    }

    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordExitXPath).click();
    }

    public void clickRegistrationPath() throws InterruptedException {
        WebElement element = driver.findElement(registerXPath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);
        driver.findElement(registerXPath).click();
    }

    public boolean positiveLogin(User userCredentials) throws InterruptedException {
        setEmail(userCredentials.getEmail());
        setPassword(userCredentials.getPassword());
        clickEnterButton();

        Thread.sleep(1000);
        return driver.findElement(confirmOrderXPath).isEnabled();
    }

    public boolean loginPage() throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElement(exitXPath).isEnabled();
    }
}
