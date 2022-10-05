package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackSongSchedule extends AudioEventAdapter {



    public final AudioPlayer audioPlayer;

    public final BlockingQueue<AudioTrack> playList;

    public TrackSongSchedule(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.playList = new LinkedBlockingQueue<>();
    }


    public void queueSong(AudioTrack track){

        if(!this.audioPlayer.startTrack(track, true)){

            this.playList.offer(track);
        }
    }


    public void playNextTrack(){

        this.audioPlayer.startTrack(this.playList.poll(),false );
    }


    public void pauseTrack(){

        this.audioPlayer.setPaused(true);
    }

    public void playTrack(){

        this.audioPlayer.setPaused(false);
    }

    public BlockingQueue<AudioTrack> getFullList(){


        return this.playList;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext){

                playNextTrack();



        }
    }





}
