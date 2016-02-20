import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

/**
 * Created by justin on 1/14/16.
 */
public class Rock2 {

    private int x, y, width, height;
    private final int DY = 5;
    private Image image;

    private int[] rockPlace = {0, 124, 248, 360};
    private Random random = new Random();

    public Rock2(){

        initRock1();
    }

    public void initRock1(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("images/rock2.png");

        ImageIcon ii = new ImageIcon(resource);
        image = ii.getImage();

        x = 200;
        y = -100;

        getImageDimensions();
    }

    public void resetRock2(){

        x = 200;
        y = -100;
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

    public void getImageDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Rectangle getBounds(){ return new Rectangle(x, y, width, height); }

    public void move(){
        if (y < 720){
            y += DY;
        } else {
            y = -500;
            x = rockPlace[random.nextInt(4)];
        }
    }
}
