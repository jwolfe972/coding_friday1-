package jordan.local.library.librarysystem.models;


import jordan.local.library.librarysystem.enums.ItemStatus;
import jordan.local.library.librarysystem.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Loans")
public class Loans {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanID;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "libraryItem.itemID", nullable = false)
    private LibraryItem libraryItem;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patron.id", nullable = false)
    private Patron patron;


    @Column(name = "loanDueDate", nullable = false)
    private Date loanDueDate;


    @Column(name = "status", nullable = false )
    private LoanStatus loanStatus;


    @Column(name = "numberOfRechecks", nullable = false)
    private int numberOfRechecks = 1;


    public Loans(LibraryItem item, Patron p, Date d){


        this.libraryItem = item;
        this.patron = p;
        this.loanDueDate = d;
        this.loanStatus = LoanStatus.NORMAL;
    }












}
