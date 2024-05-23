import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JTextField numberField1, numberField2;
    private JCheckBox checkBox1;
    private boolean checkBox1Selected = false;
    int number1 = -1, number2 = -1;
    public MainForm() {
        setTitle("Formularz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 2));

        JLabel label1 = new JLabel("Width:");
        add(label1);

        numberField1 = new JTextField();
        add(numberField1);

        JLabel label2 = new JLabel("Height:");
        add(label2);

        numberField2 = new JTextField();
        add(numberField2);

        checkBox1 = new JCheckBox("isHex");
        add(checkBox1);

        JButton button = new JButton("Wyślij");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
        add(button);

        setVisible(true);
    }

    private void submitForm() {
        String number1Str = numberField1.getText();
        String number2Str = numberField2.getText();

        checkBox1Selected = checkBox1.isSelected();

        try {
            number1 = Integer.parseInt(number1Str);
            number2 = Integer.parseInt(number2Str);
            if (number1 < 5 || number1 > 100 || number2 < 5 || number2 > 100) {
                JOptionPane.showMessageDialog(this, "Liczby muszą być między 5 a 100!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Podaj poprawne liczby!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tutaj możesz użyć wartości zmiennych number1, number2, checkBox1Selected i checkBox2Selected
        // na potrzeby przetwarzania danych z formularza
    }

    public static void main(String[] args) {
        new MainForm();
    }
    public int[] getInfo(){
        return new int[]{number1, number2, checkBox1Selected ? 1 : 0};
    }
}