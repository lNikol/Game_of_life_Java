package World.organisms.animals;

import World.World;
import World.organisms.Organism;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Human extends Animal{
    private boolean isAbilityOn = false;
    private char lastKeyPressed;

    public Human(short y, short x, World w){
        super("Human.png", "Human", (short)5, (short)4, y, x, w);
        System.out.print("Human (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Human(position[0], position[1], world);
    }

    @Override
    public void action(){
        System.out.println("Action in human - waiting for key press");
        world.setIsPlayerTurn(true);

        // dodać inny warunek dla pętli while
        while (world.getIsPlayerTurn()) {
            if (world.getKeyPressed()) {
                System.out.println("In action");
                world.setKeyPressed(false); // resetujemy stan po wykryciu naciśnięcia
                moveSystem();
            }
            try {
                Thread.sleep(100); // krótka przerwa, aby nie obciążać procesora
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("After while");
        world.setIsPlayerTurn(false);
    }

    public void setKey(char key){
        this.lastKeyPressed = key;
    }
    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }

    public void moveSystem(){
        if(!world.getIsHex()){
            switch (lastKeyPressed){
                case 'w': {
                    if(this.y >= 1 && this.y < world.getHeight()){
                        if(isAbilityOn){
                            if(this.y - 2 >= 0){
                                this.y -= 2;
                            }
                            else{
                                this.y -= 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.y -= 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the top");
                    }
                    break;
                }
                case 's': {
                    if(this.y >= 0 && this.y < world.getHeight() - 1){
                        if(isAbilityOn){
                            if(this.y + 2 >= world.getHeight() - 1){
                                this.y += 2;
                            }
                            else{
                                this.y += 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.y += 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the bottom");
                    }
                    break;
                }
                case 'a': {
                    if(this.x >= 1 && this.x < world.getWidth()){
                        if(isAbilityOn){
                            if(this.x - 2 >= 0){
                                this.x -= 2;
                            }
                            else{
                                this.x -= 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.x -= 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the left");
                    }
                    break;
                }
                case 'd': {
                    if(this.x >= 0 && this.x < world.getWidth() - 1){
                        if(isAbilityOn){
                            if(this.x + 2 < world.getWidth()){
                                this.x += 2;
                            }
                            else{
                                this.x += 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.x += 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the right");
                    }
                    break;
                }
                default: break;
            }
        }
        else{
            switch (lastKeyPressed){
               /* case 'w': {
                    if(this.y >= 1 && this.y < world.getHeight()){
                        if(isAbilityOn){
                            if(this.y - 2 >= 0){
                                this.y -= 2;
                            }
                            else{
                                this.y -= 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.y -= 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the top");
                    }
                    break;
                }
                case 'q': {
                    if(this.y >= 0 && this.y < world.getHeight()){
                        if(isAbilityOn){
                            if(this.y + 2 >= world.getHeight()){
                                this.y += 2;
                            }
                            else{
                                this.y += 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.y += 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the bottom");
                    }
                    break;
                }
                case 'e': {
                    if(this.x >= 1 && this.x < world.getWidth()){
                        if(isAbilityOn){
                            if(this.x - 2 >= 0){
                                this.x -= 2;
                            }
                            else{
                                this.x -= 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.x -= 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the left");
                    }
                    break;
                }
                case 'a': {
                    if(this.x >= 1 && this.x < world.getWidth()){
                        if(isAbilityOn){
                            if(this.x - 2 >= 0){
                                this.x -= 2;
                            }
                            else{
                                this.x -= 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.x -= 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the left");
                    }
                    break;
                }
                case 's': {
                    if(this.y >= 0 && this.y < world.getHeight()){
                        if(isAbilityOn){
                            if(this.y + 2 >= world.getHeight()){
                                this.y += 2;
                            }
                            else{
                                this.y += 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.y += 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the bottom");
                    }
                    break;
                }
                case 'd': {
                    if(this.x >= 0 && this.x < world.getWidth()){
                        if(isAbilityOn){
                            if(this.x + 2 < world.getWidth()){
                                this.x += 2;
                            }
                            else{
                                this.x += 1;
                                System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                            }
                        }
                        else{
                            this.x += 1;
                        }
                        super.collision(world.getOrganism(this.getPosition()).org);
                    }
                    else{
                        System.out.println("You cannot move to the right");
                    }
                    break;
                }*/
                default: break;
            }
        }
    }
}
