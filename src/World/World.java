package World;

import World.organisms.Organism;
import World.organisms.animals.Animal;
import World.organisms.animals.Human;
import World.organisms.plants.Plant;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class World {
    Map map;
    short width, height;
    ArrayList<Organism> organisms = new ArrayList<Organism>();
    ArrayList<Class<?>> organismsInGame = new ArrayList<Class<?>>();
    ArrayList<Organism> children = new ArrayList<Organism>();
    Human human;
    boolean isHex = false, readFile = false, keyPressed = false; // w zaleznosci od przycisku w menu bedzie hex lub kratka
    boolean humanIsAlive = true;
    boolean isPlayerTurn = false;
    String fileName;
    public World(short w, short h, boolean readFile, String fileName, boolean isH){
        this.width = w;
        this.height = h;
        this.readFile = readFile;
        this.fileName = fileName;
        this.isHex = isH;
        this.map = new Map(width, height, this);
        generateWorld();
    }
    public void generateWorld(){
        if(!isHex){
            if(readFile){
                readFromFile();
            }
            else{
                human = new Human((short) 0,(short) 0,this);
                organisms.add(human);
                this.map.setOrganism(new short[]{0,0}, human);
                for(short i = 0; i < height; ++i){
                    for(short j = 0; j < width; ++j){
                        if (j % 4 == 1) {
                            // plant generate
                            short[] randPos = randomPosition();
                            if(randPos[0] == -1){
                                continue;
                            }
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getCell(randPos).org);
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
                        }
                       else if (j % 4 == 0) {
                            // animal generate
                            short[] randPos = randomPosition();
                            if(randPos[0] == -1){
                                continue;
                            }
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5 + 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getCell(randPos).org);
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
            width = height;
            if(readFile){
                readFromFile();
            }
            else{
                human = new Human((short) 0,(short) 0,this);
                organisms.add(human);
                this.map.setOrganism(new short[]{0,0}, human);
                for(short i = 0; i < height; ++i){
                    for(short j = 0; j < height; ++j){
                        if (j % 4 == 1) {
                            // plant generate
                            short[] randPos = randomPosition();
                            if(randPos[0] == -1){
                                continue;
                            }
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getCell(randPos).org);
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
                        }
                        else if (j % 4 == 0) {
                            // animal generate
                            short[] randPos = randomPosition();
                            if(randPos[0] == -1){
                                continue;
                            }
                            try{
                                Class<?> org = Organism.organisms[(i + j) % 5 + 5];
                                if(!organismsInGame.contains(org)){
                                    organismsInGame.add(org);
                                }
                                map.setOrganism(randPos, (Organism) org.getConstructors()[0].newInstance(randPos, this));
                                organisms.add(map.getCell(randPos).org);
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean getKeyPressed(){
        return keyPressed;
    }
    public void setKeyPressed(boolean pressed){
        this.keyPressed = pressed;
    }
    public void setKey(char s){
        this.human.setKey(s);
    }
    public boolean setOrganism(short[] position, Organism org){
        if(this.map.getCell(position).org == null){
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
    public void setIsPlayerTurn(boolean isPlayerTurn){
        this.isPlayerTurn = isPlayerTurn;
    }
    public boolean getIsPlayerTurn(){
        return this.isPlayerTurn;
    }
    public Cell getCell(short[] position){
        return map.getCell(position);
    }
    // zastanowić się nad tym jak to lepiej zrobić
    // bo tej metody będę używał w Antylopie, roślinach, zwierzętach itp.
    public ArrayList<Cell> checkCellsAround(short[] position, boolean onlyOne) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        short y = position[0], x = position[1];
        if(!isHex){
            for (short i = -1; i < 2; ++i) {
                for (short j = -1; j < 2; ++j) {
                    if (i == 0 && j == 0) continue;
                    short newY = (short)(y + i);
                    short newX = (short)(x + j);
                    if ((newX < width && newX >= 0) && (newY < height && newY >= 0)) {
                        Cell cell = map.getCell(new short[]{newY, newX});
                        if (onlyOne && cell.org == null){
                            return new ArrayList<Cell>(Collections.singleton(cell));
                        }
                        neighbors.add(cell);
                    }
                }
            }
            if (onlyOne){
                if (!neighbors.isEmpty()){
                    return new ArrayList<Cell>(Collections.singleton(neighbors.getFirst()));
                }
                else{
                    neighbors.add(new Cell((short) -1,(short) -1,null));
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
        neighbors.add(new Cell((short) -1,(short) -1,null));
        return neighbors;
    }

    public short[] randomPosition(){
        Random random = new Random();
        short counter = 0;
        short x = (short)(random.nextInt(width));
        short y = (short)(random.nextInt(height));
        while (map.getCell(y, x).org != null) {
            x = (short)(random.nextInt(width));
            y = (short)(random.nextInt(height));
            if(++counter >= 300){
                return new short[] {-1, -1};
            }
        }
        return new short[] {y,x};
    }

    public void updateOrganisms(){
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
        organisms.sort(new Comparator<Organism>() {
            @Override
            public int compare(Organism a, Organism b) {
                if(a.getInitiative() == b.getInitiative()){
                    return Integer.compare(b.getAge(), a.getAge());
                }
                return Integer.compare(b.getInitiative(), a.getInitiative());
            }
        });
    }

    public void takeATurn(){
        // naprawić wyświetlanie człowieka podczas tury
        // gdzieś zostaje jego stary symbol

        humanIsAlive = (human == null) ? false : human.getIsAlive();
        if(humanIsAlive){
            for(short i = 0; i < organisms.size(); ++i){
                organisms.get(i).setAge((short)(organisms.get(i).getAge()+1));
                organisms.get(i).setHasMoved(false);
            }
            updateOrganisms();
            for(Organism org : organisms){
                if(!org.getHasMoved() && org.getIsAlive()){
                    org.setHasMoved(true);
                    org.action();
                }
                System.out.println();
            }
            updateWorld();
        }
        else{
            System.out.println("You have died");
        }
    }

    public void drawWorld(){
        String s = new String();
        for (short i = 0; i < height; ++i) {
            for (short j = 0; j < width; ++j) {
                Organism org = this.map.getCell(i, j).org;
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

    public void updateWorld(){
        for(short i = 0; i < height; ++i) {
            for(short j = 0; j < width; ++j) {
                Cell c = getCell(new short[]{i, j});
                c.setBackground(Color.white);
                c.removeAll();
                if(c.org != null) {
                    JLabel label = new JLabel(c.org.getName());
                    if(c.org instanceof Animal){
                        c.setBackground(Color.red);
                    }
                    else if(c.org instanceof Plant){
                        c.setBackground(Color.green);
                    }
                    c.add(label);
                }
                c.revalidate();
                c.repaint();
            }
        }
    }

    public void replaceOrganism(short[] position, Organism newOrg){
        this.map.replaceOrganism(position, newOrg);
    }

    public void deleteOrganism(Organism oldOrg){
        if(oldOrg == null){
            return;
        }
        oldOrg.setIsAlive(false);
        this.map.deleteOrganism(oldOrg);
        children.remove(oldOrg);
    }

    public short[] newPosition(Organism org, short dist){
        short w = (short)(this.width - 1);
        short h = (short)(this.height - 1);
        short[] pos = org.getPosition();
        short y = pos[0], x = pos[1];

        if(!isHex){
            if (y == 0) {
                if (x == 0) {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // right
                            x += dist;
                            break;
                        }
                        case 3: { // right-down
                            x += dist;
                            y += dist;
                            break;
                        }
                    }
                }
                else if (x == w) {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // left
                            x -= dist;
                            break;
                        }
                        case 3: { // left-down
                            x -= dist;
                            y += dist;
                            break;
                        }
                    }
                }
                else {
                    switch (new Random().nextInt(5) + 1) {
                        case 1: { // down
                            y += dist;
                            break;
                        }
                        case 2: { // right
                            x += (x + dist <= w)? dist :1;
                            break;
                        }
                        case 3: { // left
                            x -= (x - dist >= 0)? dist :1;
                            break;
                        }
                        case 4: { //left-down
                            if(x - dist >= 0){
                                x-=dist;
                                y+=dist;
                            }
                            else{
                                x-=1;
                                y+=1;
                            }
                            break;
                        }
                        case 5: { //right-down
                            if(x + dist <= w){
                                x+=dist;
                                y+=dist;
                            }
                            else{
                                x+=1;
                                y+=1;
                            }
                            break;
                        }
                    }
                }
            }
            else if (y == h) {
                if (x == 0) {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // right
                            x += dist;
                            break;
                        }
                        case 3: { // right-top
                            x += dist;
                            y -= dist;
                            break;
                        }
                    }
                }
                else if (x == w) {
                    switch (new Random().nextInt(3) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // left
                            x -= dist;
                            break;
                        }
                        case 3: { // left-top
                            x -= dist;
                            y -= dist;
                            break;
                        }
                    }
                }
                else {
                    switch (new Random().nextInt(5) + 1) {
                        case 1: { // top
                            y -= dist;
                            break;
                        }
                        case 2: { // left
                            x -= (x - dist >= 0)? dist :1;
                            break;
                        }
                        case 3: { // right
                            x += (x + dist <= w)? dist :1;
                            break;
                        }
                        case 4: { // right-top
                            if(x + dist <= w){
                                x+=dist;
                                y-=dist;
                            }
                            else{
                                x+=1;
                                y-=1;
                            }
                            break;
                        }
                        case 5: { // left-top
                            if(x - dist >= 0){
                                x-=dist;
                                y-=dist;
                            }
                            else{
                                x-=1;
                                y-=1;
                            }
                            break;
                        }
                    }
                }
            }
            else if (x == w && (y >= 1 && y < h)) {
                switch (new Random().nextInt(5) + 1) {
                    case 1: { // down
                        y += (y + dist <= h)? dist :1;
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
                    case 4: { // left-top
                        if(y-dist >= 0){
                            x-=dist;
                            y-=dist;
                        }
                        else{
                            x-=1;
                            y-=1;
                        }
                        break;
                    }
                    case 5: { // left-down
                        if(y + dist <= h){
                            x-=dist;
                            y+=dist;
                        }
                        else{
                            x-=1;
                            y+=1;
                        }
                        break;
                    }
                }
            }
            else if (x == 0 && (y >= 1 && y < h)) {
                switch (new Random().nextInt(5) + 1) {
                    case 1: { // down
                        y += (y + dist <= h)? dist :1;
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
                    case 4: { // right-top
                        if(y-dist >= 0){
                            x+=dist;
                            y-=dist;
                        }
                        else{
                            x+=1;
                            y-=1;
                        }
                        break;
                    }
                    case 5: { // right-down
                        if(y + dist <= h){
                            x+=dist;
                            y+=dist;
                        }
                        else{
                            x+=1;
                            y+=1;
                        }
                        break;
                    }
                }
            }
            else {
                switch (new Random().nextInt(8) + 1) {
                    case 1: { // down
                        y += (y + dist <= h)? dist :1;
                        break;
                    }
                    case 2: { // top
                        y -= (y - dist >= 0)? dist :1;
                        break;
                    }
                    case 3: { // right
                        x += (x + dist <= w)? dist :1;
                        break;
                    }
                    case 4: { // left
                        x -= (x - dist >= 0)? dist :1;
                        break;
                    }
                    case 5: { // left-top
                        if(y - dist >= 0 && x - dist >= 0){
                            x-=dist;
                            y-=dist;
                        }
                        else{
                            x-=1;
                            y-=1;
                        }
                        break;
                    }
                    case 6: { // left-down
                        if(y + dist <= h && x - dist >= 0){
                            x-=dist;
                            y+=dist;
                        }
                        else{
                            x-=1;
                            y+=1;
                        }
                        break;
                    }
                    case 7: { // right-top
                        if(y - dist >= 0 && x + dist <= w){
                            x+=dist;
                            y-=dist;
                        }
                        else{
                            x+=1;
                            y-=1;
                        }
                        break;
                    }
                    case 8: { // right-down
                        if(y + dist <= h && x + dist <= w){
                            x+=dist;
                            y+=dist;
                        }
                        else{
                            x+=1;
                            y+=1;
                        }
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
    public boolean getHumanIsAlive(){
        return this.humanIsAlive;
    }

    public short getHeight(){
        return this.height;
    }

    public boolean getIsHex(){
        return isHex;
    }

    public Map getMap(){
        return this.map;
    }
    public ArrayList<Class<?>> getOrganismsInGame() {
        return this.organismsInGame;
    }
    public void setIsHex(boolean isH){
        this.isHex = isH;
    }

    public String saveToLog(){
        updateOrganisms();
        String toSave = "";
        for (short i = 0; i < organisms.size(); ++i) {
            toSave+=organisms.get(i).writeToLog();
        };
        return toSave;
    }
    public void readFromFile(){
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach((line)->{
                if (!line.startsWith("World:")) {
                    String firstWord = line.split("\\(y,x\\): ")[0];
                    String regex = "\\d+";
                    short[] numbers = new short[5];
                    int index = 0;
                    Matcher matcher = Pattern.compile(regex).matcher(line);
                    while (matcher.find()) {
                        numbers[index++] = Short.parseShort(matcher.group());
                    }

                    for (Class<?> organism : Organism.organisms) {
                        if (firstWord.equals(organism.getSimpleName())){
                            short[] pos = new short[]{numbers[0],numbers[1]};
                            try {
                                if(firstWord.equals("Human")){
                                    this.human = new Human(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], this);
                                    map.setOrganism(pos, this.human);
                                    organisms.add(this.human);
                                }
                                else{
                                    map.setOrganism(pos, (Organism) organism.getConstructors()[1].newInstance(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], this));
                                    organisms.add(map.getCell(pos).org);
                                }

                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                            if(!organismsInGame.contains(organism)){
                                organismsInGame.add(organism);
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        }
    }
}
