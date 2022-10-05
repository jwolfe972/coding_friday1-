package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {


    public final AudioPlayer audioPlayer;
    public final TrackSongSchedule trackSongSchedule;
    private final AudioSendHandler sendHandler;


    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.trackSongSchedule = new TrackSongSchedule(this.audioPlayer);
        this.audioPlayer.addListener(this.trackSongSchedule);
        this.sendHandler = new AudioSendHandler(this.audioPlayer);
    }


    public AudioSendHandler getSendHandler(){


        return this.sendHandler;
    }
}
