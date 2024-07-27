package negocio;

import dataBase.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import interfaz.VentanaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
//        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
//        ventanaPrincipal.setVisible(true);
        //Comentado para poder probar unicamente el POO de lectura
        
        System.out.println("----Aerolineas-----------");
        AerolineaClass asd = new AerolineaClass();
        asd.leerAerolineaTxt();
        
        System.out.println(asd.getID() + "\n" + asd.getNOMBRE());
        
        
        System.out.println("----Aeropuertos----------");
        AeropuertoClass was = new AeropuertoClass();
        was.leerAeropuertoTxt();
        
        System.out.println(was.getID() + "\n" + was.getNOMBRE() + "\n" + was.getPAISES());
        
        
        System.out.println("----Tripulaciones--------");
        
        
        System.out.println("----Aviones--------------");
    }
    
}
