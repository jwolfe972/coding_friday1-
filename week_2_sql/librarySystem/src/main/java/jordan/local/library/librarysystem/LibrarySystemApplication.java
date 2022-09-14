package jordan.local.library.librarysystem;

import jordan.local.library.librarysystem.models.AudioCDs;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.services.AudioCDService;
import jordan.local.library.librarysystem.services.BookService;
import jordan.local.library.librarysystem.services.LibraryItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LibrarySystemApplication  implements CommandLineRunner {


    private final AudioCDService audioCDService;
    private final BookService bookService;


    private final LibraryItemService libraryItemService;

    public LibrarySystemApplication(AudioCDService audioCDService, BookService bookService, LibraryItemService libraryItemService) {
        this.audioCDService = audioCDService;
        this.bookService = bookService;
        this.libraryItemService = libraryItemService;
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
