package music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class SongPlayer {

    private static SongPlayer songPlayer_instance;
    private final Map<Long, GuildMusicManager> musicManagerMap;
    private final AudioPlayerManager audioPlayerManager;


    public SongPlayer(){

        this.musicManagerMap = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild){

        return this.musicManagerMap.computeIfAbsent(guild.getIdLong(), (guildId) -> {

            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
            return guildMusicManager;
        } );
    }

    public void skipSong(TextChannel textChannel){

        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;




        trackSongSchedule.playNextTrack();





    }

    public void pauseSong(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        trackSongSchedule.pauseTrack();
    }

    public List<AudioTrack> getAllTracks(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;

        BlockingQueue<AudioTrack> audioTracks = trackSongSchedule.getFullList();


        List<AudioTrack> tracks = new ArrayList<>(audioTracks);


        return tracks;



    }

    public void playSong(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        trackSongSchedule.playTrack();
    }





    public void loadToPlay(TextChannel textChannel, String youtubeURL){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());


        this.audioPlayerManager.loadItemOrdered(musicManager, youtubeURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.trackSongSchedule.queueSong(audioTrack);

                textChannel.sendMessage(String.format("Adding song to queue**\nTitle: %s\n**by**\n%s", audioTrack.getInfo().title, audioTrack.getInfo().author )).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {


                final List<AudioTrack> tracks = audioPlaylist.getTracks();

                if(!tracks.isEmpty()){

                    musicManager.trackSongSchedule.queueSong(tracks.get(0));
                    textChannel.sendMessage(String.format("Adding song to queue**\nTitle: %s\n**by**\n%s", tracks.get(0).getInfo().title, tracks.get(0).getInfo().author )).queue();
                }

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }





        });
    }


    public static SongPlayer getInstance(){

        if(songPlayer_instance == null){


            songPlayer_instance = new SongPlayer();
        }


        return songPlayer_instance;
    }

    public AudioTrack getCurrentTrack(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());

        return musicManager.audioPlayer.getPlayingTrack();
    }







}
