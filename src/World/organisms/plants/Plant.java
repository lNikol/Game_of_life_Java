package World.organisms.plants;

import World.World;
import World.organisms.Organism;

import java.util.Random;

public abstract class Plant extends Organism {
    public Plant(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
    }

    @Override
    public void action(){
        super.hasMoved = true;

        if(super.age > 2){
            // rand number [0-9] + 1
            int rand = new Random().nextInt(10) + 1;
            if(rand == 2){

            }
            else{

            }
        }
        ++super.age;
    }

    @Override
    public void collision(Organism org) {

    }

    @Override
    public void deleteOrganism() {

    }
}
