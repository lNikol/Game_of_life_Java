package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

import java.util.Random;

public abstract class Plant extends Organism {
    public Plant(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
    }

    @Override
    public void action(){
        System.out.print("Action in plant");
        if(super.age > 2){
            // rand number [0-9] + 1
            int rand = new Random().nextInt(10) + 1;
            if(rand == 2){
               if(world.setOrganism(world.newPosition(this, (short) 1), this)){
                   System.out.print("Plant child was created");
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
