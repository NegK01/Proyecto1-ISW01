package negocio;

import com.formdev.flatlaf.FlatDarculaLaf;
import interfaz.VentanaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Proyecto {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
//        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
//        ventanaPrincipal.setVisible(true);
        //Comentado para poder probar unicamente el POO de lectura
        
        AerolineaClass asd = new AerolineaClass();
        asd.leerAerolineaTxt();
        
        System.out.println(asd.getID() + "\n" + asd.getNOMBRE());
        
    }
    
}
