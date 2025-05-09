package periplus.util;

public class Book {
    public String title;
    public String currency;
    public String isbn;
    public Integer price;
    public Integer quantity;

    public Book(String title, String currency, String isbn, Integer price, Integer quantity) {
        this.title = title;
        this.currency = currency;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }
}
