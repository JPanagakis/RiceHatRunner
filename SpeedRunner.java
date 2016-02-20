import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Justin on 1/7/2016.
 */
public class SpeedRunner extends JFrame implements ActionListener{

    private Score score;
    private Board board;
    private Timer timer;
    private final int DELAY = 5;

    public SpeedRunner(){

        initUI();
    }

    private void initUI(){

        board = new Board();
        score = new Score();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, score);
        splitPane.setDividerLocation(500);
        setLayout(new BorderLayout());

        add(splitPane, BorderLayout.CENTER);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 700));
        pack();
        setResizable(false);

        setTitle("Speed Runner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        setScoreBoardString();
        setNumberOfLives();
        setGameOver();
    }

    public void setNumberOfLives(){

        score.setNumberOfLives(board.getNumberOfLives());
    }

    public void setGameOver(){

        score.setGameOver(board.getGameOver());
    }

    public void setScoreBoardString(){

        board.setScoreBoardString(score.getScoreBoardString());
    }

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){

                SpeedRunner ex = new SpeedRunner();
                ex.setVisible(true);
            }
        });
    }
}
