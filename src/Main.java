import World.World;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        World game = new World((short)5, (short)5, false);
        for(short i = 0; i < 5; ++i){
            System.out.println("Turn: " + i);
            game.takeATurn();
        }
    }
}