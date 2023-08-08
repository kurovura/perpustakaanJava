import java.util.HashMap;
import java.util.Map;

class Book {
    protected String title;
    protected String author;
    protected String ISBN;
    protected boolean available;

    public Book(String title, String author, String ISBN, boolean available) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.available = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable() {
        return available;
    }
    

    public void borrow() {
        if (available) {
            available = false;
            System.out.println("Buku " + title + " telah dipinjam.");
        } else {
            System.out.println("Buku " + title + " tidak tersedia saat ini.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println("Buku " + title + " telah dikembalikan.");
    }
}

