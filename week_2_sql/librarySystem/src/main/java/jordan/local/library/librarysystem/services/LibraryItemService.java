package jordan.local.library.librarysystem.services;

import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.repos.LibraryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public LibraryItem updateItem(LibraryItem i){


        return libraryItemRepo.save(i);
    }


    public LibraryItem findItemByID(Long id){


        return libraryItemRepo.findLibraryItemByItemID(id);
    }


    public List<LibraryItem> getAllLostItems(){


        return libraryItemRepo.findAllByItemStatus(ItemStatus.LOST);
    }


    public LibraryItem editItemPrice(LibraryItem item, double price){

        item.setCost(price);





        return  libraryItemRepo.save(item);




    }

}
