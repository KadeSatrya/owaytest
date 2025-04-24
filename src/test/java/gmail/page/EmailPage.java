package gmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        // This assumes that it has been filtered for unread emails only
        // Heavily hardcoded with assumptions
        List<WebElement> selectCheckbox = driver.findElements(By.className("oZ-jc"));
        for (WebElement checkbox : selectCheckbox) {
            if (checkbox.isDisplayed()) {
                checkbox.click();
                break;
            }
        }

        int buttonCounter = 0;
        List<WebElement> deleteButtonCandidates = driver.findElements(By.className("asa"));
        for (WebElement buttonCandidate : deleteButtonCandidates) {
            if (buttonCandidate.isDisplayed()) {
                buttonCounter++;
                if (buttonCounter == 3) {
                    buttonCandidate.click();
                    break;
                }
            }
        }
        // Original code is as below, had to change after the filtering
        // this.driver.findElement(By.className("oZ-jc")).click();
        // Note: asa is for delete button
        // this.driver.findElement(By.className("asa")).click();
    }

    public String getFirstUnreadEmailSubject(){
        //Due to problems in recognizing element, this might not identify any email with empty subjects
        List<WebElement> emailSubjects = this.driver.findElements(By.xpath("//span[@class='bqe']"));
        for (WebElement subject : emailSubjects){
            if (subject.isDisplayed()){
                return subject.getText();
            }
        }
        return "";
    }

    public void filterByUnread(){
        this.driver.findElement(By.xpath("//input[@aria-label='Search mail']")).sendKeys("is:unread");
        this.driver.findElement(By.xpath("//button[@aria-label='Search mail']")).click();
    }
}
