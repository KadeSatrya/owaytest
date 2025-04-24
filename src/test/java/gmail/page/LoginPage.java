package gmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterEmail(String email){
        this.driver.findElement(By.name("identifier")).sendKeys(email);
        this.driver.findElement(By.xpath( "//span[text()='Next']")).click();
    }

    public void enterPassword(String password){
        this.wait.until(d -> d.findElement(By.name("Passwd")).isDisplayed());
        this.driver.findElement(By.name("Passwd")).sendKeys(password);
        WebElement nextButton = this.driver.findElement(By.xpath("//span[text()='Next']"));
        this.wait.until(d -> nextButton.isEnabled());
        nextButton.click();
    }
}
