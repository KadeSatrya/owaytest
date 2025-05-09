package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends Page {
    public BookPage(WebDriver driver, WebDriverWait wait, String isbn) {
        super(driver, wait, "https://www.periplus.com/p/" + isbn);
    }

    public void addProductToCart(Integer quantity) {
        changeQuantity(quantity);
        this.driver.findElement(By.className("btn-add-to-cart")).click();
        closeModal();
    }

    private void changeQuantity(Integer quantity) {
        while(quantity > 1){
            this.driver.findElement(By.className("btn-product-plus")).click();
            quantity--;
        }
    }

    public void closeModal(){
        WebElement modalCloseButton = this.driver.findElement(By.className("ti-close"));
        this.wait.until(d -> modalCloseButton.isDisplayed());
        modalCloseButton.click();
    }
}
