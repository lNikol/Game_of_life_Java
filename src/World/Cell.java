package World;

import World.organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Cell extends JPanel {
    Hexagon hex = null;
    boolean isHex = false;
    public Organism org = null;
    public short x = -1, y = -1;
    public Cell(short yy, short xx, Organism organism){
        this.y = yy;
        this.x = xx;
        this.org = organism;
    }
    public Cell(short yy, short xx, boolean isHex){
        this.y = yy;
        this.x = xx;
        this.isHex = isHex;
    }

    public void setOrganism(Organism organism, boolean isHex){
        this.org = organism;
        if(isHex){
            this.isHex = true;
            final int SIZE = 40;
            setPreferredSize(new Dimension(2 * SIZE, (int) (SIZE * Math.sqrt(3) + SIZE/5)));
        }
    }

    public void setPosition(short[] position){
        this.y = position[0];
        this.x = position[1];
    }

    public short[] getPosition(){
        return new short[] {this.y, this.x};
    }


    @Override
    protected void paintComponent(Graphics g) {
        if(this.isHex){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            this.hex.draw(g2);
        }
        else{
            super.paintComponent(g);
        }
    }

    public void setNewColor(Color color){
        if(this.hex != null){
            this.hex.setNewColor(color);
        }
        else{
            this.hex=new Hexagon(40, 40, 40, color);
            final int SIZE = 40;
            setPreferredSize(new Dimension(2 * SIZE, (int) (SIZE * Math.sqrt(3) + SIZE/5)));
        }
    }
    private class Hexagon {
        private final int x;
        private final int y;
        private final int size;
        private Color color;

        public Hexagon(int x, int y, int size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
        }

        public void setNewColor(Color color){
            this.color = color;
        }
        public void draw(Graphics2D g2) {
            Path2D hexagon = new Path2D.Double();
            for (int i = 0; i < 6; i++) {
                double angle = Math.toRadians(60 * i);
                double px = x + size * Math.cos(angle);
                double py = y + size * Math.sin(angle);
                if (i == 0) {
                    hexagon.moveTo(px, py);
                } else {
                    hexagon.lineTo(px, py);
                }
            }
            hexagon.closePath();

            // Wypełnij heksagon kolorem
            g2.setColor(color);
            g2.fill(hexagon);

            // Narysuj obrys heksagonu
            g2.setColor(Color.BLACK);
            g2.draw(hexagon);
        }
    }
}
