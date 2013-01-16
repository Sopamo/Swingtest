import javax.swing.JFrame;

public class Collision extends JFrame {

    public static int width = 600;
    public static int height = 500;
    private Board activeBoard;
    private static Collision instance; 

    public Collision() {
        instance = this;


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
        remove(activeBoard);
        activeBoard = new Board();
        add(activeBoard);
    }

    public static void main(String[] args) {
        new Collision();
    }
}