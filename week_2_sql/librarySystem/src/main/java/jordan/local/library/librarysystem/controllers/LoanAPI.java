package jordan.local.library.librarysystem.controllers;


import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.models.Loans;
import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.services.LibraryItemService;
import jordan.local.library.librarysystem.services.LoanService;
import jordan.local.library.librarysystem.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/db/loan/v1")
public class LoanAPI {


    private final PatronService patronService;
    private final LibraryItemService libraryItemService;
    private final LoanService loanService;


    @Autowired
    public LoanAPI(PatronService patronService, LibraryItemService libraryItemService, LoanService loanService) {
        this.patronService = patronService;
        this.libraryItemService = libraryItemService;
        this.loanService = loanService;
    }


    @PostMapping("/place-loan")
    public ResponseEntity<?> placeLoan(@RequestParam("patronID") Long patronID, @RequestParam("itemID") Long itemID){


        Optional<Patron> patron = patronService.findPatronByID(patronID);

        LibraryItem libraryItem = libraryItemService.findItemByID(itemID);



        if(libraryItem != null){



            if(patron.isPresent()){


                Loans loans = loanService.checkOutAnItem(patron.get(), libraryItem);



                if(loans != null){





                    return new ResponseEntity<>(loans, HttpStatus.OK);
                }



            }



        }

        return new ResponseEntity<>("Loan not availble", HttpStatus.BAD_REQUEST);


    }


    @GetMapping("/all-Overdue")
    public ResponseEntity<List<LibraryItem>> getAllOverdue(){



        return new ResponseEntity<>(loanService.allOverDue(), HttpStatus.OK);
    }


    @GetMapping("patrons-checked")
    public ResponseEntity<?> getAllDueByPatron(@RequestParam("patronID") Long patronID){


        Optional<Patron> patron = patronService.findPatronByID(patronID);




        if(patron.isPresent()){


            return new ResponseEntity<>(loanService.getAllItemsOwedByPatron(patron.get()), HttpStatus.OK);
        }



        return new ResponseEntity<>("Patron ID is not valid", HttpStatus.BAD_REQUEST);


    }

    @PostMapping("/recheck-out")
    public ResponseEntity<?> recheckItem(@RequestParam("loanID") Long loanID){


        Optional<Loans> loans = loanService.findLoanByID(loanID);


        if(loans.isPresent()){

            Loans loanOut = loanService.recheckOutItem(loans.get());


            if(loanOut != null){


                return new ResponseEntity<>(loanOut, HttpStatus.OK);


            }







        }



        return new ResponseEntity<>("Invalid Recheck", HttpStatus.OK);
    }


    @PutMapping("/check-in")
    public ResponseEntity<?> checkIn(@RequestParam("patronID") Long patronID, @RequestParam("itemID") Long itemID){



        Optional<Patron> p = patronService.findPatronByID(patronID);
        LibraryItem libraryItem = libraryItemService.findItemByID(itemID);


        if(p.isPresent() && libraryItem != null){


            Loans l = loanService.checkInItem(libraryItem, p.get());


            if(l != null){



                return new ResponseEntity<>(l, HttpStatus.OK);
            }





        }


        return new ResponseEntity<>("There was a problem receiving the loan information", HttpStatus.OK);


    }


}
