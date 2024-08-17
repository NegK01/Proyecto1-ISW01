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
        
//        asignacionDeAsientos.matriz(); // hacemos la matriz una vez, ya que sino nunca se hara y quedara nula
//        AsignacionDeAsientos asignacionDeAsientos = new AsignacionDeAsientos();
//        String si = "100,123,12,345,12,155,1000,12,4,101000";
//        asignacionDeAsientos.guardarCedulaActual(123456789);
//        asignacionDeAsientos.AsignacionDeAsientos(si);
        
        
        Correo correo = new Correo();
//        correo.Correo();
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
        
        
    }
    
}