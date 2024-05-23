package World.organisms.plants;

import World.World;
import World.Cell;
import World.organisms.Organism;
import World.organisms.animals.Animal;

import java.util.ArrayList;

public class Hogweed extends Plant{
    public Hogweed(short[] position, World w){
        super("Hogweed.png", "Hogweed", (short)10, (short)0, position[0], position[1], w);
        System.out.println("Hogweed (" + x + "," + y + ") was created\n");
    }
    public Hogweed(short y, short x, short power, short initiative, short age, World w) {
        super("Hogweed.png", "Hogweed", power, initiative, y, x, age, w);
        System.out.println("Hogweed (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Dandelion(position, world);
    }
    @Override
    public void action(){
        ArrayList<Cell> neighbors = world.checkCellsAround(getPosition(), false);
        for(short i = 0; i < neighbors.size(); ++i){
            Cell cell = neighbors.get(i);
            if(cell.org instanceof Animal){
                world.deleteOrganism(cell.org);
            }
        }
    }

    @Override
    public void collision(Organism org){
        System.out.println(name + ": Organism ("+org.getName()+") ate me, and I kill it");
        world.deleteOrganism(org);
        world.deleteOrganism(this);
    }
}
