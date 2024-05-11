package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Antelope extends Animal{
    public Antelope(short[] position, World w){
        super("Antelope.png", "Antelope", (short)4, (short)4, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Antelope(position, world);
    }
}
