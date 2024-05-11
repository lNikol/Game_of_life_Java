package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Fox extends Animal{
    public Fox(short[] position, World w){
        super("Fox.png", "Fox", (short)3, (short)7, position[0], position[1], w);
    }

    @Override
    public Organism copy(short[] position){
        return new Fox(position, world);
    }
}
