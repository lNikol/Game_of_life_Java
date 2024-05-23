package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Turtle extends Animal{
    public Turtle(short[] position, World w){
        super("Turtle.png", "Turtle", (short)2, (short)1, position[0], position[1], w);
        System.out.println("Turtle (" + x + "," + y + ") was created\n");
    }
    public Turtle(short y, short x, short power, short initiative, short age, World w) {
        super("Turtle.png", "Turtle", power, initiative, y, x, age, w);
        System.out.println("Turtle (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Turtle(position, world);
    }

    @Override
    public boolean reboundAttack(Organism org){
        return org.getPower() < 5;
    }

    @Override
    public void action(){
        if(Math.random()<0.25){
            super.action();
        }
    }
}
