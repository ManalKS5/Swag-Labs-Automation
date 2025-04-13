package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static Pages.generals.*;

public class shopPage {

    WebDriver driver;

    // Locators
    By backbag = By.xpath("//a//div[text()='Sauce Labs Backpack']");
    By addToCart = By.xpath("//button[@id='add-to-cart']");
    By back = By.xpath("//button[@id='back-to-products']");
    By light = By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']");
    By Tshirt = By.xpath("//a//div[text()='Sauce Labs Bolt T-Shirt']");

    public shopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstElemtnt() {
        driver.findElement(backbag).click();
        driver.findElement(addToCart).click();
        driver.findElement(back).click();
    }

    public void addOrderedElements(int count) {

        for (int i = 1; i < count; i++) {
            waitAndClick(driver, By.xpath("(//div//button[text()='Add to cart'])[" + i + "]"));
        }
    }

    public void goToCart() {
        waitAndClick(driver, By.id("shopping_cart_container"));
    }

    public void remove(int count) {
        for (int i = 0; i < count; i++) {
            waitAndClick(driver, By.xpath("//div//button[text()='Remove']"));
        }
    }

    public void filter() {
        WebElement filters = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
        Select select = new Select(filters);
        waitCustom(driver);
        select.selectByVisibleText("Price (high to low)");
        waitCustom(driver);
    }

    public void addExpensiveElements() {
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        for (WebElement product : products) {
            double price = getPrice(product);
            if (price > 15){
                addElement(product);
                waitCustom(driver);
            }
            else
                continue;
        }
    }

    public double getPrice(WebElement product) {
        String priceTxt = product.findElement(By.className("inventory_item_price")).getText();
        priceTxt = priceTxt.replace("$", "");
        return Double.parseDouble(priceTxt);
    }

    public void addElement(WebElement product){
        product.findElement(By.xpath("//div//button[text()='Add to cart']")).click();
    }

    public void checkout() throws InterruptedException {
        WebElement checkoutBTN = driver.findElement(By.xpath("//button[text()='Checkout']"));
        boolean temp = scrollDown(driver, checkoutBTN);
        if (temp == true){
            waitCustom(driver);
            checkoutBTN.click();
        }
    }

}
