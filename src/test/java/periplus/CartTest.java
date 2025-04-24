package periplus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import periplus.page.*;

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
        CartPage cartPage = new CartPage(this.driver, this.wait);
        this.driver.get("https://www.periplus.com/checkout/cart");
        cartPage.waitUntilLoaded();
        cartPage.deleteItem();
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
        Assert.assertEquals(bookPage.getCartCount(), 0);

        // Search Product
        landingPage.searchBook("Kalkulus");
        searchResultPage.waitUntilLoaded();
        searchResultPage.goToFirstFoundBookPage();

        // Add product to cart
        bookPage.waitUntilLoaded();
        bookPage.addToCart();
        bookPage.closeModal();
        Assert.assertEquals(bookPage.getCartCount(), 1);
    }
}
