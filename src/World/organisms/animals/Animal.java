package World.organisms.animals;

import World.World;
import World.Cell;
import World.organisms.Organism;
import World.organisms.plants.Plant;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Animal extends Organism {
    protected short oldX, oldY;
    public Animal(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
        this.oldX = x;
        this.oldY = y;
    }
    public Animal(String image, String name, short power, short initiative, short y, short x, short age, World w) {
        super(image, name, power, initiative, y, x, age, w);
        this.oldX = x;
        this.oldY = y;
    }

    public short[] getOldPosition() {
        return new short[]{oldY, oldX};
    }

    public void setPosition(short[] position, boolean isOld){
        if(isOld){
            oldY = position[0];
            oldX = position[1];
        }
        else{
            y = position[0];
            x = position[1];
        }
    }
    public void move(){
        short[] pos = world.newPosition(this, (short) 1);
        oldY = y;
        oldX = x;
        setPosition(pos, false);
    }

    @Override
    public void action(){
        System.out.print("Action in animal");
        System.out.print("Before: " + x + " " + y);
        move();
        System.out.print("after move: " + x + " " + y);
        collision(world.getCell(getPosition()).org);
    }

    @Override
    public void collision(Organism org) {
        if(org instanceof Animal){
            if(org.getClass() == this.getClass()){
                if(!reproduction(this)){
                    reproduction(org);
                }
                y = oldY;
                x = oldX;
            }
            else{
                if(org instanceof Antelope){
                   org.collision(this);
                    if(isAlive){
                        world.replaceOrganism(getPosition(), this);
                        world.replaceOrganism(getOldPosition(), null);
                        oldY = y;
                        oldX = x;
                    }
                    else{
                        world.deleteOrganism(this);
                    }
                }
                else{
                    if (((Animal) org).reboundAttack(this)) {
                        y = oldY;
                        x = oldX;
                        world.deleteOrganism(org);
                    }
                    else{
                        if(power >= org.getPower()){
                            short[] newPosition = org.getPosition();
                            world.deleteOrganism(org);
                            world.replaceOrganism(getOldPosition(), null);
                            world.replaceOrganism(newPosition, this);
                            oldY = y;
                            oldX = x;
                        }
                        else{
                            world.deleteOrganism(this);
                        }
                    }
                }
            }
        }
        else if(org instanceof Plant){
            org.collision(this);
            oldY = y;
            oldX = x;
        }
        else{
            world.replaceOrganism(getPosition(), this);
            world.replaceOrganism(getOldPosition(), null);
            oldY = y;
            oldX = x;
        }
    }

    public boolean reproduction(Organism other){
        if(age <= 2){
            return false;
        }
       return checkReproduction();
    }
    public abstract boolean reboundAttack(Organism org);
}
