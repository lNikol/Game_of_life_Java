package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Antelope;

public class Dandelion extends Plant{
    public Dandelion(short[] position, World w){
        super("Dandelion.png", "Dandelion", (short)0, (short)0, position[0], position[1], w);
        System.out.print("Dandelion (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Dandelion(position, world);
    }
    @Override
    public void action(){
        for(short i = 0; i < 3; ++i){
            super.action();
        }
    }
}
