package utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    // a map of file names to audio clips
    private static final Map<String, Clip> audioClips = new HashMap<>();

    // play the audio file at the given file location
    public static void playAudio(String fileLocation) {
        try {
            // get the audio input stream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileLocation));

            // get the audio format of the audio input stream
            AudioFormat audioFormat = audioInputStream.getFormat();

            // create a data line info object with the audio format and a line available with that format
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);

            // create a new clip
            Clip clip = (Clip) AudioSystem.getLine(info);

            // store the clip in the map
            audioClips.put(fileLocation, clip);

            // open the audio input stream and start the clip
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    // stop the audio file being played at the given file location
    public static void stopAudio(String fileLocation) {
        Clip clip = audioClips.get(fileLocation);
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}