package negocio;

import com.formdev.flatlaf.FlatDarculaLaf;
import interfaz.JFInicioSesión;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws ParseException {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Correo correo = new Correo();
//        correo.Correo();
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
        
        
    }
    
}