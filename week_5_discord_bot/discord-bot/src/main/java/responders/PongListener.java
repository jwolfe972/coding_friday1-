package responders;


import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import music.SongPlayer;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;


import java.net.URL;
import java.util.List;
import java.util.Random;

public class PongListener extends ListenerAdapter {




    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {


        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();

        String [] wordArgs = content.split(" ");



        switch (wordArgs[0]){

            case "!play":


                if(wordArgs.length < 2 ){

                    event.getChannel().sendMessage("Incorrect usage!!\n Ex: '!play <name of song and artist>'").queue();



                }else if(!event.getMember().getVoiceState().inAudioChannel()){


                    event.getChannel().sendMessage("Must be in the music voice channel to listen to and issue music commands").queue();
                }

                else {

                    final AudioManager audioManager = event.getGuild().getAudioManager();
                    final VoiceChannel voiceChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();


                    audioManager.openAudioConnection(voiceChannel);


                    String link = "";

                    for(int i = 1; i < wordArgs.length; ++i){

                        link += wordArgs[i] + " ";



                    }

                    if (!isURL(link)){

                        link = "ytsearch:" + link + " audio";
                    }


                    List<TextChannel> musicCmdChannels = event.getGuild().getTextChannelsByName("music-commands", true);


                    if(musicCmdChannels.size() != 1){


                        event.getChannel().sendMessage("Commands for music must come from a single channel called '#music-commands'").queue();
                    }else {



                        SongPlayer.getInstance().playSong(musicCmdChannels.get(0));
                        SongPlayer.getInstance().loadToPlay(musicCmdChannels.get(0), link, isURL(link));
                    }

                }
            break;


            case "!track":
                if(wordArgs.length == 2 ){

                    if(isNumeric(wordArgs[1])){


                        if(SongPlayer.getInstance().getMusicManager(event.getGuild()).trackSongSchedule.playList.size() > Integer.parseInt(wordArgs[1])){



                            SongPlayer.getInstance().getMusicManager(event.getGuild()).trackSongSchedule.playNextTrack(Integer.parseInt(wordArgs[1]));
                            List<TextChannel> musicCmdChannels = event.getGuild().getTextChannelsByName("music-commands", true);
                            AudioTrack currentTrack = SongPlayer.getInstance().getCurrentTrack(musicCmdChannels.get(0));

                            if(currentTrack != null){


                                event.getChannel().sendMessage(String.format("***Jumped to to track #%d\n***/%s by %s\n%s", Integer.parseInt(wordArgs[1]), currentTrack.getInfo().title, currentTrack.getInfo().author, currentTrack.getInfo().uri )).queue();
                            }


                        }else {


                            event.getChannel().sendMessage("Integer Value for track too large!").queue();
                        }

                    }else {

                        event.getChannel().sendMessage("Second argument has to be a numeric").queue();
                    }

                }else {

                    event.getChannel().sendMessage("Incorrect Usage!! Example '!track <number in playlist>'").queue();
                }
                break;
            case "!sleep":

                if(!event.getMember().getVoiceState().inAudioChannel()){


                    event.getChannel().sendMessage("Must be in the music voice channel to listen to and issue music commands").queue();
                }

                final AudioManager audioManager = event.getGuild().getAudioManager();

                audioManager.closeAudioConnection();

                event.getChannel().sendMessage("I have left the audio channel to sleep :sleeping:").queue();
                break;


            default:
                break;



        }




    }


    public boolean isURL(String link){



        try {

            new URL(link).toURI();
            return true;

        } catch (Exception e){

            return false;
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
//        if (event.getName().equals("start")) {
//            event.reply(String.format("Beep boop starting wordle game the word for game is %s", getRandomElement() )).queue();
//        }

        if(!event.getMember().getVoiceState().inAudioChannel()){


            event.reply("Must be in the voice channel to use this bot").queue();
        }else {



            List<TextChannel> musicCmdChannels = event.getGuild().getTextChannelsByName("music-commands", true);


            if(musicCmdChannels.size() != 1){


                event.getChannel().sendMessage("Commands for music must come from a single channel called '#music-commands'").queue();
            }else {





                switch (event.getName()){


                    case "skipsong":
                        SongPlayer.getInstance().skipSong(musicCmdChannels.get(0));
                        SongPlayer.getInstance().playSong(musicCmdChannels.get(0));
                        AudioTrack currentTrack = SongPlayer.getInstance().getCurrentTrack(musicCmdChannels.get(0));
                        if(currentTrack != null){
                            event.reply("Song has been skipped!\nNow playing: "+ currentTrack.getInfo().title + " by: " + currentTrack.getInfo().author ).queue();
                        }   else {
                            event.reply("Song has been skipped!").queue();
                        }

                        break;
                    case "pausesong":
                        SongPlayer.getInstance().pauseSong(musicCmdChannels.get(0));
                        event.reply("Song has been paused").queue();
                        break;
                    case "playsong":
                        SongPlayer.getInstance().playSong(musicCmdChannels.get(0));
                        event.reply("Song has resumed playing").queue();
                        break;
                    case "currentsong":
                        currentTrack = SongPlayer.getInstance().getCurrentTrack(musicCmdChannels.get(0));

                        if(currentTrack != null){

                            event.reply(String.format("**Current Track**\n%s\nby:**%s\n\n%s", currentTrack.getInfo().title, currentTrack.getInfo().author, currentTrack.getInfo().uri )).queue();
                        }else {

                            event.reply("No song is currently playing").queue();
                        }

                        break;
                    case "listplaylist":
                        List<AudioTrack> tracks = SongPlayer.getInstance().getAllTracks(musicCmdChannels.get(0));
                        String msg = "";
                        int count = 0;

                        if(tracks.size() != 0){

                            for(AudioTrack audioTrack: tracks){

                                msg += String.format("%d.  %s by %s\n", count, audioTrack.getInfo().title, audioTrack.getInfo().author);
                                count+=1;
                            }

                            event.reply(msg).queue();


                        }else {

                            event.reply("Playlist is empty... Add songs!").queue();
                        }
                        break;
                    case "repeat":
                        SongPlayer.getInstance().setRepeat(musicCmdChannels.get(0), true);
                        event.reply("Reating turned on").queue();
                        break;

                    case "repeatoff":
                        SongPlayer.getInstance().setRepeat(musicCmdChannels.get(0), false);
                        event.reply("Repeating turned off").queue();
                        break;

                    case "clear":
                        SongPlayer.getInstance().clearQueue(musicCmdChannels.get(0));
                        event.reply("Music Queue has now been cleared!!").queue();
                        break;
                    case "shuffle":
                        SongPlayer.getInstance().setShuffle(musicCmdChannels.get(0), true );
                        event.reply("Shuffle turned ON").queue();
                        break;
                    case "offshuffle":
                        SongPlayer.getInstance().setShuffle(musicCmdChannels.get(0), false );
                        event.reply("Shuffle turned OFF").queue();
                        break;


                        


                    default:
                        event.reply("Invalid command").queue();





                }






            }








        }






    }




}
