package World.organisms;
import World.World;
import World.organisms.animals.*;
import World.organisms.plants.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public abstract class Organism {
    protected short x = -1, y = -1, age = 0;
    protected boolean isAlive = true, hasMoved = false;
    protected String name = "", image = "";
    protected short power = -1, initiative = -1;
    protected World world;

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

    public Organism(String image, String name, short power, short initiative,
                    short y, short x, short age, World w) {
        this.image = image;
        this.name = name;
        this.power = power;
        this.initiative = initiative;
        this.y = y;
        this.x = x;
        this.age = age;
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
    public void setPower(short pow){
        this.power = pow;
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
    public boolean getHasMoved(){
        return this.hasMoved;
    }
    public void setHasMoved(boolean moved){
        this.hasMoved = moved;
    }
    public boolean getIsAlive(){
        return this.isAlive;
    }
    public void setIsAlive(boolean alive){
        this.isAlive = alive;
    }

    public void setAge(short a){
        this.age = a;
    }
    public short getAge(){
        return this.age;
    }
    public String writeToLog(){
       return (this.name + "(y,x): (" + Short.toString(this.y) + ", "
               + Short.toString(this.x) + "), power: " + Short.toString(this.power) + ", initiative: "
               + Short.toString(this.initiative) + ", age: " + Short.toString(this.age) + "\n");
    }

}