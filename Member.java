import java.util.ArrayList;
import java.util.List;

class Member {
    private int ID;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // setter getter
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrow (Book book) {
        if (book.getAvailable()) {
            book.borrow();
            borrowedBooks.add(book);
        } else {
            System.out.println("Buku " + book.getTitle() + " tidak tersedia saat ini.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
        } else {
            System.out.println("Buku " + book.getTitle() + " tidak dipinjamkan kepada anggota ini.");
        }
    }
}