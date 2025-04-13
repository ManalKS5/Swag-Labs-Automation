package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class cartPage {

    WebDriver driver;

    public cartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void printInfo(){
        List<WebElement> products = driver.findElements(By.className("cart_item"));
        for (WebElement product : products){
           System.out.println(product.findElement(By.className("inventory_item_name")).getText());
           System.out.println(product.findElement(By.className("inventory_item_price")).getText());
        }

    }


}
