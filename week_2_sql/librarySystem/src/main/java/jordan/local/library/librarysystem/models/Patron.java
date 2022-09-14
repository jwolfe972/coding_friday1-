package jordan.local.library.librarysystem.models;


import jordan.local.library.librarysystem.enums.PatronStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Patron")

@NoArgsConstructor
@Getter
@Setter
public class Patron {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(name = "patronFirstName", nullable = false)
    private String patronFirstName;


    @Column(name = "patronLastName", nullable = false)
    private String patronLastName;

    @Column(name = "balance", nullable = false)
    private double balance = 0.00;


    @Column(name = "numOfItemsRented" , nullable = false)
    private int numberOfRentedItems = 0;


    @Column(name = "patronStatus", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PatronStatus patronStatus = PatronStatus.NORMAL;

    public Patron(String fname, String lname){


        this.patronFirstName = fname;
        this.patronLastName = lname;

    }




}
