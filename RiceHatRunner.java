import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Justin on 1/7/2016.
 */
public class RiceHatRunner extends JFrame implements ActionListener{

    private Score score;
    private Board board;
    private Timer timer;
    private final int DELAY = 5;
    private JSplitPane splitPane;
    private int divLocation;

    public RiceHatRunner(){

        initUI();
    }

    private void initUI(){

        board = new Board();
        score = new Score();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, score);
        divLocation = 700;
        splitPane.setDividerLocation(divLocation);
        setLayout(new BorderLayout());

        add(splitPane, BorderLayout.CENTER);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 700));
        pack();
        setResizable(false);

        setTitle("Rice Hat Runner");
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
        setTitle();
        checkTitle();
    }

    public void checkTitle(){

        if (divLocation == 700) {
            if (!(board.getTitle())) {
                divLocation = 500;
                splitPane.setDividerLocation(divLocation);
            }
        }
    }

    public void setNumberOfLives(){

        score.setNumberOfLives(board.getNumberOfLives());
    }

    public void setGameOver(){

        score.setGameOver(board.getGameOver());
    }

    public void setTitle(){

        score.setTitle(board.getTitle());
    }

    public void setScoreBoardString(){

        board.setScoreBoardString(score.getScoreBoardString());
    }

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){

                RiceHatRunner ex = new RiceHatRunner();
                ex.setVisible(true);
            }
        });
    }
}
