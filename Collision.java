import javax.swing.JFrame;

public class Collision extends JFrame {

    public static int width = 1000;
    public static int height = 800;
    private Board activeBoard;
    private static Collision instance; 
    private boolean canShowHighscore;

    public Collision() {
        instance = this;
        canShowHighscore = true;

        activeBoard = new Board();
        add(activeBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Collision.width, Collision.height);
        setLocationRelativeTo(null);
        setTitle("Jump 'n run");
        setResizable(false);
        setVisible(true);
    }

    public static Collision getInstance()
    {
        return instance;
    }

    public void restartGame()
    {
        canShowHighscore = true;
        remove(activeBoard);
        activeBoard = new Board();
        add(activeBoard);

    }

    public void showHighscore(int score)
    {
        if(!canShowHighscore) return;
        //Create and set up the window.
        JFrame frame = new JFrame("Highscores");
 
        //Add contents to the window.
        frame.add(new Highscore(score));
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        canShowHighscore = false;
    }

    public static void main(String[] args) {
        new Collision();
    }
}