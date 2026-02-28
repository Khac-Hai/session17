package bai3;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishedYear;
    private double price;

    public Book() {}

    public Book(String title, String author, int publishedYear, double price) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public Book(int id, String title, String author, int publishedYear, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishedYear() { return publishedYear; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }
    public void setPrice(double price) { this.price = price; }
}
