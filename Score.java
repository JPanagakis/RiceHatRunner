import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by justin on 1/14/16.
 */
public class Score extends JPanel implements ActionListener{

    private Timer timer;
    private final int DELAY = 5;
    private int scoreBoard;
    private String scoreBoardString;
    private JTextField scoreBoardTF;
    private int numberOfLives;
    private boolean gameOver;

    private Lives lives;

    public Score(){

        initScore();
    }

    public void initScore(){

        scoreBoard = -4000;
        numberOfLives = 2;
        gameOver = false;

        initScoreBoardTF();
        lives = new Lives();

        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(200, 700));

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initScoreBoardTF(){

        scoreBoardTF = new JTextField("Score:");
        scoreBoardTF.setFont(new Font("Serif", Font.BOLD, 20));
        scoreBoardTF.setBorder(BorderFactory.createEmptyBorder());
        scoreBoardTF.setEditable(false);
        scoreBoardTF.setSize(200, 150);
        add(scoreBoardTF);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        if (scoreBoard > 0) {
            g2d.drawString(scoreBoardString, 60, 75);
        }

        if (numberOfLives == 2){
            g2d.drawImage(lives.getImage2(), lives.getX(), lives.getY(), this);
        } else if (numberOfLives == 1){
            g2d.drawImage(lives.getImage1(), lives.getX(), lives.getY(), this);
        } else if (numberOfLives == 0){
            g2d.drawImage(lives.getImage0(), lives.getX(), lives.getY(), this);
        } else {
            g2d.drawImage(lives.getImage00(), lives.getX(), lives.getY(), this);
        }
    }

    public void setNumberOfLives(int l){ numberOfLives = l; }

    public void setGameOver(boolean bl){ gameOver = bl; }

    public String getScoreBoardString(){

        scoreBoardString = Integer.toString(scoreBoard);
        return scoreBoardString;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (!gameOver) {
            scoreBoard += 5;
            scoreBoardString = Integer.toString(scoreBoard);
        } else {
            scoreBoard = -4000;
        }

        repaint();
    }
}
