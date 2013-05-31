package servidorweb.swing.vistas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author Miguel González Gómez
 */
public class JTextFieldNumerico extends JTextField {
    
    public JTextFieldNumerico() {
        super();
        addKeyListener(new EscuchadorTeclas());
    }
    
    public JTextFieldNumerico(int ancho) {
        super(ancho);
        addKeyListener(new EscuchadorTeclas());
    }
    
    public JTextFieldNumerico(String valor) {
        super(valor);
        addKeyListener(new EscuchadorTeclas());
    }
    
    private class EscuchadorTeclas implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();

            //Si no es un número y no es delete o backspace consumimos el evento
            if (!(Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE))) {
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
