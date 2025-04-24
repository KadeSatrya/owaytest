package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void waitUntilLoaded(){
        this.wait.until(d -> this.driver.findElement(By.id("ps")).isDisplayed());
    }

    public void login(String email, String password){
        this.driver.findElement(By.name("email")).sendKeys(email);
        this.driver.findElement(By.name("password")).sendKeys(password);
        this.driver.findElement(By.id("button-login")).click();
    }
}
