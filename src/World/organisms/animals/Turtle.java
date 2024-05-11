package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Turtle extends Animal{
    public Turtle(short[] position, World w){
        super("Turtle.png", "Turtle", (short)2, (short)1, position[0], position[1], w);
    }
    @Override
    public Organism copy(short[] position){
        return new Turtle(position, world);
    }
}