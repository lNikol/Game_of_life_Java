import World.Game;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Game game = new Game();
        game.setVisible(true);
        int i = 0;
       /* while (game.getWorld().getHumanIsAlive()){
            System.out.println("Turn: " + i++);
            game.getWorld().takeATurn();
            //game.saveToLog();
        }
        game.endGame();*/
    }
}