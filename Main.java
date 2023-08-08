import java.util.*;
import exception.LateReturnExc ;
import exception.BookUnavailableException;
// 13521020 Varraz. maaf Pak, laporan tidak terbuat karena program belum selesai
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Selamat datang di Perpustakaan VHA");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Tampilkan Buku yang Tersedia");
            System.out.println("6. Tampilkan Buku yang Dipinjam");
            System.out.println("7. Tampilkan Informasi Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilihan Anda: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    System.out.print("Masukkan ID anggota: ");
                    int memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan nama anggota: ");
                    String memberName = scanner.nextLine();
                    library.addMember(memberID, memberName);
                    break;
                case 3:
                    System.out.print("Masukkan ID anggota: ");
                    int borrowMemberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan nomor ISBN buku yang akan dipinjam: ");
                    String borrowISBN = scanner.nextLine();
                    try{
                        library.borrow(borrowMemberID,borrowISBN);
                    }catch(BookUnavailableException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Masukkan ID anggota: ");
                    int returnMemberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan nomor ISBN buku yang akan dikembalikan: ");
                    String returnISBN = scanner.nextLine();
                    library.returnBook(returnMemberID, returnISBN);
                    break;
                case 5:
                    library.displayAvailableBooks();
                    break;
                case 6:
                    library.displayBorrowedBooks();
                    break;
                case 7:
                    System.out.print("Masukkan ID anggota: ");
                    int infoMemberID = scanner.nextInt();
                    scanner.nextLine();
                    library.displayInfoMember(infoMemberID);
                    break;
                case 8:
                    System.out.println("Terima kasih telah menggunakan layanan Perpustakaan VHA!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
