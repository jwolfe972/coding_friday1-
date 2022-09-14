package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.models.DvDs;
import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.repos.DvDRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DvDService {


    private final DvDRepo dvDRepo;

    public DvDService(DvDRepo dvDRepo) {
        this.dvDRepo = dvDRepo;
    }

    public LibraryItem addDvdDvDs(String category, int runTime, String studioName, double price, ItemStatus status, int days, Date releaseDate,
                                  String title){


        DvDs dvd = new DvDs(category, runTime, studioName, price, status, days, releaseDate, title);


        return dvDRepo.save(dvd);



    }

    public List<DvDs> getAllDvDs(){


        return dvDRepo.findAll();
    }
}
