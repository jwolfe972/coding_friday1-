package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.models.AudioCDs;
import jordan.local.library.librarysystem.models.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryItemRepo extends JpaRepository<LibraryItem, Long> {



    LibraryItem findLibraryItemByItemID(Long id);

}
