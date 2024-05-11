package World.organisms.animals;

import World.World;
import World.organisms.Organism;

import java.util.ArrayList;

public class Antelope extends Animal{
    public Antelope(short[] position, World w){
        super("Antelope.png", "Antelope", (short)4, (short)4, position[0], position[1], w);
        System.out.print("Antelope (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Antelope(position, world);
    }
    @Override
    public void action(){
        short[] pos = world.newPosition(this, (short) 2);
        setPosition(pos, false);
        collision(world.getOrganism(pos));
    }

    @Override
    public void collision(Organism other) {
        if(other != null){
            if(Math.random() > 0.5){
                ArrayList<short[]> neighbors = world.checkCellsAround(getPosition(), false);
                for(short i = 0; i < neighbors.size(); ++i){
                    if(world.getOrganism(neighbors.get(i)) == null){
                        super.collision(other);
                        return;
                    }
                }
            }
            else{
                super.collision(other);
            }
        }
        else{
            super.collision(other);
        }
    }

    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
