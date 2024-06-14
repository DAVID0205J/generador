import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class Main {

    private static final String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::generador);
    }

    private static void generador() {
        JFrame frame = new JFrame("Generador de Contraseñas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null); // Centrar la ventana

        JLabel label = new JLabel("Ingrese la longitud de la contraseña:");
        frame.add(label);

        JTextField lengthField = new JTextField(5);
        frame.add(lengthField);

        JButton generateButton = new JButton("Generar");
        frame.add(generateButton);

        JLabel passwordLabel = new JLabel();
        frame.add(passwordLabel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lengthText = lengthField.getText();
                try {
                    int length = Integer.parseInt(lengthText);
                    if (length < 1) {
                        JOptionPane.showMessageDialog(null, "La longitud debe ser al menos 1", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String password = generatePassword(length);
                    passwordLabel.setText("Contraseña generada: " + password);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    private static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(letras.length());
            password.append(letras.charAt(index));
        }

        return password.toString();
    }
}
