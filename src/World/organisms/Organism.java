package World.organisms;
import World.World;
import World.organisms.animals.*;
import World.organisms.plants.*;

public abstract class Organism {
    public static Class<?>[] organisms = {
            Guarana.class,
            Hogweed.class,
            Grass.class,
            Dandelion.class,
            Wolfberries.class,
            Wolf.class,
            Fox.class,
            Sheep.class,
            Turtle.class,
            Antelope.class,
            Human.class,
    };
    protected String name = "", image = "";
    protected short power = -1, initiative = -1;
    protected short x = -1, y = -1, age = 0;
    protected World world;
    protected boolean hasMoved = false;

    public Organism(String image, String name, short power, short initiative,
                    short y, short x, World w) {
        this.image = image;
        this.name = name;
        this.power = power;
        this.initiative = initiative;
        this.y = y;
        this.x = x;
        this.world = w;
    }

    public abstract void action();
    public abstract void collision(Organism org);
    public abstract Organism copy(short[] position);
    public void drawOrganism(){
        //world.map.setOrganism(this.getPosition());
    }
    public String getImage(){
        return this.image;
    }
    public short getPower(){
        return this.power;
    }
    public short getInitiative(){
        return this.initiative;
    }
    public short[] getPosition(){
        return new short[]{this.y, this.x};
    }
    public World getWorld(){
        return this.world;
    }
    public abstract void deleteOrganism();
    public boolean getHasMoved(){
        return this.hasMoved;
    }
    public void setHasMoved(boolean moved){
        this.hasMoved = moved;
    }
    public void setAge(short a){
        this.age = a;
    }
    public short getAge(){
        return this.age;
    }
    public void writeToLog() {
        // Implementacja zapisu do logu
    }
}