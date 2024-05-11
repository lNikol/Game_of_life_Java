package World;

import World.organisms.Organism;

import java.util.Vector;

public class Map {
    Vector<Vector<Cell>> map = new Vector<Vector<Cell>>();
    private short width = -1, height = -1;
    public Map(short width, short height, boolean isHex){
        this.width = width;
        this.height = height;
        if(!isHex){
            for(short i = 0; i < height; ++i){
                Vector<Cell> row = new Vector<Cell>(height);
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

    public Organism getOrganism(short[] position){
        return this.map.get(position[0]).get(position[1]).org;
    }
    public Organism getOrganism(short y, short x){
        return this.map.get(y).get(x).org;
    }

}
