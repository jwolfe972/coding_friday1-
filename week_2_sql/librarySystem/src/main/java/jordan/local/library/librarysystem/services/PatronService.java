package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.repos.PatronRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {


    private final PatronRepo patronRepo;

    @Autowired
    public PatronService(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }


    public Patron addPatron(String fname, String lname){

        Patron newPatron = new Patron(fname, lname);


        return patronRepo.save(newPatron);

    }

    public List<Patron> getAllPatrons(){


        return patronRepo.findAll();
    }


    public Optional<Patron> findPatronByID(Long id){



        return patronRepo.findById(id);
    }
}
