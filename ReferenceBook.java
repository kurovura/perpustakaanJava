import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import exception.LateReturnException ;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

class ReferenceBook extends Book {

    private Date dateBorrow;
    private Calendar dateReturn ;
    private Scanner scanner;

    public ReferenceBook(String title, String author, String ISBN, boolean available, Date dateBorrow, Calendar dateReturn, Scanner scanner) {
        super(title, author, ISBN, available);
        this.dateBorrow = dateBorrow ;
        this.dateReturn = dateReturn ;
        scanner = new Scanner(System.in);
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }
    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }
    public Calendar getDateReturn() {
        return dateReturn;
    }
    public void setDateReturn(Calendar dateReturn) {
        this.dateReturn = dateReturn;
    }

     @Override
    public void borrow() {
        try {
            System.out.print("Masukkan tanggal peminjaman (dd-MM-yyyy): ");
            String borrowDateStr = scanner.nextLine(); // Assuming scanner is defined in this class
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(true);
            Date dateBorrow = sdf.parse(borrowDateStr);
            Calendar dateReturn = Calendar.getInstance();
            dateReturn.setTime(dateBorrow);
            dateReturn.add(Calendar.MONTH, 6);
            System.out.println("Tanggal pengembalian: " + sdf.format(dateReturn.getTime()));
            setDateReturn(dateReturn);
            setAvailable(false);
            System.out.println("Buku " + getTitle() + " sukses dipinjamkan ke kamu");
        } catch (ParseException e) {
            System.out.println("Format tanggal tidak sesuai. Gunakan format dd-MM-yyyy.");
        }
    }

    @Override
    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("Masukkan tanggal pengembalian (dd-MM-yyyy): ");
        String returnDayStr = scanner.nextLine();

        try {
            Calendar returnToday = Calendar.getInstance();
            returnToday.setTime(sdf.parse(returnDayStr));

            if (returnToday.after(getDateReturn())) {
                long diffInMillis = returnToday.getTimeInMillis() - getDateReturn().getTimeInMillis();
                long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);

                // Calculate totalDenda based on the late return
                long totalDenda = diffInDays * 10000;

                throw new LateReturnException("Anda terlambat mengembalikan media " + getTitle() + " selama " + diffInDays + " hari. Anda terkena denda sebesar " + totalDenda + " rupiah.");
            } else {
                setAvailable(true);
                System.out.println("Media berjudul " + getTitle() + " telah dikembalikan.");
            }
        } catch (ParseException e) {
            System.out.println("Format tanggal salah. Pastikan menggunakan format dd-MM-yyyy.");
        } catch (LateReturnException e) {
            System.out.println(e.getMessage());
        }

    }


}