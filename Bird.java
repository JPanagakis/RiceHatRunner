import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

/**
 * Created by justin on 1/15/16.
 */
public class Bird {

    private boolean leftBird = true;
    private int[] birdPlaceY = {-500, -425, -350, -250, -175, -100, -25, 50, 125, 200,};
    private int[] leftBirdPlaceX = {-50, -100, -150};
    private int[] rightBirdPlaceX = {550, 600, 650};
    private Random random = new Random();
    private int holdRandom = 0;

    private int x, y, width, height;
    private final int DLX = 2;
    private final int DRX = -2;
    private final int DY = 3;
    private Image imageRight;
    private Image imageLeft;

    public Bird(){

        initBird();
    }

    public void initBird(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resourcer = classLoader.getResource("images/bird1.png");
        URL resourcel = classLoader.getResource("images/bird2.png");

        ImageIcon iir = new ImageIcon(resourcer);
        ImageIcon iil = new ImageIcon(resourcel);

        imageRight = iir.getImage();
        imageLeft = iil.getImage();

        x = -50;
        y = 0;

        getImageDimensions();
    }

    public void resetBird(){

        x = -50;
        y = 0;
    }

    public Image getImage(){
        if (leftBird){
            return imageLeft;
        } else {
            return imageRight;
        }
    }

    public void getImageDimensions(){
        width = imageLeft.getWidth(null);
        height = imageLeft.getHeight(null);
    }

    public Rectangle getBounds(){ return new Rectangle(x, y, width, height); }

    public int getX(){return x;}
    public int getY(){return y;}

    public void move(){

        if (leftBird){
            if (x < 550){
                y += DY;
                x += DLX;
            } else {
                y = birdPlaceY[random.nextInt(10)];
                birdSwitch();
                if (leftBird){
                    x = leftBirdPlaceX[random.nextInt(3)];
                } else {
                    x = rightBirdPlaceX[random.nextInt(3)];
                }
            }
        } else {
            if (x > -200){
                y += DY;
                x += DRX;
            } else {
                y = birdPlaceY[random.nextInt(10)];
                birdSwitch();
                if (leftBird){
                    x = leftBirdPlaceX[random.nextInt(3)];
                } else {
                    x = rightBirdPlaceX[random.nextInt(3)];
                }
            }
        }
    }

    public void birdSwitch(){

        holdRandom = random.nextInt(2);
        if (holdRandom == 1){
            leftBird = false;
        } else {
            leftBird = true;
        }
    }
}
