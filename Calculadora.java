import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculadora extends JFrame implements ActionListener {
    JTextField pantalla;
    JButton[] botones;
    JButton suma, resta, multiplicacion, division, igual, borrar, punto;
    String operador = "";
    double numero1 = 0;
    boolean decimal = false;

    public Calculadora() {
        setSize(400, 600);
        setTitle("Casio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Font f = new Font("Arial", Font.BOLD, 30);

        pantalla = new JTextField();
        pantalla.setBounds(1, 20, 380, 80);
        pantalla.setEnabled(false);
        pantalla.setFont(new Font("Arial", Font.BOLD, 40));
        pantalla.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(pantalla);

        int x = 1, y = 420;
        botones = new JButton[10];
        botones[0] = new JButton("0");
        botones[0].setBounds(x, y, 80, 80);
        botones[0].setFont(f);
        botones[0].addActionListener(this);
        add(botones[0]);

        y = 320;
        int contColumnas = 1;
        for (int i = 1; i < 10; i++) {
            botones[i] = new JButton(i + "");
            botones[i].setBounds(x, y, 80, 80);
            botones[i].addActionListener(this);
            botones[i].setFont(f);
            add(botones[i]);
            x = x + 100;
            if (contColumnas == 3) {
                y = y - 100;
                x = 1;
                contColumnas = 0;
            }
            contColumnas = contColumnas + 1;
        }

        // Botones de operaciones
        suma = new JButton("+");
        suma.setBounds(301, 120, 80, 80);
        suma.setFont(f);
        suma.addActionListener(this);
        add(suma);

        resta = new JButton("-");
        resta.setBounds(301, 220, 80, 80);
        resta.setFont(f);
        resta.addActionListener(this);
        add(resta);

        multiplicacion = new JButton("*");
        multiplicacion.setBounds(301, 320, 80, 80);
        multiplicacion.setFont(f);
        multiplicacion.addActionListener(this);
        add(multiplicacion);

        division = new JButton("/");
        division.setBounds(301, 420, 80, 80);
        division.setFont(f);
        division.addActionListener(this);
        add(division);

        igual = new JButton("=");
        igual.setBounds(101, 420, 180, 80);
        igual.setFont(f);
        igual.addActionListener(this);
        add(igual);

        borrar = new JButton("C");
        borrar.setBounds(1, 120, 80, 80);
        borrar.setFont(f);
        borrar.addActionListener(this);
        add(borrar);

        punto = new JButton(".");
        punto.setBounds(101, 320, 80, 80);
        punto.setFont(f);
        punto.addActionListener(this);
        add(punto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        if (boton == borrar) {
            pantalla.setText("");
            numero1 = 0;
            operador = "";
            decimal = false;
        } else if (boton == igual) {
            double numero2 = Double.parseDouble(pantalla.getText());
            double resultado = 0;
            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }
            pantalla.setText(String.valueOf(resultado));
            operador = "";
            decimal = false;
        } else if (boton == suma || boton == resta || boton == multiplicacion || boton == division) {
            numero1 = Double.parseDouble(pantalla.getText());
            operador = boton.getText();
            pantalla.setText("");
            decimal = false;
        } else if (boton == punto) {
            if (!decimal) {
                pantalla.setText(pantalla.getText() + ".");
                decimal = true;
            }
        } else {
            pantalla.setText(pantalla.getText() + boton.getText());
        }
    }

    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        c.setVisible(true);

    }
}
