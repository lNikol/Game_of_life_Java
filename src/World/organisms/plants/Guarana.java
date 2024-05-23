package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

public class Guarana extends Plant{
    public Guarana(short[] position, World w){
        super("Guarana.png", "Guarana", (short)0, (short)0, position[0], position[1], w);
        System.out.println("Guarana (" + x + "," + y + ") was created\n");
    }
    public Guarana(short y, short x, short power, short initiative, short age, World w) {
        super("Guarana.png", "Guarana", power, initiative, y, x, age, w);
        System.out.println("Guarana (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Guarana(position, world);
    }

    @Override
    public void collision(Organism org) {
        System.out.println(name + ": Organism ("+org.getName()+") ate me, and I boost its power");
        org.setPower((short)(org.getPower() + 3));
        world.deleteOrganism(this);
        world.replaceOrganism(((Animal) org).getOldPosition(), null);
        world.replaceOrganism(org.getPosition(), org);
    }
}
