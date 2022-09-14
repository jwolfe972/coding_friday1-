package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.models.Books;
import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.repos.BookRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {


    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public LibraryItem addBook(String fname, String lname, String genre, double cost, int days, Date releaseDate, String title){


        Books newBook = new Books(fname, lname, genre, cost, ItemStatus.IN_STOCK,  days, releaseDate, title);



        return bookRepo.save(newBook);


    }

    public List<Books> getAllBooks(){


        return bookRepo.findAll();
    }
}
