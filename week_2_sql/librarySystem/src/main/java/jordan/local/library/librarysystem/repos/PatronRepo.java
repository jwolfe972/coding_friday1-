package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepo extends JpaRepository<Patron, Long> {


}
