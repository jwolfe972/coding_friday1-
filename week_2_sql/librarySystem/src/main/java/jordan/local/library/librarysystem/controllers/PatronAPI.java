package jordan.local.library.librarysystem.controllers;


import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.services.PatronService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/db/patron/v1")
public class PatronAPI {


    private final PatronService patronService;


    public PatronAPI(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping("/add-patron")
    public ResponseEntity<Patron> addPatron(@RequestBody Patron p){



        Patron rP = patronService.addPatron(p);



        return new ResponseEntity<>(rP, HttpStatus.OK);




    }


    @GetMapping("/list-all")
    public ResponseEntity<List<Patron>> getAllPatron(){


        return new ResponseEntity<>(patronService.getAllPatrons(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getPatronByID(@PathVariable("id") Long id){


        Optional<Patron> p = patronService.findPatronByID(id);


        if(p.isPresent()){



            return new ResponseEntity<>(p.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<?> deactivatePatron( @RequestBody Patron p){



        Optional<Patron> rP = patronService.findPatronByID(p.getId());


        if(rP.isPresent()){



           Patron rt =  patronService.deactivatePatron(rP.get());

           return new ResponseEntity<>(rt, HttpStatus.OK);


        }


        return new ResponseEntity<>("Failed to deactivate account with id of " + p.getId() + ".", HttpStatus.OK);



    }


    @PutMapping("/update-patron")
    public ResponseEntity<?> editName(@RequestBody Patron p){

        if (patronService.findPatronByID(p.getId()).isPresent()){


           Patron uP =  patronService.updatePatron(p);


           return new ResponseEntity<>(uP, HttpStatus.OK);
        }


        return new ResponseEntity<>("Invalid user information", HttpStatus.OK);



    }

    @PutMapping("/edit-balance")
    public ResponseEntity<?> editBalance(@RequestParam("patronID") Long patronID, @RequestParam("balance") double balance){

        Optional<Patron> patron = patronService.findPatronByID(patronID);



        if(patron.isPresent()){



            Patron p = patronService.editBalance(patron.get(), balance);



            return new ResponseEntity<>(p, HttpStatus.OK);






        }




        return new ResponseEntity<>("Operation to edit balance failed", HttpStatus.OK);




    }



    @PutMapping("/edit-name")
    public ResponseEntity<?> updatePatron(@RequestParam(value = "id", required = true) Long id, @RequestParam("firstName") String fname,
                                          @RequestParam(value = "lastName") String lname){


        Optional<Patron> found = patronService.findPatronByID(id);

        if(found.isEmpty()){


            return new ResponseEntity<>("Patron not found", HttpStatus.BAD_REQUEST);



        }


        Patron out = patronService.editName(found.get(), fname, lname);


        return new ResponseEntity<>(out, HttpStatus.OK);



    }




    @PostMapping("/pay-balance")
    public ResponseEntity<?> payBalance(@RequestParam("patronID") Long id, @RequestParam("pay")double paymentAmount){



        Optional<Patron> patron = patronService.findPatronByID(id);


        if(patron.isPresent()){



            String response = patronService.payFines(patron.get(), paymentAmount);


            return new ResponseEntity<>(response, HttpStatus.OK);
        }



        return new ResponseEntity<>("Payment failed", HttpStatus.OK);
    }


}
