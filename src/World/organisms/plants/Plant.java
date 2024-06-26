package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

import java.util.Random;

public abstract class Plant extends Organism {
    public Plant(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
    }
    public Plant(String image, String name, short power, short initiative, short y, short x, short age, World w) {
        super(image, name, power, initiative, y, x, age, w);
    }
    @Override
    public void action(){
        if(super.age > 2){
            if(checkReproduction()){
                // rand number [0-9] + 1
                int rand = new Random().nextInt(10) + 1;
                if(rand == 1){
                    world.setOrganism(world.newPosition(this, (short) 1), this);
                }
            }
        }
    }

    @Override
    public void collision(Organism org) {
        world.deleteOrganism(this);
        world.replaceOrganism(org.getPosition(), org);
        world.replaceOrganism(((Animal) org).getOldPosition(), null);
    }
}
