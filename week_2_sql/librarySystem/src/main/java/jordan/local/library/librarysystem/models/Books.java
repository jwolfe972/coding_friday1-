package jordan.local.library.librarysystem.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Books")
@DiscriminatorValue(value = "BOOK")
public class Books extends LibraryItem {


    @Column(name = "authorFirstName", nullable = false)
    private String authorFirstName;



    @Column(name = "authorLastName", nullable = false)
    private String authorLastName;




    @Column(name = "bookGenre", nullable = false)
    private String bookGenre;


    public Books(String fname, String lname, String genre, double cost, ItemStatus status, int days, Date releaseDate, String title){


        super(cost, status, days, releaseDate, title);
        this.authorFirstName = fname;
        this.authorLastName = lname;
        this.bookGenre = genre;
    }




}
