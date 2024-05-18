import World.World;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        int i = 0;
        while (game.getWorld().getHumanIsAlive()){
            System.out.println("Turn: " + i++);
            game.getWorld().takeATurn();
            game.saveToLog();
        }
        game.endGame();
    }
}