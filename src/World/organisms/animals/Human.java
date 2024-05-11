package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Human extends Animal{
    public Human(short y, short x, World w){
        super("Human.png", "Human", (short)5, (short)4, y, x, w);
    }
    @Override
    public Organism copy(short[] position){
        return new Human(position[0], position[1], world);
    }
}
