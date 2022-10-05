package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import org.jetbrains.annotations.Nullable;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class AudioSendHandler implements net.dv8tion.jda.api.audio.AudioSendHandler {

    private final AudioPlayer audioPlayer;

    private final ByteBuffer byteBuffer;

    private final MutableAudioFrame mutableAudioFrame;

    public AudioSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.byteBuffer = ByteBuffer.allocate(1024);
        this.mutableAudioFrame = new MutableAudioFrame();
        this.mutableAudioFrame.setBuffer(byteBuffer);
    }


    @Override
    public boolean canProvide() {
        return this.audioPlayer.provide(this.mutableAudioFrame);
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        final Buffer buffer = ((Buffer) this.byteBuffer).flip();
        return (ByteBuffer) buffer;

    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
