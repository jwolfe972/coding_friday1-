package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Books, Long> {
}
