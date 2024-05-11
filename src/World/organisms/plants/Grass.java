package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

public class Grass extends Plant{
    public Grass(short[] position, World w){
        super("Grass.png", "Grass", (short)0, (short)0, position[0], position[1], w);
        System.out.print("Grass (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Grass(position, world);
    }
    @Override
    public void action(){
        System.out.print("Action in grass");
    }
}
