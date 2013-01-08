import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private ArrayList aliens;
    private ArrayList buildings;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    private static Board instance;
    private double speed;

    private int[][] pos = { 
        {250, 200}
     };

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ingame = true;

        setSize(Collision.width, Collision.height);

        player = new Player();

        initAliens();
        initBuildings();

        timer = new Timer(5, this);
        timer.start();
        this.speed = 1;
        Board.instance = this;
    }

    public static Board getInstance() {
        return Board.instance;
    }

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }

    public void initAliens() {
        aliens = new ArrayList();

        for (int i=0; i<pos.length; i++ ) {
            aliens.add(new Alien(pos[i][0], pos[i][1]));
        }
    }

    public void initBuildings() {
        this.buildings = new ArrayList();

        for (int i=0; i<1; i++ ) {
            this.buildings.add(new Building(200, 50, 10, 100));
        }
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D)g;
            
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

            for(int i = 0; i<aliens.size();++i) {
                Alien a = (Alien) aliens.get(i);
                g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }

            g2d.setColor(Color.WHITE);

            for(int i = 0; i<buildings.size();++i) {
                Building b = (Building) buildings.get(i);
                b.paint(g2d);
            }

            g2d.drawString("Position: " + player.getX() + " - " + player.getY() + " - DY: " + player.getDY() + " - DX: " + player.getDX(), 5, 15);

        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                         B_HEIGHT / 2);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {


        //ArrayList ms = craft.getMissiles();
        player.move();
        for (int i = 0; i < aliens.size(); ++i) {
            Alien a = (Alien) aliens.get(i);
            a.move();
        }
        checkCollisions();
        repaint();  
    }

    public void checkCollisions() {

        Rectangle r3 = player.getBounds();

        /*for (int j = 0; j<aliens.size(); j++) {
            Alien a = (Alien) aliens.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                ingame = false;
            }
        }*/

        int playerY = player.getY() - player.getHeight();
        for (int i = 0; i < buildings.size(); ++i) {
            Building b = (Building) buildings.get(i);
            if(player.getDY() > 0 && playerY < b.getY() && player.getX() + player.getWidth() >= b.getX() && player.getX() <= b.getX() + b.getWidth()) {
                player.setY(b.getY()-player.getHeight() - 1);
                player.setMidair(false);
            }
            if(player.getX() + player.getWidth() >= b.getX() && player.getX() <= b.getX() + b.getWidth()) {
                player.setMidair(true);
            }

        }
    }

    public Player getPlayer() {
        return player;
    }


    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
