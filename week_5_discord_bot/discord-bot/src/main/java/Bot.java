import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import responders.PongListener;

import java.util.Arrays;

public class Bot {

    public String BOT_TOKEN = "BOT ID";
    private  JDA jda;
    private Guild guild;
    public  GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES};
    public Bot() throws InterruptedException {





            this.jda = giveBackJDA();

            this.guild = this.jda.getGuildById("GUID ID");
            if(guild != null){


                guild.upsertCommand("start", "start a game of wordle").queue();
                guild.upsertCommand("skipsong", "skips the current song in playlist").queue();
                guild.upsertCommand("pausesong", "pauses the song in the music channel").queue();
                guild.upsertCommand("playsong", "plays song in the music channel").queue();
                guild.upsertCommand("currentsong", "gives information on the current song being played").queue();
                guild.upsertCommand("listplaylist", "lists the contents of the entire playlist").queue();
                guild.upsertCommand("repeat", "repeats all tracks playing").queue();
                guild.upsertCommand("repeatoff", "turns off the repeat effect" ).queue();
                guild.upsertCommand("clear", "this command clears the current music queue").queue();
                guild.upsertCommand("shuffle", "shuffle the current playlist").queue();
                guild.upsertCommand("offshuffle", "turn the shuffle feature off").queue();

            }












    }


    public JDA giveBackJDA() throws InterruptedException {


        return JDABuilder.createDefault(BOT_TOKEN)
                .enableIntents(Arrays.asList(INTENTS))
                .enableCache(CacheFlag.VOICE_STATE)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Playing the best tunes"))
                .addEventListeners(new PongListener())
                .build().awaitReady();
    }
}
