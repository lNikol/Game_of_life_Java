import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import World.World;
public class Game extends JFrame {

    private World w;
    public Game() {
        setTitle("Key Listener Example");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter());
        setVisible(true);
        short width = 5, height = 5;
        w = new World(width, height, false);
    }

    // Klasa wewnętrzna World
   /* class World {
        public void action() {
            boolean temp = true;
            while (temp) {
                if (keyPressed) {
                    System.out.println("In action");
                    System.out.println(true);
                    keyPressed = false; // resetujemy stan po wykryciu naciśnięcia
                    temp = false;
                }
                try {
                    Thread.sleep(100); // krótka przerwa, aby nie obciążać procesora
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    // KeyListener do nasłuchiwania klawiszy
    private class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (!w.getKeyPressed()) {
                w.setKeyPressed(true);
                w.setKey(e.getKeyChar());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
    public World getWorld(){
        return this.w;
    }
}