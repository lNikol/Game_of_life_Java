import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

import FileService.FileService;
import World.World;
public class Game extends JFrame {
    private FileService fs;
    private World w;
    String fileName = "log.log";
    public Game() throws IOException {
        setTitle("Key Listener Example");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter());
        setVisible(true);
        readLogFile();
    }

    public void createWorld(short width, short height, boolean read) throws FileNotFoundException {
        w = new World(width, height, read, fileName);
        fs = new FileService(fileName);
    }

    // KeyListener do nasłuchiwania klawiszy
    private class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {
            if (!w.getKeyPressed()) {
                w.setKeyPressed(true);
                w.setKey(e.getKeyChar());
            }
        }
    }
    public World getWorld(){
        return this.w;
    }
    public void setFileName(String fn){
        this.fileName = fn;
    }

    public void saveToLog() throws IOException {
        fs.writeToLog(w.saveToLog(), w.getWidth(), w.getHeight());
    }

    public void readLogFile() throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine();
                if (line != null && line.startsWith("World:")) {
                    String[] worldInfo = line.split(" ");
                    short height = Short.parseShort(worldInfo[2].split(",")[0]);
                    short width = Short.parseShort(worldInfo[worldInfo.length - 1]);
                    createWorld(width, height, true);
                }
                else{
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Write width: ");
                    short width = scanner.nextShort();
                    System.out.println("Write height: ");
                    short height = scanner.nextShort();
                    scanner.close();

                    createWorld(width, height, false);
                }
                return;
            } catch (IOException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write width: ");
                short width = scanner.nextShort();
                System.out.println("Write height: ");
                short height = scanner.nextShort();
                scanner.close();

                createWorld(width, height, false);
            }
        }

    public void endGame(){
        dispose();
    }
}