import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by justin on 1/15/16.
 */
public class CountDown {

    private Image count3;
    private Image count2;
    private Image count1;
    private Image count0;

    private int x = 100;
    private int y = 200;

    public CountDown(){

        initCountDown();
    }

    public void initCountDown(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource3 = classLoader.getResource("images/count3.png");
        URL resource2 = classLoader.getResource("images/count2.png");
        URL resource1 = classLoader.getResource("images/count1.png");
        URL resource0 = classLoader.getResource("images/count0.png");

        ImageIcon ii3 = new ImageIcon(resource3);
        ImageIcon ii2 = new ImageIcon(resource2);
        ImageIcon ii1 = new ImageIcon(resource1);
        ImageIcon ii0 = new ImageIcon(resource0);

        count3 = ii3.getImage();
        count2 = ii2.getImage();
        count1 = ii1.getImage();
        count0 = ii0.getImage();
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public Image getImage3(){
        return count3;
    }
    public Image getImage2(){
        return count2;
    }
    public Image getImage1(){
        return count1;
    }
    public Image getImage0(){
        return count0;
    }
}
