import World.World;

public class Main {
    public static void main(String[] args) {
        World game = new World((short)5, (short)5, false);
        int i = 0;
        while (game.getHumanIsAlive()){
            System.out.println("Turn: " + i++);
            game.takeATurn();
        }
    }
}