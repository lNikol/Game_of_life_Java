package World.organisms.plants;

import World.World;
import World.organisms.Organism;

public class Grass extends Plant{
    public Grass(short[] position, World w){
        super("Grass.png", "Grass", (short)0, (short)0, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Grass(position, world);
    }
}
