package World;

import World.organisms.Organism;
import World.organisms.animals.*;
import World.organisms.plants.*;

import java.util.Random;
import java.util.Vector;

public class World {
    //map[human->getY()][human->getX()]->org = human;
    // zrobić klasę map z arg short[] i organism
    Map map;
    short width, height;
    Vector<Organism> organisms = new Vector<Organism>();
    Vector<Organism> children = new Vector<Organism>();

    boolean isHex = false, readFile = false; // w zaleznosci od przycisku w menu bedzie hex lub kratka
    public World(short w, short h, boolean readFile){
        width = w;
        height = h;
        this.readFile = readFile;
        map = new Map(width, height, isHex);
    }
    public void generateWorld(){
        if(!isHex){
            if(readFile){

            }
            else{
                Human human = new Human((short) 0,(short) 0,this);
                organisms.add(human);
                for(short i = 0; i < height; ++i){
                    for(short j = 0; j < width; ++j){
                        /*\
                        *  public static Class<?>[] organisms = {
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
    * */
                        if (j % 4 == 1) {
                            // plant generate
                            short[] randPos = randomPosition();
                            try{
                                Organism.organisms[(i + j) % 5].getConstructor().newInstance(randPos, this);
                                organisms.add(map.getOrganism(randPos));
                            }
                            catch (Exception e){}
                        }
                        else if (j % 4 == 0) {
                            // animal generate
                            short[] randPos = randomPosition();
                            try{
                                Organism.organisms[(i + j) % 5 + 5].getConstructor().newInstance(randPos, this);
                                organisms.add(map.getOrganism(randPos));
                            }
                            catch (Exception e){}
                        }
                    }
                }
            }
        }
        else{
            if(readFile){

            }
            else{
                for(short i = 0; i < height; ++i){
                    for(short j = 0; j < width; ++j){
                    }
                }
            }
        }
    }
    // zastanowić się nad tym jak to lepiej zrobić
    // bo tej metody będę używał w Antylopie, roślinach, zwierzętach itp.

    public void addOrganism(short[] position, Organism org){
        this.map.setOrganism(position,org);
        organisms.add(org);
    }

    public void setOrganism(short[] position, Organism org){
        if(this.map.getOrganism(position) == null){
            children.add(org.copy(position));
        }
        else{
            System.out.println("setOrganism in World: Cannot set organism, the place by (y,x) " + position[0] + ", " + position[1]+" isn't null");
        }

    }

    public short[] checkCellsAround(short[] position) {
        short y = position[0], x = position[1];
        if(!isHex){
            for (short i = -1; i < 2; ++i) {
                for (short j = -1; j < 2; ++j) {
                    if (i == 0 && j == 0) continue;
                    if ((x + j <= width && x + j > 0) && (y + i <= height && y + i > 0)) {
                        Organism organism = map.getOrganism(new short[]{(short) (y + i), (short) (x + j)});
                        if (organism == null) {
                            x += j;
                            y += i;
                            return new short[]{y, x};
                        }
                    }
                }
            }
        }
        else{
            short dLines[] = { -1, 0, 1, 1, 0, -1 };
            short dPoses[] = { -1, -1, 0, 1, 1, 0 };

            for (short i = 0; i < 6; ++i) {
                short newRow = (short)(y + dLines[i]);
                short newCol = (short)(x + dPoses[i]);
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < height) {
                    if(true){
                        break;
                    }
                }
            }
        }
        return new short[]{-1,-1};
    }

    public short[] randomPosition(){
        if(!isHex){
            Random random = new Random();
            short x = (short)(random.nextInt(width));
            short y = (short)(random.nextInt(height));
            while (map.getOrganism(y, x) != null) {
                x = (short)(random.nextInt(width));
                y = (short)(random.nextInt(height));
            }
            return new short[] {y,x};
        }
        else{
            return new short[] {-1, -1};
        }
    }
}
