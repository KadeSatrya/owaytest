package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends Page {
    public BookPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void addToCart(){
        waitForPreloader();
        this.driver.findElement(By.className("btn-add-to-cart")).click();
    }

    public void closeModal(){
        WebElement modalCloseButton = this.driver.findElement(By.className("ti-close"));
        this.wait.until(d -> modalCloseButton.isDisplayed());
        modalCloseButton.click();
    }
}
