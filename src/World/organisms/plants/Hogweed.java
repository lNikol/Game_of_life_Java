package World.organisms.plants;

import World.World;
import World.organisms.Organism;

public class Hogweed extends Plant{
    public Hogweed(short[] position, World w){
        super("Hogweed.png", "Hogweed", (short)10, (short)0, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Dandelion(position, world);
    }
}
