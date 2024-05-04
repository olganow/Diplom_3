package pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class MainPage {
    private final WebDriver driver;

    private final By mainPageLoginXPath = By.xpath(".//*[text()='Войти в аккаунт']");
    private final By personAccountXPath = By.xpath(".//*[text()='Личный Кабинет']");
    private final By confirmOrderXPath = By.xpath(".//*[text()='Оформить заказ']");
    private final By mainPageBunXPath = By.xpath(".//*[@class='text text_type_main-default' and text()='Булки']");
    private final By mainPageSaucePath = By.xpath(".//*[@class='text text_type_main-default' and text()='Соусы']");
    private final By mainPageFillingXPath = By.xpath(".//*[@class='text text_type_main-default' and text()='Начинки']");
    private final By mainPageIngredientXPath = By.xpath(".//*[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage clickMainPageLogin() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(mainPageLoginXPath).click();
        return this;
    }

    public boolean checkPlaceOrder() throws InterruptedException {
        Thread.sleep(700);
        return driver.findElement(confirmOrderXPath).isEnabled();
    }

    public void clickPersonAccount() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(personAccountXPath).click();
    }

    public String changeIngredient(String ingredient) throws InterruptedException {
        Thread.sleep(3000);
        switch (ingredient) {
            case "Булки":
                if (!(driver.findElement(mainPageBunXPath).isEnabled())) {
                    driver.findElement(mainPageBunXPath).click();
                }
                break;
            case "Соусы":
                driver.findElement(mainPageSaucePath).click();
                break;
            case "Начинки":
                driver.findElement(mainPageFillingXPath).click();
                break;
        }
        return driver.findElement(mainPageIngredientXPath).getText();
    }

}
