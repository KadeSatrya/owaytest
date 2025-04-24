package gmail;

import gmail.page.EmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import gmail.page.LandingPage;
import gmail.page.LoginPage;

import java.time.Duration;

public class EmailTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void cleanUp() {
        this.driver.quit();
    }

    @Test
    public void testDeleteEmail() {
        LandingPage landingPage = new LandingPage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        EmailPage emailPage = new EmailPage(driver, wait);

        landingPage.goToLandingPage();
        loginPage.enterEmail("testway04@gmail.com");
        loginPage.enterPassword("Harrymeow123");
        emailPage.waitUntilLoaded();
        emailPage.filterByUnread();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Integer emailCountBeforeDeletion = emailPage.getEmailCount();
        String firstUnreadEmailSubject = emailPage.getFirstUnreadEmailSubject();
        emailPage.deleteSingleEmail();
        Assert.assertEquals(emailPage.getEmailCount(), emailCountBeforeDeletion-1);
        // Note: this assumes that there are no emails with the same subject
        Assert.assertNotEquals(emailPage.getFirstUnreadEmailSubject(), firstUnreadEmailSubject);
    }
}
