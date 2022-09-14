package jordan.local.library.librarysystem.services;

import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.repos.LibraryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LibraryItemService {

    private final LibraryItemRepo libraryItemRepo;

    @Autowired
    public LibraryItemService(LibraryItemRepo libraryItemRepo) {
        this.libraryItemRepo = libraryItemRepo;
    }



    public List<LibraryItem> getAllItems(){


        return libraryItemRepo.findAll();
    }


    public LibraryItem findItemByID(Long id){


        return libraryItemRepo.findLibraryItemByItemID(id);
    }

}
