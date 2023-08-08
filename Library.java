import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner ;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import exception.BookUnavailableException;

class Library {
    private Map<String, Book> catalog;
    private Map<Integer, Member> members;
    private Scanner scanner;

    public Library() {
        catalog = new HashMap<>();
        members = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    //getter setter
    public void setCatalog (Map<String, Book> catalog) {
        this.catalog = catalog;
    }
    public Map<String, Book> getCatalog() {
        return catalog;
    }
    public void setMembers (Map<Integer, Member> members) {
        this.members = members;
    }
    public Map<Integer, Member> getMembers() {
        return members;
    }


    public void addBook() {
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan nama penulis: ");
        String author = scanner.nextLine();
        System.out.print("Masukkan nomor ISBN: ");
        String ISBN = scanner.nextLine();
        boolean available = true;

        Book newBook = new Book (title, author, ISBN,available);

        catalog.put(ISBN, newBook);
        System.out.println("Buku " + title + " telah ditambahkan ke katalog.");
    }

    public void addMember(int ID, String name) {
        System.out.print("Masukkan ID anggota: ");
        int memberID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan nama anggota: ");
        String memberName = scanner.nextLine();

        Member newMember = new Member(ID, name);

        members.put(ID, newMember);
        System.out.println("Anggota " + name + " telah didaftarkan.");
    }

    public void borrow(int memberID, String ISBN) throws BookUnavailableException {
    try {
        // System.out.print("Masukkan ID anggota: ");
        // memberID = scanner.nextInt();
        // scanner.nextLine();
        // System.out.print("Masukkan nama anggota: ");
        // String memberName = scanner.nextLine();
        // System.out.print("Masukkan nomor ISBN buku yang akan dipinjam: ");
        // ISBN = scanner.nextLine();
        if (members.containsKey(memberID) && catalog.containsKey(ISBN)) {
            Member member = members.get(memberID);
            Book book = catalog.get(ISBN);

            if (!book.getAvailable()) {
                throw new BookUnavailableException("Buku tidak tersedia untuk saat ini");
            } else {
                if (book instanceof ReferenceBook) {
                    ((ReferenceBook) book).borrow();
                } else if (book instanceof DigitalMedia) {
                    ((DigitalMedia) book).borrow();
                }
                member.borrow(book);
            }
        } else {
            System.out.println("ID anggota atau ISBN buku tidak valid.");
        }
    } catch (BookUnavailableException e) {
        System.out.println(e.getMessage());
    }
}



    public void returnBook(int memberID, String ISBN) {
        if (members.containsKey(memberID) && catalog.containsKey(ISBN)) {
            Member member = members.get(memberID);
            Book book = catalog.get(ISBN);
            if (book instanceof ReferenceBook){
                ((ReferenceBook) book).returnBook() ;
            }else if(book instanceof DigitalMedia){
                ((DigitalMedia) book).returnBook();
            }
            member.returnBook(book);
        } else {
            System.out.println("ID anggota atau ISBN buku tidak valid.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Daftar buku yang tersedia:");
        int i = 1;
        for (Book book : catalog.values()) {
            if (book.getAvailable()) {
                System.out.println(i + ". " + book.getTitle() + " - " + book.getAuthor());
                i++;
            }
        }
    }


    public void displayBorrowedBooks() {
        System.out.println("Daftar buku yang dipinjam:");
        int i = 1;
        for (Member member : members.values()) {
            List<Book> borrowedBooks = member.getBorrowedBooks();
            for (Book book : borrowedBooks) {
                System.out.println(i+ ". "+ book.getTitle() + " - " + book.getAuthor() + " oleh " + member.getName());
                i++ ;
            }
        }
    }

     public void displayInfoMember(int ID) {
        if (members.containsKey(ID)) {
            Member member = members.get(ID);
            System.out.println("Informasi member dengan id " + ID + " :\n");
            System.out.println("Nama : " + member.getName());
            System.out.println("Daftar buku yang member pinjam :\n");
            int i = 1;
            for (Book book : member.getBorrowedBooks()) {
                System.out.println(i + ". " + book.getTitle() + " - " + book.getAuthor());
                i++;
            }
        } else {
            System.out.println("Anggota dengan ID " + ID + " tidak ditemukan.");
        }
    }
}

