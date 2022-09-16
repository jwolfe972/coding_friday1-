package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.enums.LoanStatus;
import jordan.local.library.librarysystem.enums.PatronStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.models.Loans;
import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.repos.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanService {

    private final LoanRepo loanRepo;
    private final PatronService patronService;
    private final LibraryItemService libraryItemService;



    @Autowired
    public LoanService(LoanRepo loanRepo, PatronService patronService, LibraryItemService libraryItemService) {
        this.loanRepo = loanRepo;
        this.patronService = patronService;
        this.libraryItemService = libraryItemService;
    }


    public Loans checkOutAnItem(Patron patron, LibraryItem libraryItem){




        if(patron.isEligible()){



            if(libraryItem.getItemStatus() == ItemStatus.IN_STOCK){


                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, libraryItem.getLoanDays());


                Date dueDate = c.getTime();



                Loans newLoan = new Loans(libraryItem, patron, dueDate);

                libraryItem.setItemStatus(ItemStatus.LOANED_OUT);


                patron.setNumberOfRentedItems(patron.getNumberOfRentedItems() + 1);


                libraryItemService.updateItem(libraryItem);



                return loanRepo.save(newLoan);




            }





        }

        return null;
    }




    public Loans reportItemLost(Loans l){


        l.setLoanStatus(LoanStatus.LOST);


        Patron p = l.getPatron();
        p.setPatronStatus(PatronStatus.FINED);


        LibraryItem li = l.getLibraryItem();


        p.setBalance( p.getBalance() - (l.getLibraryItem().getCost()) );

        li.setItemStatus(ItemStatus.LOST);


        patronService.updatePatron(p);
        libraryItemService.updateItem(li);











        return loanRepo.save(l);
    }


    public Loans recheckOutItem(Loans l){



        if(l.getNumberOfRechecks() > 0){


            l.setNumberOfRechecks(l.getNumberOfRechecks() -1);


            Calendar c = Calendar.getInstance();
            c.setTime(l.getLoanDueDate());
            c.add(Calendar.DATE, l.getLibraryItem().getLoanDays());


            Date dueDate = c.getTime();

            l.setLoanDueDate(dueDate);


            return loanRepo.save(l);



        }



        return null;












    }


    public Loans checkInItem(LibraryItem item, Patron p) {

        Loans loans = loanRepo.findCheckoutLoan(p.getId(), item.getItemID(), LoanStatus.COMPLETE.ordinal());




        if(loans != null){





            loans.setLoanStatus(LoanStatus.COMPLETE);


            item.setItemStatus(ItemStatus.IN_STOCK);
            p.setNumberOfRentedItems(p.getNumberOfRentedItems() -1);

            libraryItemService.updateItem(item);
            patronService.updatePatron(p);


            return loanRepo.save(loans);



        }

        return null;




    }

    public List<Loans> getAllItemsOwedByPatron(Patron p){


        return loanRepo.findAllByPatron(p);


    }

    public Optional<Loans> findLoanByID(Long id){


        return loanRepo.findById(id);
    }

    public List<LibraryItem> allOverDue(){


        List<Loans> allOverDue = loanRepo.findAllByLoanStatus(LoanStatus.OVERDUE);

        List<LibraryItem> allItemsOverdue = new ArrayList<>();

        for(Loans l: allOverDue){

            allItemsOverdue.add(l.getLibraryItem());



        }


        return allItemsOverdue;
    }

}
