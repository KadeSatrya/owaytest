package periplus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import periplus.page.BookPage;
import periplus.page.LandingPage;
import periplus.page.LoginPage;
import periplus.page.SearchResultPage;
import java.time.Duration;

public class CartTest {
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

    @Test()
    public void testAddSingleItem() {
        LandingPage landingPage = new LandingPage(this.driver, this.wait);
        LoginPage loginPage = new LoginPage(this.driver, this.wait);
        SearchResultPage searchResultPage = new SearchResultPage(this.driver, this.wait);
        BookPage bookPage = new BookPage(this.driver, this.wait);

        // Login
        landingPage.goToLandingPage();
        landingPage.waitUntilLoaded();
        landingPage.goToLogin();
        loginPage.waitUntilLoaded();
        loginPage.login("testway04@gmail.com", "Harrymeow123");
        landingPage.goToLandingPage();
        landingPage.waitUntilLoaded();
        Assert.assertTrue(landingPage.verifyLoggedIn("Test"));

        // Search Product
        landingPage.searchBook("Kalkulus");
        searchResultPage.waitUntilLoaded();
        searchResultPage.goToFirstFoundBookPage();

        // Add product to cart
        bookPage.waitUntilLoaded();
        bookPage.addToCart();
        Assert.assertEquals(bookPage.getCartCount(), 1);
    }
}
