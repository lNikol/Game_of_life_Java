package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Human extends Animal{
    public Human(short y, short x, World w){
        super("Human.png", "Human", (short)5, (short)4, y, x, w);
        System.out.print("Human (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Human(position[0], position[1], world);
    }

    @Override
    public void action(){
        System.out.print("Action in human");
    }

    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
