package World.organisms.animals;

import World.World;
import World.organisms.Organism;

import java.util.ArrayList;

public class Fox extends Animal{
    public Fox(short[] position, World w){
        super("Fox.png", "Fox", (short)3, (short)7, position[0], position[1], w);
        System.out.print("Fox (" + x + "," + y + ") was created\n");
    }

    @Override
    public Organism copy(short[] position){
        return new Fox(position, world);
    }

    @Override
    public void action(){
        ArrayList<short[]> pos = (world.checkCellsAround(getPosition(), false));

        for(short i = 0; i < pos.size(); ++i){
            if(pos.get(i)[0] == -1){
                return;
            }
            Organism org = world.getOrganism(pos.get(i));
            if(org == null || power >= org.getPower()){
                setPosition(pos.get(i), false);
                super.collision(org);
                return;
            }
        }
    }

    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
