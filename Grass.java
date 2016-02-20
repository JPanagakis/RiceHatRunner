import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Justin on 1/8/2016.
 */
public class Grass {

    private int x, y;
    private final int DY = 5;
    private Image image;

    public Grass(){

        initGrass();
    }

    public void initGrass(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("images/grass.png");

        ImageIcon ii = new ImageIcon(resource);
        image = ii.getImage();

        x = -100;
        y = -1400;
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

    public void move(){
        if (y < 0){
            y += DY;
        } else {
            y = -1400;
        }
    }
}
