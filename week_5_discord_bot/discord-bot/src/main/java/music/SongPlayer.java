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


import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

    public void setRepeat(TextChannel textChannel, boolean val){

        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());

        musicManager.trackSongSchedule.repeating = val;



    }

    public void skipSong(TextChannel textChannel){

        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        if(trackSongSchedule.shuffle){


            trackSongSchedule.shuffleTrack();
        }else{
            trackSongSchedule.playNextTrack();
        }










    }

    public void pauseSong(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        trackSongSchedule.pauseTrack();
    }

    public List<AudioTrack> getAllTracks(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;

        LinkedList<AudioTrack> audioTracks = trackSongSchedule.getFullList();


        List<AudioTrack> tracks = new ArrayList<>(audioTracks);


        return tracks;



    }

    public void playSong(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        trackSongSchedule.playTrack();
    }

    public void playNextTrack(TextChannel textChannel, int element){

        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());



        TrackSongSchedule trackSongSchedule = musicManager.trackSongSchedule;


        trackSongSchedule.playNextTrack(element);







    }





    public void loadToPlay(TextChannel textChannel, String youtubeURL, boolean playlist){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());


        this.audioPlayerManager.loadItemOrdered(musicManager, youtubeURL.trim(), new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {


                musicManager.trackSongSchedule.queueSong(audioTrack);



                textChannel.sendMessage(String.format("Adding song to queue**\nTitle: %s\n**by**\n%s\n\n%s", audioTrack.getInfo().title, audioTrack.getInfo().author,  audioTrack.getInfo().uri )).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {


                final List<AudioTrack> tracks = audioPlaylist.getTracks();


                if(playlist){


                    for(AudioTrack track: tracks){


                        musicManager.trackSongSchedule.queueSong(track);
                    }


                    textChannel.sendMessage(String.format("Adding %d songs to queue from %s playlist!", tracks.size(), audioPlaylist.getName())).queue();



                }else {


                    musicManager.trackSongSchedule.queueSong(tracks.get(0));
                    textChannel.sendMessage(String.format("Adding song to queue**\nTitle: %s\n**by**\n%s\n\n%s", tracks.get(0).getInfo().title, tracks.get(0).getInfo().author,  tracks.get(0).getInfo().uri )).queue();



                }



            }

            @Override
            public void noMatches() {

                textChannel.sendMessage("Couldnt find a track from here: " + youtubeURL).queue();

            }

            @Override
            public void loadFailed(FriendlyException e) {


                textChannel.sendMessage("Failed to load track: " + youtubeURL).queue();

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

    public void clearQueue(TextChannel textChannel){


        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());

        musicManager.trackSongSchedule.playList.clear();


    }

    public void setShuffle(TextChannel textChannel, boolean val){

        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());


        musicManager.trackSongSchedule.shuffle = val;



    }







}
