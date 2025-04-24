package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends Page {
    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public Boolean verifyLoggedIn(String username){
        return this.driver.findElement(By.id("nav-signin-text")).getText().equals(username);
    }



    public void goToLogin(){
        driver.findElement(By.id("nav-signin-text")).click();
    }

    public void searchBook(String title){
        WebElement searchBox = driver.findElement(By.id("filter_name"));
        searchBox.sendKeys(title);
        searchBox.submit();
    }
}
