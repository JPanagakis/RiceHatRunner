import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by justin on 2/25/16.
 */
public class Pause {

    private URL pauseURL;
    private Image pauseImage;
    private int x, y;

    public Pause(){

        initPause();
    }

    public void initPause(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        pauseURL = classLoader.getResource("images/pause.png");

        ImageIcon ii = new ImageIcon(pauseURL);
        pauseImage = ii.getImage();

        x = 50;
        y = 125;
    }

    public Image getImage(){ return pauseImage; }

    public int getX(){ return x; }

    public int getY(){ return y; }
}
