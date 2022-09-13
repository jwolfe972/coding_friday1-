package jordan.local.library.librarysystem.services;


import jordan.local.library.librarysystem.models.AudioCDs;
import jordan.local.library.librarysystem.models.ItemStatus;
import jordan.local.library.librarysystem.models.LibraryItem;
import jordan.local.library.librarysystem.repos.AudioCDRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AudioCDService {


    private final AudioCDRepo audioCDRepo;

    public AudioCDService(AudioCDRepo audioCDRepo) {
        this.audioCDRepo = audioCDRepo;
    }



    public LibraryItem addCD(String artist, String title, int numberOfTracks, Date releaseDate, String genre, double price, int days){


        AudioCDs cd = new AudioCDs(artist, title, numberOfTracks, releaseDate, genre , price, ItemStatus.IN_STOCK, days);



        return audioCDRepo.save(cd);
    }
}
