package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

public class Wolfberries extends Plant{
    public Wolfberries(short[] position, World w){
        super("Wolfberries.png", "Wolfberries", (short)99, (short)0, position[0], position[1], w);
        System.out.print("Wolfberries (" + x + "," + y + ") was created\n");
    }
    public Wolfberries(short y, short x, short power, short initiative, short age, World w) {
        super("Wolfberries.png", "Wolfberries", power, initiative, y, x, age, w);
        System.out.print("Wolfberries (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Wolfberries(position, world);
    }
    @Override
    public void collision(Organism org){
        world.deleteOrganism(org);
        world.deleteOrganism(this);
    }
}
