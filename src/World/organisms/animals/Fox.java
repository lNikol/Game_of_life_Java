package World.organisms.animals;

import World.World;
import World.organisms.Organism;
import World.Cell;
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
        ArrayList<Cell> pos = (world.checkCellsAround(getPosition(), false));

        for(short i = 0; i < pos.size(); ++i){
            if(pos.get(i) != null && pos.get(i).getPosition()[0] == -1){
                return;
            }
            Cell cell = world.getOrganism(pos.get(i).getPosition());
            if(cell.org == null || (cell.org != null && power >= cell.org.getPower())){
                setPosition(new short[] {cell.y, cell.x}, false);
                super.collision(cell.org);
                return;
            }
        }
    }

    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }
}
