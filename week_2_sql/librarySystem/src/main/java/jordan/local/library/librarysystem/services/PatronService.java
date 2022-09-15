package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.enums.PatronStatus;
import jordan.local.library.librarysystem.models.Patron;
import jordan.local.library.librarysystem.repos.PatronRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatronService {


    private final PatronRepo patronRepo;

    @Autowired
    public PatronService(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }


    public Patron addPatron(Patron p){



        return patronRepo.save(p);

    }


    public Patron updatePatron(Patron p){

//
//        p.setPatronFirstName(fname);
//        p.setPatronLastName(lname);
        return patronRepo.save(p);
    }

    public List<Patron> getAllPatrons(){


        return patronRepo.findAll();
    }


    public Optional<Patron> findPatronByID(Long id){



        return patronRepo.findById(id);
    }


    public Patron deactivatePatron(Patron p){


        p.setInUse(false);


        return patronRepo.save(p);
    }

    public Patron editName(Patron p, String fname, String lname){


        p.setPatronFirstName(fname);
        p.setPatronLastName(lname);

        return patronRepo.save(p);


    }

    public Patron editBalance(Patron p, double balance){

        p.setBalance(balance);


//
//        if(balance == 0.00){
//
//
//            p.setPatronStatus(PatronStatus.NORMAL);
//        }

        return patronRepo.save(p);



    }

    public double getAfterPaymentBalance(double balance, double paymentAmount){



        return paymentAmount - balance;







    }


    public Map<Patron, Double> payFines(Patron p, double payment){

        Map<Patron, Double> returnOut = new HashMap<>();

        double amountAfter = getAfterPaymentBalance(p.getBalance(), payment);



        if(amountAfter > 0.00){


            p.setBalance(amountAfter);
            Patron retP = patronRepo.save(p);

            returnOut.put(retP, (amountAfter*-1) );


            return returnOut;




        }else  {

            p.setBalance(0.00);
            p.setPatronStatus(PatronStatus.NORMAL);



            Patron returnP = patronRepo.save(p);

            returnOut.put(returnP, (amountAfter * -1));


            return returnOut;



        }


    }



    public List<Patron> findAllNegativeBalance(){




        return patronRepo.findAllByBalanceLessThan(0.00);

    }





}
