package World;

import World.organisms.Organism;
import World.organisms.animals.Animal;
import World.organisms.animals.Human;

import java.util.*;

public class World {
    Map map;
    short width, height;
    ArrayList<Organism> organisms = new ArrayList<Organism>();
    ArrayList<Class<?>> organismsInGame = new ArrayList<Class<?>>();
    ArrayList<Organism> children = new ArrayList<Organism>();
    Human human;
    boolean isHex = false, readFile = false; // w zaleznosci od przycisku w menu bedzie hex lub kratka
    public World(short w, short h, boolean readFile){
        this.width = w;
        this.height = h;
        this.readFile = readFile;
        this.map = new Map(width, height, isHex);

        human = new Human((short) 0,(short) 0,this);
        organisms.add(human);
        organismsInGame.add(Organism.organisms[(short)(Organism.organisms.length-1)]);
        this.map.setOrganism(new short[]{0,0}, human);
        generateWorld();
    }
    public void generateWorld(){
        if(!isHex){
            if(readFile){

            }
            else{
                for(short i = 0; i < height; ++i){
                    for(short j = 0; j < width; ++j){
                        if (j % 4 == 1) {
                            // plant generate
                            short[] randPos = randomPosition();
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getOrganism(randPos));
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
                        }
                        else if (j % 4 == 0) {
                            // animal generate
                            short[] randPos = randomPosition();
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5 + 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getOrganism(randPos));
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
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

    public void addOrganism(short[] position, Organism org){
        this.map.setOrganism(position,org);
        organisms.add(org);
    }

    public boolean setOrganism(short[] position, Organism org){
        if(this.map.getOrganism(position) == null){
            Organism child = org.copy(position);
            children.add(child);
            this.map.setOrganism(position, child);
            return true;
        }
        else{
            System.out.print("setOrganism in World: Cannot set organism, the place by (y,x) " + position[0] + ", " + position[1]+" isn't null");
            return false;
        }
    }

    public Organism getOrganism(short[] position){
        return map.getOrganism(position);
    }
    // zastanowić się nad tym jak to lepiej zrobić
    // bo tej metody będę używał w Antylopie, roślinach, zwierzętach itp.
    public ArrayList<short[]> checkCellsAround(short[] position, boolean onlyOne) {
        // return ArrayList<Organism>

        ArrayList<short[]> neighbors = new ArrayList<short[]>();
        short y = position[0], x = position[1];
        if(!isHex){
            for (short i = -1; i < 2; ++i) {
                for (short j = -1; j < 2; ++j) {
                    if (i == 0 && j == 0) continue;
                    short newY = (short)(y + i);
                    short newX = (short)(x + j);
                    if ((newX < width && newX >= 0) && (newY < height && newY >= 0)) {
                        Organism organism = map.getOrganism(new short[]{newY, newX});
                        if (onlyOne && organism == null){
                            return new ArrayList<short[]>(Collections.singleton(new short[]{newY, newX}));
                        }

                        neighbors.add(new short[]{newY,newX});
                    }
                }
            }
            if (onlyOne){
                if (!neighbors.isEmpty()){
                    return new ArrayList<short[]>(Collections.singleton(neighbors.getFirst()));
                }
                else{
                    neighbors.add(new short[]{-1,-1});
                    return neighbors;
                }

            }
            return neighbors;
        }
        else{
            short[] dLines = { -1, 0, 1, 1, 0, -1 };
            short[] dPoses = { -1, -1, 0, 1, 1, 0 };

            for (short i = 0; i < 6; ++i) {
                short newRow = (short)(y + dLines[i]);
                short newCol = (short)(x + dPoses[i]);
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < height) {
                    if(true){
                        break;
                    }
                }
            }
            /*if (onlyOne){
                return new ArrayList<short[]>(Collections.singleton(neighbors.getFirst()));
            }
            return neighbors;*/
        }
        neighbors.add(new short[]{-1,-1});
        return neighbors;
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

    public void takeATurn(){
        if(human.getIsAlive()){
            short childrenSize = (short)children.size();
            for(short i = 0; i < childrenSize; ++i){
                if(children.get(i) != null){
                    organisms.add(children.get(i));
                }
            }
            children.clear();

            for(short i = (short) (organisms.size() - 1); i >= 0; --i) {
                if (!organisms.get(i).getIsAlive()) {
                    organisms.remove(i);
                }
            }

            for(short i = 0; i < organisms.size(); ++i){
                organisms.get(i).setAge((short)(organisms.get(i).getAge()+1));
                organisms.get(i).setHasMoved(false);
            }
            organisms.sort(new Comparator<Organism>() {
                @Override
                public int compare(Organism a, Organism b) {
                    if(a.getInitiative() == b.getInitiative()){
                        return Integer.compare(b.getAge(), a.getAge());
                    }
                    return Integer.compare(b.getInitiative(), a.getInitiative());
                }
            });
            drawWorld();
            for(Organism org : organisms){
                if(!org.getHasMoved() && org.getIsAlive()){
                    org.setHasMoved(true);
                    org.action();
                }
                System.out.println();
            }

            drawWorld();
        }
        else{
            System.out.println("You have died");
            return;
        }
    }
    public void drawWorld(){
        String s = new String();
        for (short i = 0; i < height; ++i) {
            for (short j = 0; j < width; ++j) {
                Organism org = this.map.getOrganism(i, j);
                if(org == null){
                    s+=" . ";
                }
                else{
                    char sym;
                    if(Objects.equals(org.getImage(), "Grass.png")){
                        sym = 'g';
                    }
                    else if(Objects.equals(org.getImage(), "Hogweed.png")){
                        sym = 'h';
                    }
                    else if(Objects.equals(org.getImage(), "Wolfberries.png")){
                        sym = 'w';
                    }
                    else{
                        sym = org.getImage().charAt(0);
                    }
                    s+= " "+sym+" ";
                }
            }
            s+="\n";
        }
        System.out.println(s);
    }

    public void replaceOrganism(short[] position, Organism newOrg){
        this.map.replaceOrganism(position, newOrg);
    }

    public void deleteOrganism(Organism oldOrg){
        if(oldOrg == null){
            return;
        }
        this.map.deleteOrganism(oldOrg);
        children.remove(oldOrg);
    }

    public short[] newPosition(Organism org, short dist){
        short width = (short)(this.width - 1);
        short height = (short)(this.height - 1);
        short[] pos = org.getPosition();
        short y = pos[0], x = pos[1];

        if(!getIsHex()){
            // ruch tylko w 4 strony, nie w 8 stron (brak kątowych ruchów)
            if (y == 0) {
                if (x == 0) {
                    switch (new Random().nextInt(2) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // right
                            x += dist;
                            break;
                        }
                    }
                }
                else if (x == width) {
                    switch (new Random().nextInt(2) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // left
                            x -= dist;
                            break;
                        }
                    }
                }
                else {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // right
                            x += (x + dist <= width)? dist :1;
                            break;
                        }
                        case 3: { // left
                            x -= (x - dist >= 0)? dist :1;
                            break;
                        }
                    }
                }
            }
            else if (y == height) {
                if (x == 0) {
                    switch (new Random().nextInt(2) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // right
                            x += dist;
                            break;
                        }
                    }
                }
                else if (x == width) {
                    switch (new Random().nextInt(2) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // left
                            x -= dist;
                            break;
                        }
                    }
                }
                else {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // left
                            x -= (x - dist >= 0)? dist :1;
                            break;
                        }
                        case 3: { // right
                            x += (x + dist <= width)? dist :1;
                            break;
                        }
                    }
                }
            }
            else if (x == width && (y >= 1 && y < height)) {
                switch (new Random().nextInt(3) + 1) {
                    case 1: { // down
                        y += (y + dist <= height)? dist :1;
                        break;
                    }
                    case 2: { // top
                        y -= (y - dist >= 0)? dist :1;
                        break;
                    }
                    case 3: { // left
                        x -= dist;
                        break;
                    }
                }
            }
            else if (x == 0 && (y >= 1 && y < height)) {
                switch (new Random().nextInt(3) + 1) {
                    case 1: { // down
                        y += (y + dist <= height)? dist :1;
                        break;
                    }
                    case 2: { // top
                        y -= (y - dist >= 0)? dist :1;
                        break;
                    }
                    case 3: { // right
                        x += dist;
                        break;
                    }
                }
            }
            else {
                switch (new Random().nextInt(4) + 1) {
                    case 1: { // down
                        y += (y + dist <= height)? dist :1;
                        break;
                    }
                    case 2: { // top
                        y -= (y - dist >= 0)? dist :1;
                        break;
                    }
                    case 3: { // right
                        x += (x + dist <= width)? dist :1;
                        break;
                    }
                    case 4: { // left
                        x -= (x - dist >= 0)? dist :1;
                        break;
                    }
                }
            }
        }
        else{

        }
        return new short[]{y,x};
    }

    public short getWidth(){
        return this.width;
    }

    public short getHeight(){
        return this.height;
    }

    public boolean getIsHex(){
        return isHex;
    }
    public void setIsHex(boolean isH){
        this.isHex = isH;
    }
}
