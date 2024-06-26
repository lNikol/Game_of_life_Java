package World.organisms.animals;

import World.World;
import World.organisms.Organism;

import java.util.Random;

public class Human extends Animal{
    private boolean isAbilityOn = false, isAbilityActive = false, abilityKeyPressed = false; // isAbilityActive 50%
    private char lastKeyPressed = ' ';
    private short counterAfterAbility = 0, abilityCounter = -1;

    public Human(short y, short x, World w){
        super("Human.png", "Human", (short)5, (short)4, y, x, w);
        System.out.println("Human (" + x + "," + y + ") was created\n");
    }
    public Human(short y, short x, short power, short initiative, short age, World w) {
        super("Human.png", "Human", power, initiative, y, x, age, w);
        System.out.println("Human (" + x + "," + y + ") was created\n");
    }
    @Override
    public Organism copy(short[] position){
        return new Human(position[0], position[1], world);
    }

    @Override
    public void action(){
        System.out.println("\n\nYour turn\n\n");
        moveSystem();
    }

    public void setKey(char key){
        this.lastKeyPressed = key;
        if(key == 'o'){
            this.abilityKeyPressed = true;
            this.isAbilityOn = true;
        }
    }
    public char getKey(){
        return this.lastKeyPressed;
    }


    public void setAbilityKeyPressed(boolean pressed){
        this.abilityKeyPressed = pressed;
    }
    @Override
    public boolean reboundAttack(Organism org){
        return false;
    }

    public void setAbilityActive(){
        if(abilityKeyPressed){
            ++abilityCounter;
            if(abilityCounter <= 2){
                isAbilityActive = true;
            }
            else if(abilityCounter <= 4){
                isAbilityActive = new Random().nextBoolean();
            }
            if(abilityCounter >= 5){
                ++counterAfterAbility;
                isAbilityActive = false;
                isAbilityOn = false;
                if(counterAfterAbility >= 5){
                    counterAfterAbility = 0;
                    abilityCounter = 0;
                    abilityKeyPressed = false;
                }
                else{
                    System.out.println("The ability cannot be activated");
                }
            }

        }
    }

    public void moveSystem(){
        if(!world.getIsHex()){
            switch (lastKeyPressed){
                case 'w': {
                    if(this.y >= 1 && this.y < world.getHeight()){
                        setAbilityActive();
                        if(isAbilityOn && isAbilityActive){
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
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the top");
                    }
                    break;
                }
                case 's': {
                    if(this.y >= 0 && this.y < world.getHeight() - 1){
                        setAbilityActive();
                        if(isAbilityOn && isAbilityActive){
                            if(this.y + 2 < world.getHeight()){
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
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the bottom");
                    }
                    break;
                }
                case 'a': {
                    if(this.x >= 1 && this.x < world.getWidth()){
                        setAbilityActive();
                        if(isAbilityOn && isAbilityActive){
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
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the left");
                    }
                    break;
                }
                case 'd': {
                    if(this.x >= 0 && this.x < world.getWidth() - 1){
                        setAbilityActive();
                        if(isAbilityOn && isAbilityActive){
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
                        super.collision(world.getCell(this.getPosition()).org);
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
                case 'q': {
                    if(this.y >= 1 && this.y < world.getHeight()){
                        this.y -= 1;
                        System.out.println("You cannot move two squares (you will move one square) because there is a border there \n");
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the top");
                    }
                    break;
                }
                case 'w': {
                    if(this.y >= 1 && this.y < world.getHeight() && this.x >= 0 && this.x < world.getHeight()){
                        if(isAbilityOn){
                            x+=1;
                            y-=1;
                            world.setIsPlayerTurn(false);
                            super.collision(world.getCell(this.getPosition()).org);
                            world.setIsPlayerTurn(false);
                        }
                        else{
                            System.out.println("You cannot move to the top-top, please active the ability");
                        }
                    }
                    else{
                        System.out.println("You cannot move to the top-top, please active the ability");
                    }
                    break;
                }
                case 'e': {
                    if(this.x >= 0 && this.x < world.getHeight() && this.y >= 1 && this.y < world.getHeight()){
                        if(isAbilityOn){
                            if(x + 2 < world.getHeight()){
                                x+=2;
                                y-=1;
                                super.collision(world.getCell(this.getPosition()).org);
                                world.setIsPlayerTurn(false);
                            }
                        }
                        System.out.println("You cannot move to the right-top-top, please active the ability");
                    }
                    else{
                        System.out.println("You cannot move to the right-top-top, please active the ability");
                    }
                    break;
                }
                case 'd': {
                    if(this.x >= 0 && this.x < world.getWidth() -1){
                        if(isAbilityOn){
                            if(this.x + 2 < world.getHeight()){
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
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the right");
                    }
                    break;
                }
                case 's': {
                    if(this.y >= 0 && this.y < world.getHeight() && this.x >= 1 && this.x < world.getHeight()){
                        this.x-=1;
                        this.y+=1;
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the down-down");
                    }
                    break;
                }
                case 'a': { //left
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
                        super.collision(world.getCell(this.getPosition()).org);
                        world.setIsPlayerTurn(false);
                    }
                    else{
                        System.out.println("You cannot move to the left");
                    }
                    break;
                }
                default: break;
            }
        }
    }
}