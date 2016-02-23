import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by justin on 2/23/16.
 */
public class TitleScreen {

    private int x, y;
    private Image image;

    public TitleScreen(){

        initTitleScreen();
    }

    public void initTitleScreen(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("images/TitleScreen.png");

        ImageIcon ii = new ImageIcon(resource);
        image = ii.getImage();

        x = 0;
        y = 0;
    }

    public Image getImage(){
        return image;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
