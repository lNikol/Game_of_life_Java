package World;

import World.organisms.Organism;
import World.organisms.animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map {
    ArrayList<ArrayList<Cell>> map = new ArrayList<ArrayList<Cell>>();
    private short width = -1, height = -1;
    public Map(short width, short height, boolean isHex, World w){
        this.width = width;
        this.height = height;
        if(!isHex){
            for(short i = 0; i < height; ++i){
                ArrayList<Cell> row = new ArrayList<Cell>(height);
                for(short j = 0; j < width; ++j){
                    row.add(new Cell(i, j));
                }
                this.map.add(row);
            }
        }
        else{

        }
    }
    public void setOrganism(short[] position, Organism organism){
       this.map.get(position[0]).get(position[1]).setOrganism(organism);
    }

    public void setOrganism(short y, short x, Organism organism){
        this.map.get(y).get(x).setOrganism(organism);
    }


    public void replaceOrganism(short[] position, Organism newOrg){
        this.map.get(position[0]).get(position[1]).setOrganism(newOrg);
    }

    public void deleteOrganism(Organism oldOrg){
        if(oldOrg == null) {
            return;
        }
        short[] oldPos;
        if(oldOrg instanceof Animal){
            if(oldOrg.getIsAlive()){
                oldPos = oldOrg.getPosition();
            }
            else{
                oldPos = ((Animal) oldOrg).getOldPosition();
            }
        }
        else{
            oldPos = oldOrg.getPosition();
        }
        replaceOrganism(oldPos, null);
    }
    public Cell getCell(short[] position){
        return this.map.get(position[0]).get(position[1]);
    }
    public Cell getCell(short y, short x){
        return this.map.get(y).get(x);
    }

}
