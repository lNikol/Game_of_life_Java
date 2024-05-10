package World;

import World.organisms.Organism;

public class Cell {
    public Organism org = null;
    public short x = -1, y = -1;
    public void setOrganism(Organism organism){
        this.org = organism;
    }
    public void setPosition(short[] position){
        this.y = position[0];
        this.x = position[1];
    }
    public short[] getPosition(){
        return new short[] {this.y, this.x};
    }
}
