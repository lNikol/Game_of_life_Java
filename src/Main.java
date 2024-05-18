import World.World;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.getWorld().takeATurn();

        int i = 0;

        while (game.getWorld().getHumanIsAlive()){
            System.out.println("Turn: " + i++);
            game.getWorld().takeATurn();
        }

    }
}