package World.organisms.animals;

import World.World;
import World.Cell;
import World.organisms.Organism;

import java.util.ArrayList;

public class Antelope extends Animal{
    public Antelope(short[] position, World w){
        super("Antelope.png", "Antelope", (short)4, (short)4, position[0], position[1], w);
        System.out.println("Antelope (" + x + "," + y + ") was created\n");
    }
    public Antelope(short y, short x, short power, short initiative, short age, World w) {
        super("Antelope.png", "Antelope", power, initiative, y, x, age, w);
        System.out.println("Antelope (" + x + "," + y + ") was created\n");
    }

    @Override
    public Organism copy(short[] position){
        return new Antelope(position, world);
    }
    @Override
    public void action(){
        short[] pos = world.newPosition(this, (short) 2);
        setPosition(pos, false);
        collision(world.getCell(pos).org);
    }
    @Override
    public void collision(Organism other) {
        if(other != null){
            if(Math.random() > 0.5){
                ArrayList<Cell> neighbors = world.checkCellsAround(getPosition(), false);
                for(short i = 0; i < neighbors.size(); ++i){
                    if(neighbors.get(i).org == null){
                        setPosition(neighbors.get(i).getPosition(), false);
                        super.collision(neighbors.get(i).org);
                        return;
                    }
                }
                super.collision(other);
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
