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
@Entity(name = "AudioCDs")
@DiscriminatorValue(value = "CD")
public class AudioCDs extends LibraryItem{

    @Column(name = "artist", nullable = false)
    private String artist;


    @Column(name = "numberOfTracks", nullable = false)
    private int numberOfTracks;

    @Column(name = "genre", nullable = false)
    private String genre;


    public AudioCDs(String artist, String title, int numberOfTracks, Date releaseDate, String genre, double price, ItemStatus status, int days){


        super(price, status, days, releaseDate, title);
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
        this.genre = genre;
    }




}
