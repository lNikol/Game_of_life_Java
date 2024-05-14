package World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHumanListener implements KeyListener {

    public void startListening() {
        // Tworzenie okna słuchacza klawiszy
        // Tu możesz użyć swojego interfejsu użytkownika lub dowolnego kontekstu, w którym chcesz śledzić zdarzenia klawiatury
        // W tym przykładzie używam pustego interfejsu użytkownika
        // W prawdziwej aplikacji stworzyłbyś bardziej kompleksowy interfejs użytkownika
        javax.swing.JFrame frame = new javax.swing.JFrame("KeyListener Example");
        javax.swing.JPanel panel = new javax.swing.JPanel();
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Dodanie słuchacza klawiatury do panelu
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ta metoda jest wywoływana, gdy klawisz jest wciśnięty i zwolniony
        // W tym przykładzie ignorujemy tę metodę
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Ta metoda jest wywoływana, gdy klawisz jest wciśnięty
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'a' || e.getKeyChar() == 's' || e.getKeyChar() == 'd') {
            // Wywołanie innej funkcji
            someOtherFunction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ta metoda jest wywoływana, gdy klawisz zostanie zwolniony
        // W tym przykładzie ignorujemy tę metodę
    }

    // Przykładowa funkcja, którą chcemy wywołać po naciśnięciu klawiszy "l" lub "s"
    public void someOtherFunction() {
        System.out.println("Function called!");
        // Tutaj możesz dodać kod, który ma zostać wykonany po naciśnięciu klawiszy "l" lub "s"
    }
}