package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackSongSchedule extends AudioEventAdapter {



    public final AudioPlayer audioPlayer;
    public boolean repeating = false;
    public boolean shuffle = false;

    public final LinkedList<AudioTrack> playList;
    public int playListPosition = 0;

    public AudioTrack curTrack;

    public TrackSongSchedule(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.playList = new LinkedList<>();
    }


    public void queueSong(AudioTrack track){

        if(!this.audioPlayer.startTrack(track, true)){

            this.playList.offer(track);
        }
    }

    public void promoteToHead(AudioTrack track){


        if(!this.audioPlayer.startTrack(track, true)){

            this.playList.addFirst(track);
            
            System.out.println(this.playList.peek().getInfo().title);
        }






    }


    public void playNextTrack(){


        AudioTrack track = this.playList.poll();
        this.audioPlayer.startTrack(track,false );

    }

    public void playNextTrack(int value){

        AudioTrack track =  this.playList.get(value);
        this.playList.remove(value);

        this.audioPlayer.startTrack(track, false);



    }

    public void shuffleTrack(){


        if(this.playList.size() > 1){

            Random rand = new Random();


            int randValue = rand.nextInt(this.playList.size());
            AudioTrack track = this.playList.get(randValue);
            this.playList.remove(randValue);
            this.audioPlayer.startTrack(track, false);




        }else {

            playNextTrack();
        }

    }


    public void pauseTrack(){

        this.audioPlayer.setPaused(true);
    }

    public void playTrack(){

        this.audioPlayer.setPaused(false);
    }



    public LinkedList<AudioTrack> getFullList(){


        return this.playList;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext){

            if(this.repeating){

                this.audioPlayer.startTrack(track.makeClone(), false);
                return;
            }

            if(shuffle){

                shuffleTrack();
            }else {
                playNextTrack();
            }





        }
    }







}
