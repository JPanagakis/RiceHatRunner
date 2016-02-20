import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by justin on 1/14/16.
 */
public class Lives {

    private int x, y;

    private Image image2;
    private Image image1;
    private Image image0;
    private Image image00;

    public Lives(){

        initLives();
    }

    public void initLives(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource2 = classLoader.getResource("images/playerlives2.png");
        URL resource1 = classLoader.getResource("images/playerlives1.png");
        URL resource0 = classLoader.getResource("images/playerlives0.png");
        URL resource00 = classLoader.getResource("images/playerdead.png");

        ImageIcon ii2 = new ImageIcon(resource2);
        ImageIcon ii1 = new ImageIcon(resource1);
        ImageIcon ii0 = new ImageIcon(resource0);
        ImageIcon ii00 = new ImageIcon(resource00);

        image2 = ii2.getImage();
        image1 = ii1.getImage();
        image0 = ii0.getImage();
        image00 = ii00.getImage();

        x = 0;
        y = 200;
    }

    public Image getImage2(){return image2;}
    public Image getImage1(){return image1;}
    public Image getImage0(){return image0;}
    public Image getImage00(){return image00;}

    public int getX(){return x;}
    public int getY(){return y;}
}
