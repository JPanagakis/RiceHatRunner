import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by Justin on 1/8/2016.
 */
public class Rock1 {

    private int x, y, width, height;
    private final int DY = 5;
    private Image image;

    private int[] rockPlaceY = {-100, -200, -300, -400, -500, -600};
    private int[] rockPlaceX = {0, 62, 124, 186, 248, 310, 372, 400};
    private Random random = new Random();

    public Rock1(){

        initRock1();
    }

    public void initRock1(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("images/rock1.png");

        ImageIcon ii = new ImageIcon(resource);
        image = ii.getImage();

        x = 200;
        y = -4500;

        getImageDimensions();
    }

    public void resetRock1(){

        x = 200;
        y = -4500;
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
        if (y < 720){
            y += DY;
        } else {
            y = rockPlaceY[random.nextInt(6)];
            x = rockPlaceX[random.nextInt(8)];
        }
    }

    public void getImageDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Rectangle getBounds(){ return new Rectangle(x, y, width, height); }
}
