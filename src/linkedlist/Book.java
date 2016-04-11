/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author muslumoncel
 */
public class Book {

    private String bookTitle, edition, publisher, price, author;
    private String[] authors;
    private int quantity;

    public Book(String author, String bookTitle, String edition, String publisher, String price, int quantity) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.edition = edition;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
    }

    public Book(String[] authors, String bookTitle, String edition, String publisher, String price, int quantity) {
        this.authors = authors;
        this.bookTitle = bookTitle;
        this.edition = edition;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getEdition() {
        return edition;
    }

    public String getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAuthor() {
        return author;
    }
    
    

    public void setAuthors(String[] author) {
        this.authors = author;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (authors!=null && authors.length > 0) {
            for (String aut : authors) {
                builder.append(aut);
                builder.append(" ");
            }
            return "Author(s) : " + builder.toString()
                    + "\nTitle :" + bookTitle
                    + "\nEdition : " + edition
                    + "\nPublisher : " + publisher
                    + "\nPrice : " + price
                    + "\nQuantity : " + quantity + "\n";
        } else {
            return "Author(s) : " + author
                    + "\nTitle :" + bookTitle
                    + "\nEdition : " + edition
                    + "\nPublisher : " + publisher
                    + "\nPrice : " + price
                    + "\nQuantity : " + quantity + "\n";
        }
    }
}
