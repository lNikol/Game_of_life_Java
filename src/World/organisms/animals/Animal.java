package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public abstract class Animal extends Organism {
    public Animal(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
    }

    @Override
    public void action(){

    }

    @Override
    public void collision(Organism org) {

    }

    @Override
    public void deleteOrganism() {

    }

}
