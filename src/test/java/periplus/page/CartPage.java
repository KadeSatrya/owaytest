package periplus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import periplus.util.Book;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends Page {
    public CartPage(WebDriver driver, WebDriverWait wait, String url) {
        super(driver, wait, url);
    }


    public void deleteAllItems(){
        while (!this.driver.findElements(By.className("btn-cart-remove")).isEmpty()){
            this.driver.findElement(By.className("btn-cart-remove")).click();
            waitForPreloader();
        }
    }

    /* BOOK OBJECTS */

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<Book>();
        List<WebElement> bookElements = this.driver.findElements(By.className("row-cart-product"));
        if (!bookElements.isEmpty()){
            for(WebElement bookElement : bookElements){
                WebElement wrapperElement = bookElement.findElement(By.className("col-9"));
                List<WebElement> rowElements = wrapperElement.findElements(By.className("row"));
                books.add(parseBookFromCartRowElement(rowElements));
            }
        }
        return books;
    }

    private Book parseBookFromCartRowElement(List<WebElement> rowElements){
        String title = rowElements.get(0).getText().trim();
        String isbn = rowElements.get(1).getText().trim();
        String[] priceAndCurrency = rowElements.get(2).getText().trim().split(" ");
        String currency = priceAndCurrency[0];
        Integer price = Integer.parseInt(priceAndCurrency[1].replace(",", ""));

        WebElement quantityRowElement = rowElements.get(3);
        WebElement quantityElement = quantityRowElement.findElement(By.className("input-number"));
        String quantityString = quantityElement.getDomAttribute("value");
        Integer quantity = Integer.parseInt(quantityString);

        return new Book(title, currency, isbn, price, quantity);
    }

    /* SUBTOTAL AND TOTAL */

    public Integer getSubTotalAmount(){
        return Integer.parseInt(getSubtotalString()[1].replace(",",""));
    }

    public String getSubTotalCurrency(){
        return getSubtotalString()[0];
    }

    public Integer getTotalAmount(){
        return Integer.parseInt(getTotalString()[1].replace(",",""));
    }

    public String getTotalCurrency(){
        return getTotalString()[0];
    }

    private String[] getSubtotalString(){
        return getTotalSection().get(0).getText().trim().split(" ");
    }

    private String[] getTotalString(){
        return getTotalSection().get(1).getText().trim().split(" ");
    }

    private List<WebElement> getTotalSection(){
            return this.driver.findElements(By.id("sub_total"));
    }
}
