package jordan.local.library.librarysystem;

import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.models.Loans;
import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LibrarySystemApplication  implements CommandLineRunner {


    private final AudioCDService audioCDService;
    private final BookService bookService;
    private final LoanService loanService;


    private final LibraryItemService libraryItemService;

    private final PatronService patronService;

    public LibrarySystemApplication(AudioCDService audioCDService, BookService bookService, LoanService loanService, LibraryItemService libraryItemService, PatronService patronService) {
        this.audioCDService = audioCDService;
        this.bookService = bookService;
        this.loanService = loanService;
        this.libraryItemService = libraryItemService;
        this.patronService = patronService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {





       LibraryItem s =  audioCDService.addCD("dd", "ss", 12, new Date(), "sfefews", 22.00, 10);

       LibraryItem b =  bookService.addBook("Jordan", "Wolfe", "non-fiction", 34.44, 20, new Date(), "Breathe of Fresh Air" );

        Patron p = patronService.addPatron(new Patron("Jordan", "Wolfe"));


        Loans loans = loanService.checkOutAnItem(p, s);
            loanService.checkOutAnItem(p,b);





        List<Patron> allLastName = patronService.getByLastName("Wolfe");



        for(Patron pat: allLastName ){


            System.out.println(pat.getPatronStatus());
        }



















    }
}
