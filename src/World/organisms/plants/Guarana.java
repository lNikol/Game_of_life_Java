package World.organisms.plants;

import World.World;
import World.organisms.Organism;

public class Guarana extends Plant{
    public Guarana(short[] position, World w){
        super("Guarana.png", "Guarana", (short)0, (short)0, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Guarana(position, world);
    }
}
