import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * Created by Justin on 1/7/2016.
 */
public class Player implements ActionListener {

    private int dx, dy, x, y, width, height, invincibleCounter;
    private Image image;
    private Timer timer;
    private final int DELAY = 100;
    private boolean running = true;
    private boolean vis;
    private boolean invincible;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL resourcef1 = classLoader.getResource("images/player.png");
    URL resourcef2 = classLoader.getResource("images/player1.png");
    URL resourcef3 = classLoader.getResource("images/playerinvincible.png");
    URL resourcef4 = classLoader.getResource("images/playerinvincible1.png");

    private ImageIcon frame1 = new ImageIcon(resourcef1);
    private ImageIcon frame2 = new ImageIcon(resourcef2);
    private ImageIcon frame3 = new ImageIcon(resourcef3);
    private ImageIcon frame4 = new ImageIcon(resourcef4);

    public Player() {

        initPlayer();
    }

    private void initPlayer() {

        vis = true;
        invincible = false;
        invincibleCounter = 0;

        ImageIcon ii = new ImageIcon(resourcef1);
        image = ii.getImage();

        timer = new Timer(DELAY, this);
        timer.start();

        x = 205;
        y = 550;

        getImageDimensions();
    }

    public void resetPlayer(){

        x = 205;
        y = 550;
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public void getImageDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Rectangle getBounds(){ return new Rectangle(x, y, width, height - 5); }

    public boolean isVisible(){ return vis; }

    public boolean isInvincible(){ return invincible; }

    public void setInvincible(boolean b){ invincible = b; }

    public void setVisible(Boolean visible){ vis = visible; }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImage(){
        return image;
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            if (x < 0){x = 0;}
            if (x > 0) {
                dx = -6;
            } else {
                dx = 0;
            }
        }

        if (key == KeyEvent.VK_RIGHT){
            if (x > 415){x = 415;}
            if (x < 415) {
                dx = 6;
            } else {
                dx = 0;
            }
        }

        if (key == KeyEvent.VK_UP) {
            if (y < 0){y = 0;}
            if (y > 0) {
                dy = -3;
            } else {
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_DOWN){
            if (y > 600){y = 600;}
            if (y < 600) {
                dy = 5;
            } else {
                dy = 0;
            }
        }
    }

    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            dx = 0;
            if (x < 0){
                x = 0;
            }
        }

        if (key == KeyEvent.VK_RIGHT){
            dx = 0;
            if (x > 415){
                x = 415;
            }
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            if (y < 0){
                y = 0;
            }
        }

        if (key == KeyEvent.VK_DOWN){
            dy = 0;
            if (y > 600){
                y = 600;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (invincible){
            if (invincibleCounter < 5){
                invincibleCounter++;
                if (running){
                    image = frame4.getImage();
                    running = false;
                } else {
                    image = frame3.getImage();
                    running = true;
                }
            } else {
                invincible = false;
                invincibleCounter = 0;
            }
        } else if (running){
            image = frame2.getImage();
            running = false;
        } else {
            image = frame1.getImage();
            running = true;
        }

    }
}
