import javax.swing.JFrame;

public class Collision extends JFrame {

    public static int width = 600;
    public static int height = 500;

    public Collision() {
        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Collision.width, Collision.height);
        setLocationRelativeTo(null);
        setTitle("Jump 'n run");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Collision();
    }
}