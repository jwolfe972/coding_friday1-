package jordan.local.library.librarysystem.repos;

import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.models.AudioCDs;
import jordan.local.library.librarysystem.models.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryItemRepo extends JpaRepository<LibraryItem, Long> {



    LibraryItem findLibraryItemByItemID(Long id);


    List<LibraryItem> findAllByItemStatus(ItemStatus status);


}
