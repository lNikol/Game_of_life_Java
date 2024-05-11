package World.organisms.plants;

import World.World;
import World.organisms.Organism;
import World.organisms.animals.Animal;

public class Guarana extends Plant{
    public Guarana(short[] position, World w){
        super("Guarana.png", "Guarana", (short)0, (short)0, position[0], position[1], w);
        System.out.print("Guarana (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Guarana(position, world);
    }
    @Override
    public void action(){
        System.out.print("Action in guarana");
        super.action();
    }

    @Override
    public void collision(Organism org) {
        org.setPower((short)(org.getPower() + 3));
        world.deleteOrganism(this);
        world.replaceOrganism(org.getPosition(), org);
        world.replaceOrganism(((Animal) org).getOldPosition(), null);
    }
}
