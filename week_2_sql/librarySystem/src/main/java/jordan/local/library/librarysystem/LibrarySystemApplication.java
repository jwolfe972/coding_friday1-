package jordan.local.library.librarysystem;

import jordan.local.library.librarysystem.services.AudioCDService;
import jordan.local.library.librarysystem.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class LibrarySystemApplication  implements CommandLineRunner {


    private final AudioCDService audioCDService;
    private final BookService bookService;

    public LibrarySystemApplication(AudioCDService audioCDService, BookService bookService) {
        this.audioCDService = audioCDService;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



        audioCDService.addCD("dd", "ss", 12, new Date(), "sfefews", 22.00, 10);

        bookService.addBook("Jordan", "Wolfe", "non-fiction", 34.44, 20, new Date(), "Breathe of Fresh Air" );









    }
}
