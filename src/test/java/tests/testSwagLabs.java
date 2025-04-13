package tests;

import Pages.cartPage;
import Pages.checkoutPage;
import Pages.loginPage;
import Pages.shopPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Pages.generals.waitAndClick;
import static Pages.generals.waitCustom;

public class testSwagLabs {

    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testValidUser() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");

        loginPage user = new loginPage(driver);
        shopPage shop = new shopPage(driver);
        cartPage cart = new cartPage(driver);
        checkoutPage checkout = new checkoutPage(driver);

        //login
        user.loginInformation("standard_user", "secret_sauce");

        //add single elements
        shop.addFirstElemtnt();

        //add number of elements 1 - 6
        shop.addOrderedElements(4);
        shop.goToCart();
        waitAndClick(driver, By.id("continue-shopping"));
        // remove elements
        shop.remove(4);

        //filter
        shop.filter();

        // add filtered elements
        shop.addExpensiveElements();
        shop.goToCart();
        waitCustom(driver);
        cart.printInfo();

        // chcekout
        shop.checkout();
        waitCustom(driver);
        checkout.fillFirstName("firstName");
        checkout.fillLastName("lastName");
        checkout.fillPostalCode("postalCode");
        checkout.continu();
        checkout.printInfo();
        checkout.finish();
        checkout.backHome();
    }

    @Test
    public void testInvalidUser(){
        driver.get("https://www.saucedemo.com/");

        loginPage user = new loginPage(driver);

        user.loginInformation("locked_out_user", "secret_sauce");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
