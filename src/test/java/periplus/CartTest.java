package periplus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import periplus.page.*;
import periplus.util.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private CartPage cartPage;

    // Login Credentials
    private final String EMAIL = "testway04@gmail.com";
    private final String PASSWORD = "Harrymeow123";
    private final String CURRENCY = "Rp";

    // Book dummy data
    private final Book bookOne = new Book("James", CURRENCY, "9780593862735", 536000, 1);
    private final Book bookTwo = new Book("Cover Story", CURRENCY, "9780008562045", 218000, 2);
    private final Book bookThree = new Book("Zero Point", CURRENCY, "9781350537842", 235000, 5);
    private List<Book> expectedBookList;

    @BeforeTest
    private void setUp() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.loginPage = new LoginPage(this.driver, this.wait, "https://www.periplus.com/account/Login");
        this.cartPage = new CartPage(this.driver, this.wait, "https://www.periplus.com/checkout/cart");

        expectedBookList = new ArrayList<>();
        expectedBookList.add(bookOne);
        expectedBookList.add(bookTwo);
        expectedBookList.add(bookThree);
    }

    @AfterMethod
    private void cleanUp() {
        cartPage.navigateTo();
        cartPage.deleteAllItems();
    }

    @AfterTest
    private void Teardown(){
        this.driver.quit();
    }

    @BeforeMethod
    private void login() {
        loginPage.login(EMAIL, PASSWORD);
    }

    @Test(priority = 1)
    public void testAddOneQuantityOfAnObject() {
        // Navigate to and add one book to cart
        BookPage bookOnePage = new BookPage(this.driver, this.wait, bookOne.isbn);
        bookOnePage.navigateTo();
        bookOnePage.addProductToCart(bookOne.quantity);

        // Navigate to cart page and get relevant data for testing
        cartPage.navigateTo();
        List<Book> bookList = cartPage.getAllBooks();
        Book cartBookOne = bookList.get(0);

        // Test that cart has one book
        compareCartQuantity(bookOne.quantity);

        // Test that the book in cart is the correct one
        compareBook(cartBookOne, bookOne);

        // Test Sub-total and Total
        compareTotalPrice(bookOne.price);
    }

    @Test(priority = 2)
    public void testAddMultipleQuantityOfAnObject() {
        // Navigate to and add books to cart
        BookPage bookTwoPage = new BookPage(this.driver, this.wait, bookTwo.isbn);
        bookTwoPage.navigateTo();
        bookTwoPage.addProductToCart(bookTwo.quantity);

        // Navigate to cart page and get relevant data for testing
        cartPage.navigateTo();
        List<Book> bookList = cartPage.getAllBooks();
        Book cartBookTwo = bookList.get(0);

        // Test that cart has correct amount of book
        compareCartQuantity(bookTwo.quantity);

        // Test that the book in cart is the correct one
        compareBook(cartBookTwo, bookTwo);

        // Test Sub-total and Total
        compareTotalPrice(bookTwo.price * bookTwo.quantity);
    }

    @Test(priority = 3)
    public void testAddMultipleQuantityOfMultipleObjects() {
        // Navigate to and add books to cart
        for (Book book : expectedBookList) {
            BookPage bookPage = new BookPage(this.driver, this.wait, book.isbn);
            bookPage.navigateTo();
            bookPage.addProductToCart(book.quantity);
        }

        // Navigate to cart page and get relevant data for testing
        cartPage.navigateTo();
        List<Book> bookList = cartPage.getAllBooks();

        // Test that cart has correct amount of book
        compareCartQuantity(getExpectedQuantity(bookList));

        // Test that the book in cart is the correct one
        for (int i=0; i<bookList.size(); i++) {
            compareBook(bookList.get(i), expectedBookList.get(i));
        }

        // Test Sub-total and Total
        compareTotalPrice(getExpectedTotalPrice(expectedBookList));
    }

    /* Assertion Methods */
    private void compareBook(Book actualBook, Book expectedBook) {
        Assert.assertEquals(actualBook.title, expectedBook.title);
        Assert.assertEquals(actualBook.isbn, expectedBook.isbn);
        Assert.assertEquals(actualBook.price, expectedBook.price);
        Assert.assertEquals(actualBook.quantity, expectedBook.quantity);
        Assert.assertEquals(actualBook.currency, expectedBook.currency);
    }

    private void compareTotalPrice(Integer expectedTotal) {
        Assert.assertEquals(cartPage.getTotalAmount(), expectedTotal);
        Assert.assertEquals(cartPage.getSubTotalAmount(), expectedTotal);
        Assert.assertEquals(cartPage.getTotalCurrency(), CURRENCY);
        Assert.assertEquals(cartPage.getSubTotalCurrency(), CURRENCY);
    }

    private void compareCartQuantity(Integer expected) {
        Assert.assertEquals(cartPage.getCartCount(), expected);
    }

    /* Util Methods */
    private Integer getExpectedTotalPrice(List<Book> bookList) {
        Integer total = 0;
        for (Book book : bookList) {
            total += book.price * book.quantity;
        }
        return total;
    }

    private Integer getExpectedQuantity(List<Book> bookList) {
        Integer quantity = 0;
        for (Book book : bookList) {
            quantity += book.quantity;
        }
        return quantity;
    }
}
