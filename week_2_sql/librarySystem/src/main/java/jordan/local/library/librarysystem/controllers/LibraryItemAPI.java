package jordan.local.library.librarysystem.controllers;


import jordan.local.library.librarysystem.models.AudioCDs;
import jordan.local.library.librarysystem.models.Books;
import jordan.local.library.librarysystem.models.DvDs;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.services.LibraryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/db/libraryItems/v1")
public class LibraryItemAPI {


    private final LibraryItemService libraryItemService;

    public LibraryItemAPI(LibraryItemService libraryItemService) {
        this.libraryItemService = libraryItemService;
    }


    @GetMapping("/full-catalog")
    public ResponseEntity<List<LibraryItem>> getFullCatalog(){

        return new ResponseEntity<>(libraryItemService.getAllItems(), HttpStatus.OK);





    }

    @GetMapping("/find-item")

    public ResponseEntity<?> findItemByID(@RequestParam(name = "id") Long id){



        LibraryItem  lT = libraryItemService.findItemByID(id);


        if(lT != null){


            return new ResponseEntity<>(lT, HttpStatus.OK);

        }


        return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);




    }







}
