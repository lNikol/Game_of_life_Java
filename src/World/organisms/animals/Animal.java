package World.organisms.animals;

import World.World;
import World.organisms.Organism;
import World.organisms.plants.Plant;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism {
    protected short oldX, oldY;
    public Animal(String image, String name, short power, short initiative, short y, short x, World w) {
        super(image, name, power, initiative, y, x, w);
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
        collision(world.getOrganism(getPosition()));
    }

    @Override
    public void collision(Organism org) {
        if(org instanceof Animal){
            if(org.getClass() == this.getClass()){
                if(!reproduction(this)){
                    reproduction(org);
                }
                x = oldX;
                y = oldY;
                return;
            }
            else{
                if(org instanceof Antelope){
                   org.collision(this);
                    if(!isAlive){
                        world.deleteOrganism(this);
                    }
                }
                else{
                    if (((Animal) org).reboundAttack(this)) {
                        x = oldX;
                        y = oldY;
                        world.deleteOrganism(org);
                    }
                    else{
                        if(power >= org.getPower()){
                            short[] newPosition = org.getPosition();
                            world.deleteOrganism(org);
                            world.replaceOrganism(newPosition, this);
                            oldX = x;
                            oldY = y;
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
        }
        else{
            world.replaceOrganism(getPosition(), this);
            world.replaceOrganism(getOldPosition(), null);
            oldX = x;
            oldY = y;
        }
    }

    public boolean reproduction(Organism other){
        ArrayList<short[]> emptyPlace = world.checkCellsAround(other.getPosition(), true);
        if(emptyPlace.getFirst()[0] == -1 || emptyPlace.getFirst() != null){
            return false;
        }
        else{
           return world.setOrganism(emptyPlace.getFirst(), other);
        }
    }


    public abstract boolean reboundAttack(Organism org);
}
