package gmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends Page {
    public EmailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitUntilLoaded(){
        this.wait.until(d -> this.driver.findElement(By.linkText("Inbox")).isDisplayed());
    }

    public Integer getEmailCount(){
        // CSS class name for the select button that I found in gmail is "oZ-jc"
        return this.driver.findElements(By.className("oZ-jc")).size();
    }

    public void deleteSingleEmail(){
        this.driver.findElement(By.className("oZ-jc")).click();
        // Note: ar9 is for delete button
        this.driver.findElement(By.className("asa")).click();
    }
}
