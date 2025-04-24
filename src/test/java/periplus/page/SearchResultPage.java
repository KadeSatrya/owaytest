package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends Page{
    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void goToFirstFoundBookPage(){
        waitForPreloader();
        this.driver.findElement(By.className("hover-img")).click();
    }
}
