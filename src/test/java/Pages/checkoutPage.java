package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Pages.generals.scrollDown;
import static Pages.generals.waitAndClick;

public class checkoutPage {

    WebDriver driver;

    public checkoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstName(String fName){
        driver.findElement(By.id("first-name")).sendKeys(fName);
    }

    public void fillLastName(String lName){
        driver.findElement(By.id("last-name")).sendKeys(lName);
    }

    public void fillPostalCode(String Pcode){
        driver.findElement(By.id("postal-code")).sendKeys(Pcode);
    }

    public void continu(){
        waitAndClick(driver, By.id("continue"));
    }

    public void printInfo(){
        System.out.println(driver.findElement(By.className("summary_subtotal_label")).getText());
        System.out.println(driver.findElement(By.className("summary_tax_label")).getText());
        System.out.println(driver.findElement(By.className("summary_total_label")).getText());
    }

    public void finish(){
      if (scrollDown(driver, driver.findElement(By.id("finish"))))
          driver.findElement(By.id("finish")).click();
    }

    public void backHome(){
        waitAndClick(driver, By.id("back-to-products"));
    }
}
