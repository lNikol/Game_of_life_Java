package World.organisms.plants;

import World.World;
import World.organisms.Organism;

public class Wolfberries extends Plant{
    public Wolfberries(short[] position, World w){
        super("Wolfberries.png", "Wolfberries", (short)99, (short)0, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Wolfberries(position, world);
    }
}
