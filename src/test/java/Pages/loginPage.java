package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

    WebDriver driver;

    // Locators
    By userNameInput = By.xpath("//input[@id='user-name']");
    By passwordInput = By.xpath("//input[@id='password']");
    By submitBTN = By.xpath("//input[@id='login-button']");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void loginInformation(String user, String pass){

        try{
            driver.findElement(userNameInput).sendKeys(user);
            driver.findElement(passwordInput).sendKeys(pass);
            driver.findElement(submitBTN).click();
        }
        catch (Exception e){
            System.out.println(driver.findElement(By.id("error-button")).getText());
        }

    }

}
