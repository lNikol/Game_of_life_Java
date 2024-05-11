package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Wolf extends Animal{
    public Wolf(short[] position, World w){
        super("Wolf.png", "Wolf", (short)9, (short)5, position[0], position[1], w);
        System.out.print("Wolf (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Wolf(position, world);
    }

    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
