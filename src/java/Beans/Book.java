package Beans;

import java.io.Serializable;


public class Book implements Serializable{
    private int bookId;
    private String bookName;
    private int quantity;
    private String author;
    private Category category;
    private int price;
    private String img;
    private String description;

    public Book(String bookName, int quantity, String author, Category category, int price, String img, String description) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.author = author;
        this.category = category;
        this.price = price;
        this.img = img;
        this.description = description;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Book() {
    }
   
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", bookName=" + bookName + ", quantity=" + quantity + ", author=" + author + ", category=" + category + ", price=" + price + ", img=" + img + ", description=" + description + '}';
    }
  
    
}
