package utilities;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {
  public static void playAudio(String[] fileLocations) {
    Clip[] audioClips = new Clip[fileLocations.length];

    try {
      for (int i = 0; i < fileLocations.length; i++) {
        File audioFile = new File(fileLocations[i]);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClips[i] = (Clip) AudioSystem.getLine(info);
        audioClips[i].open(audioStream);
      }

      for (Clip audioClip : audioClips) {
        audioClip.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

// Implementation
// String[] fileLocations = { "file/location/1.mp3", "file/location/2.mp3" };
// AudioPlayer.playAudio(fileLocations);