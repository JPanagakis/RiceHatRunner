import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by justin on 2/22/16.
 */
public class SpeakerBox {

    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
    URL urlHolder;

    public SpeakerBox(URL url){

        urlHolder = url;
        initSpeakerBox(urlHolder);
    }

    public void initSpeakerBox(URL url){

        try {
            stream = AudioSystem.getAudioInputStream(url);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){

        try{
            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loop(int i){

        try{
            clip.loop(i);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){

        try{
            clip.stop();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void restart(){
        try {
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
