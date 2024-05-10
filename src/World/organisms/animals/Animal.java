package World.organisms.animals;

import World.World;
import World.organisms.Organism;

public class Animal extends Organism {
    public Animal(String image, String name, short power, short initiative, short x, short y, World w) {
        super(image, name, power, initiative, x, y, w);
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
