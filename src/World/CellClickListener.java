package World;

import World.organisms.Organism;
import World.organisms.animals.Animal;
import World.organisms.plants.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class CellClickListener extends MouseAdapter {
    private short row;
    private short col;
    World w;
    public CellClickListener(short row, short col, World W) {
        this.row = row;
        this.col = col;
        this.w = W;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JPopupMenu menu = new JPopupMenu();
            for (Class<?> organism : w.getOrganismsInGame()) {
                JMenuItem item = new JMenuItem(organism.getSimpleName());
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            addOrganismToCell(row, col, organism, w);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                menu.add(item);
            }
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public static void addOrganismToCell(short row, short col, Class<?> organism, World w) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if(w != null){
            Cell c = w.getMap().getCell(row, col);
            if (c.org == null){
                short[] pos = new short[]{row, col};
                if(String.valueOf(organism.getPackage()).contains(".animals")){
                    if(c.isHex){
                        c.setNewColor(Color.RED);
                    }
                    else {
                        c.setBackground(Color.RED);
                    }
                }
                else if(String.valueOf(organism.getPackage()).contains(".plants")){
                    if(c.isHex){
                        c.setNewColor(Color.GREEN);
                    }
                    else {
                        c.setBackground(Color.GREEN);
                    }
                }
                w.setOrganism(pos, organism);
                JLabel label = new JLabel(organism.getSimpleName());
                c.removeAll();
                c.add(label);
                c.revalidate();
                c.repaint();
            }
        }
    }
}