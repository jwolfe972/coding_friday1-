package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.enums.LoanStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.models.Loans;
import jordan.local.library.librarysystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loans, Long> {


//    Loans findLoansByLibraryItemAndPatronAndLoanStatusNotContaining(LibraryItem item, Patron p, LoanStatus loanStatus);
// %?3%

    @Query(value = "  SELECT * FROM loans WHERE patron_id = ?1\n  " +
            "  AND library_item_itemid = ?2  \n" +
            "  and `status` <> ?3  ;", nativeQuery = true)
    Loans findCheckoutLoan(Long patronID, Long itemID, int status);


    List<Loans> findAllByPatron(Patron patron);


    List<Loans> findAllByLoanStatus(LoanStatus loanStatus);
}
