import World.Game;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {

        int[] dane;
        MainForm form = new MainForm();
        Game game;
        while (true) {
            dane = form.getInfo();
            if (dane[0] != -1) {
                game = new Game(dane);
                game.setVisible(true);
                form.dispose();
                break; // Wychodzimy z pętli, jeśli użytkownik wprowadził dane
            }
            // Opóźnienie, aby nie obciążać procesora
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}