package responders;


import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import music.SongPlayer;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

public class PongListener extends ListenerAdapter {




    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();

        String [] wordArgs = content.split(" ");


        if(wordArgs.length < 2 || !wordArgs[0].equals("!play")){

            event.getChannel().sendMessage("Incorrect usage!!\n Ex: '!play <name of song and artist>'").queue();



        }else {




            if(!event.getMember().getVoiceState().inAudioChannel()){


                event.getChannel().sendMessage("You need to be in a voice channel for this command to work").queue();
            }else {


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

                SongPlayer.getInstance().playSong(event.getGuild().getTextChannelById("CHAT ID"));
                SongPlayer.getInstance().loadToPlay(event.getGuild().getTextChannelById("CHAT ID"), link );



            }



        }


    }


    public boolean isURL(String link){



        try {

            new URI(link);
            return true;

        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
//        if (event.getName().equals("start")) {
//            event.reply(String.format("Beep boop starting wordle game the word for game is %s", getRandomElement() )).queue();
//        }

        if(!event.getMember().getVoiceState().inAudioChannel()){


            event.reply("Must be in the voice channel to use this bot").queue();
        }else {


            switch (event.getName()){


                case "skipsong":
                    SongPlayer.getInstance().skipSong(event.getGuild().getTextChannelById("CHAT ID"));
                    SongPlayer.getInstance().playSong(event.getGuild().getTextChannelById("CHAT ID"));
                    AudioTrack currentTrack = SongPlayer.getInstance().getCurrentTrack(event.getGuild().getTextChannelById("CHAT ID"));
                    event.reply("Song has been skipped!\nNow playing: "+ currentTrack.getInfo().title + " by: " + currentTrack.getInfo().author ).queue();
                    break;
                case "pausesong":
                    SongPlayer.getInstance().pauseSong(event.getGuild().getTextChannelById("CHAT ID"));
                    event.reply("Song has been paused").queue();
                    break;
                case "playsong":
                    SongPlayer.getInstance().playSong(event.getGuild().getTextChannelById("CHAT ID"));
                    event.reply("Song has resumed playing").queue();
                    break;
                case "currentsong":
                     currentTrack = SongPlayer.getInstance().getCurrentTrack(event.getGuild().getTextChannelById("CHAT ID"));

                    if(currentTrack != null){

                        event.reply(String.format("**Current Track**\n%s\nby:**%s", currentTrack.getInfo().title, currentTrack.getInfo().author )).queue();
                    }else {

                        event.reply("No song is currently playing").queue();
                    }

                    break;
                case "listplaylist":
                    List<AudioTrack> tracks = SongPlayer.getInstance().getAllTracks(event.getGuild().getTextChannelById("CHAT ID"));
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


                default:
                    event.reply("Invalid command").queue();



            }



        }






    }




}
