package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.enums.LoanStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.models.Loans;
import jordan.local.library.librarysystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loans, Long> {


    Loans findLoansByLibraryItemAndPatronAndLoanStatusNotContaining(LibraryItem item, Patron p, LoanStatus loanStatus);


    List<Loans> findAllByPatron(Patron patron);


    List<Loans> findAllByLoanStatus(LoanStatus loanStatus);
}
