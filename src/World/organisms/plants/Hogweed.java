package World.organisms.plants;

import World.World;
import World.Cell;
import World.organisms.Organism;
import World.organisms.animals.Animal;

import java.util.ArrayList;

public class Hogweed extends Plant{
    public Hogweed(short[] position, World w){
        super("Hogweed.png", "Hogweed", (short)10, (short)0, position[0], position[1], w);
        System.out.print("Hogweed (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Dandelion(position, world);
    }
    @Override
    public void action(){
        System.out.print("Action in hogweed");
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
        world.deleteOrganism(org);
        world.deleteOrganism(this);
    }
}
