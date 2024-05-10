package World.organisms;
import World.World;

public abstract class Organism {
    protected String name = "", image = "";
    protected short power = -1, initiative = -1;
    protected short x = -1, y = -1, age = 0;
    protected World world;
    protected boolean hasMoved = false;

    public Organism(String image, String name, short power, short initiative,
                    short x, short y, World w) {
        this.image = image;
        this.name = name;
        this.power = power;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.world = w;
    }

    public abstract void action();
    public abstract void collision(Organism org);
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

    public void writeToLog() {
        // Implementacja zapisu do logu
    }
}