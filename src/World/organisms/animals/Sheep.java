package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Sheep extends Animal{
    public Sheep(short[] position, World w){
        super("Sheep.png", "Sheep", (short)4, (short)4, position[0], position[1], w);
        System.out.print("Sheep (" + x + "," + y + ") was created\n");
    }

    @Override
    public Organism copy(short[] position){
        return new Sheep(position, world);
    }


    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
