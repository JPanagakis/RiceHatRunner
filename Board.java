import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * Created by Justin on 1/7/2016.
 */
public class Board extends JPanel implements ActionListener{

    private Timer timer;
    private URL punchURL;
    private URL mainBgmURL;
    private URL gameOverBgmURL;
    private URL titleBgmUrl;
    private SpeakerBox bgm;
    private SpeakerBox gameOverBgm;
    private SpeakerBox titleBgm;
    private TitleScreen titleScreen;
    private Pause pausePicture;
    private Grass grass;
    private Player player;
    private CountDown countDown;
    private Rock1 rock1;
    private Rock1 rock12;
    private Rock2 rock2;
    private Bird bird;
    private Bird bird2;
    private boolean gameOver;
    private boolean title;
    private boolean paused;
    private final int DELAY = 5;
    private int difficulty;
    private final int difficulty1 = 15000;
    private final int difficulty2 = 30000;
    private final int difficulty3 = 45000;
    private final int difficulty4 = 60000;
    private int numberOfLives;
    private String scoreBoardString;

    public Board(){

        initBoard();
    }

    private void initBoard(){

        addKeyListener(new TAdapter());
        addKeyListener(new TryAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(500, 700));

        titleScreen = new TitleScreen();
        pausePicture = new Pause();

        grass = new Grass();
        player = new Player();
        countDown = new CountDown();
        rock2 = new Rock2();
        rock1 = new Rock1();
        rock12 = new Rock1();
        bird = new Bird();
        bird2 = new Bird();

        title = true;
        gameOver = false;
        paused = false;

        numberOfLives = 2;
        difficulty = -4000;

        timer = new Timer(DELAY, this);


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        punchURL = classLoader.getResource("sound/punch.wav");
        titleBgmUrl = classLoader.getResource("sound/Sea.wav");
        mainBgmURL = classLoader.getResource("sound/Genocide.wav");
        gameOverBgmURL = classLoader.getResource("sound/Rain.wav");

        titleBgm = new SpeakerBox(titleBgmUrl);
        gameOverBgm = new SpeakerBox(gameOverBgmURL);
        bgm = new SpeakerBox(mainBgmURL);

        titleBgm.start();
    }

    public int getNumberOfLives(){ return numberOfLives; }

    public boolean getGameOver(){ return gameOver; }

    public boolean getTitle(){ return title; }

    public boolean getPaused(){ return paused; }

    public void setScoreBoardString(String s){ scoreBoardString = s; }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (title){
            drawTitle(g);
        } else if (!gameOver) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawTitle(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(titleScreen.getImage(), titleScreen.getX(), titleScreen.getY(), this);
    }

    private void drawObjects(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(grass.getImage(), grass.getX(), grass.getY(), this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        g2d.drawImage(rock1.getImage(), rock1.getX(), rock1.getY(), this);

        if (paused){
            g2d.drawImage(pausePicture.getImage(), pausePicture.getX(), pausePicture.getY(), this);
            timer.stop();
        }

        startCountDown(g2d);

        updateDifficulty(g2d);
    }

    private void drawGameOver(Graphics g){

        bgm.stop();
        String msg = "Game Over";
        String msg1 = "Score: " + scoreBoardString;
        String msg2 = "Press Space to Try Again!";
        Font gO = new Font("Helvetica", Font.BOLD, 40);
        Font sC = new Font("Helvetica", Font.BOLD, 30);
        Font tA = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(gO);
        FontMetrics fm1 = getFontMetrics(sC);
        FontMetrics fm2 = getFontMetrics(tA);

        g.setColor(Color.WHITE);
        g.setFont(gO);
        g.drawString(msg, (500 - fm.stringWidth(msg)) / 2, 250);
        g.setFont(sC);
        g.drawString(msg1, (500 - fm1.stringWidth(msg1)) / 2, 300);
        g.setFont(tA);
        g.drawString(msg2, (500 - fm2.stringWidth(msg2)) / 2, 500);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        checkGameOver();
        normalMove();
        checkCollisions();

        if (!title && !paused) {
            difficulty += DELAY;
        }
        repaint();
    }

    public void checkGameOver(){

        if (numberOfLives < 0){
            gameOver = true;
        }

        if (gameOver){
            timer.stop();
            gameOverBgm.restart();
        }
    }

    public void tryAgain(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE){

            if (gameOver) {
                restart();
            } else if (title){
                title = false;
                timer.start();
                titleBgm.stop();
                bgm.start();
            } else {
                if (!paused) {
                    paused = true;
                    bgm.stop();
                } else {
                    timer.start();
                    paused = false;
                    bgm.start();
                }
            }
        }
    }

    public void restart(){

        gameOverBgm.stop();
        bgm.restart();
        gameOver = false;
        numberOfLives = 2;
        difficulty = -4000;
        player.resetPlayer();
        rock1.resetRock1();
        rock12.resetRock1();
        rock2.resetRock2();
        bird.resetBird();
        bird2.resetBird();
        timer.restart();
    }

    public void checkCollisions(){

        Rectangle playerR = player.getBounds();
        Rectangle rock1R = rock1.getBounds();
        Rectangle rock12R = rock12.getBounds();
        Rectangle rock2R = rock2.getBounds();
        Rectangle birdR = bird.getBounds();
        Rectangle bird2R = bird.getBounds();

        if ((playerR.intersects(rock1R) || playerR.intersects(rock12R) || playerR.intersects(rock2R)
                || playerR.intersects(birdR) || playerR.intersects(bird2R)) && !player.isInvincible()){

            player.setInvincible(true);
            numberOfLives -= 1;

            doSound(punchURL);
        }
    }

    public void doSound(URL url){
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(url);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            if (url.equals(mainBgmURL)){
                clip.loop(1);
            } else {
                clip.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void normalMove(){

        if (player.isVisible()){

            grass.move();
            player.move();
            rock1.move();
            if (difficulty >= difficulty1) {
                rock12.move();
            }
            if (difficulty >= difficulty2) {
                bird.move();
            }
            if (difficulty >= difficulty3) {
                rock2.move();
            }
            if (difficulty >= difficulty4) {
                bird2.move();
            }
        }
    }

    public void startCountDown(Graphics2D g2d){

        if (difficulty >= -1000 && difficulty < 0){
            g2d.drawImage(countDown.getImage0(), countDown.getX(), countDown.getY(), this);
        } else if (difficulty >= -2000 && difficulty < -1000){
            g2d.drawImage(countDown.getImage1(), countDown.getX(), countDown.getY(), this);
        } else if (difficulty >= -3000 && difficulty < -2000){
            g2d.drawImage(countDown.getImage2(), countDown.getX(), countDown.getY(), this);
        } else if (difficulty >= -4000 && difficulty < -3000){
            g2d.drawImage(countDown.getImage3(), countDown.getX(), countDown.getY(), this);
        }
    }

    public void updateDifficulty(Graphics2D g2d){

        if (difficulty >= difficulty1){
            g2d.drawImage(rock12.getImage(), rock12.getX(), rock12.getY(), this);
        }
        if (difficulty >= difficulty2){
            g2d.drawImage(bird.getImage(), bird.getX(), bird.getY(), this);
        }
        if (difficulty >= difficulty3){
            g2d.drawImage(rock2.getImage(), rock2.getX(), rock2.getY(), this);
        }
        if (difficulty >= difficulty4){
            g2d.drawImage(bird2.getImage(), bird2.getX(), bird2.getY(), this);
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
    }

    private class TryAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) { tryAgain(e); }
    }
}
