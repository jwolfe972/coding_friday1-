package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatronRepo extends JpaRepository<Patron, Long> {



    List<Patron> findAllByBalanceLessThan(double value);


    Patron findPatronById(Long id);




    List<Patron> findAllByPatronLastNameContainingIgnoreCase(String lname);








}
