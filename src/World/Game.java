package World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import FileService.FileService;
import World.World;
import World.Cell;
import World.organisms.Organism;
import World.organisms.animals.Animal;
import World.organisms.plants.Plant;

public class Game extends JFrame {
    private FileService fs;
    private final int radius = 30; // Promień heksagonu
    private World w;
    String fileName = "log.log";
    public Game() throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        setTitle("Game of life");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter());
        readLogFile();

        if(!w.getIsHex()){
            setLayout(new GridLayout(w.getHeight() + 1, w.getWidth()));
            for(short i = 0; i < w.getHeight(); ++i) {
                for(short j = 0; j < w.getWidth(); ++j) {
                    Cell c = w.getCell(new short[]{i, j});
                    c.setBorder(BorderFactory.createLineBorder(Color.black));
                    c.setBackground(Color.white);
                    c.addMouseListener(new CellClickListener(i, j, w));
                    if(c.org != null) {
                        JLabel label = new JLabel(c.org.getName());
                        if(c.org instanceof Animal) {
                            c.setBackground(Color.red);
                        } else if(c.org instanceof Plant) {
                            c.setBackground(Color.green);
                        }
                        c.removeAll();
                        c.add(label);
                    }
                    add(c);
                }
            }
        /*JButton button2 = new JButton("Pobierz grę");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                w.saveToLog();
            }
        });
        add(button2);*/
            requestFocusInWindow();
        }
        else{
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.fill = GridBagConstraints.NONE;

            for (int row = 0; row < w.getHeight(); row++) {
                for (int col = 0; col < w.getHeight(); col++) {
                    Cell c = w.getCell(new short[]{(short) row, (short) col});
                    c.addMouseListener(new CellClickListener((short) row, (short) col, w));
                    gbc.gridx = col + row;
                    gbc.gridy = row;
                    if(c.org != null) {
                        JLabel label = new JLabel(c.org.getName());
                        if(c.org instanceof Animal) {
                            c.setNewColor(Color.red);
                        } else if(c.org instanceof Plant) {
                            c.setNewColor(Color.green);
                        }
                        c.removeAll();
                        c.add(label);
                    }
                    else{
                        JLabel label = new JLabel(" ");
                        c.setNewColor(Color.white);
                        c.removeAll();
                        c.add(label);
                    }
                    add(c, gbc);
                }
            }
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }



    public void createWorld(short width, short height, boolean read, boolean isHex) throws FileNotFoundException {
        w = new World(width, height, read, fileName, isHex);
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
                createWorld(width, height, true, false);
            }
            else{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write width: ");
                short width = scanner.nextShort();
                System.out.println("Write height: ");
                short height = scanner.nextShort();
                System.out.println("Write isHex: ");
                boolean isHex = scanner.nextBoolean();
                scanner.close();
                createWorld(width, height, false, isHex);
            }
        } catch (IOException e) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write width: ");
            short width = scanner.nextShort();
            System.out.println("Write height: ");
            short height = scanner.nextShort();
            System.out.println("Write isHex: ");
            boolean isHex = scanner.nextBoolean();
            scanner.close();
            createWorld(width, height, false, isHex);
        }
    }

    public void endGame(){
        dispose();
    }
}