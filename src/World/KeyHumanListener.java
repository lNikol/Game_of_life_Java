package World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import World.organisms.animals.Human;

public class KeyHumanListener implements KeyListener {

    private Human human;
    private boolean isPlayerTurn = false;

    public KeyHumanListener(Human human, boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
        this.human = human;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Metoda nie jest używana w tym przypadku
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Metoda nie jest używana w tym przypadku
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isPlayerTurn){
            human.setKey(e.getKeyChar());
        }

    }


    public void setIsPlayerTurn(boolean isPlayerTurn){
        this.isPlayerTurn = isPlayerTurn;
    }
}