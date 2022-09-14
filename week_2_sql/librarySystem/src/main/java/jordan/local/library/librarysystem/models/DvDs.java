package jordan.local.library.librarysystem.models;


import jordan.local.library.librarysystem.enums.ItemStatus;
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
@Entity(name = "DvDs")
@DiscriminatorValue(value = "DVD")
public class DvDs extends LibraryItem {


    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "runTime", nullable = false)
    private int runTime;

    @Column(name = "studioName", nullable = false)
    private String studioName;


    public DvDs(String category, int runTime, String studioName, double price, ItemStatus status, int days, Date releaseDate,
                String title){

        super(price, status, days, releaseDate, title);


        this.category = category;
        this.runTime = runTime;
        this.studioName = studioName;
    }






}
