package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Page {
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void deleteItem(){
        waitForPreloader();
        this.driver.findElement(By.className("btn-cart-remove")).click();
    }
}
