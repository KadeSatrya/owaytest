package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitUntilLoaded(){
        this.wait.until(d -> this.driver.findElement(By.className("search-bar")).isDisplayed());
    }

    public Integer getCartCount(){
        return Integer.parseInt(this.driver.findElement(By.id("cart_total")).getText());
    }

    protected void waitForPreloader(){
        // Note to self: Preloader seems to be blocking the click
        // src: https://stackoverflow.com/questions/57809310/how-to-handle-div-id-preloader-obscuring-other-elements-with-selenium-check
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
    }
}
