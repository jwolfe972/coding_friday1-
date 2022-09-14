package jordan.local.library.librarysystem.models;


import jordan.local.library.librarysystem.enums.ItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "LibraryItem")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ItemType", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryItem {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long itemID;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ItemStatus itemStatus;


    @Column(name = "loanDays", nullable = false)
    private int loanDays;


    @Column(name = "releaseDate", nullable = false)
    private Date releaseDate;


    @Column(name = "title", nullable = false)
    private String title;




    public LibraryItem(double price, ItemStatus status, int days, Date releaseDate, String title){


        this.cost = price;
        this.itemStatus = status;
        this.loanDays = days;
        this.releaseDate = releaseDate;
        this.title = title;

    }














}
