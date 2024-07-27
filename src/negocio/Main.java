package negocio;

import dataBase.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import interfaz.JFInicioSesión;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
        
        
        
        
        System.out.println("----------Aerolineas----------");
        AerolineaClass asd = new AerolineaClass();
        
        asd.leerAerolineaTxt();
        
        System.out.println(asd.getID() + "\n\n" + asd.getNOMBRE());
        
        
        System.out.println("----------Aeropuertos----------");
        AeropuertoClass was = new AeropuertoClass();
        
        was.leerAeropuertoTxt();
        
        System.out.println(was.getID() + "\n\n" + was.getNOMBRE() + "\n\n" + was.getPAISES());
        
        
        System.out.println("----------Tripulaciones----------");
        TripulacionesClass nene = new TripulacionesClass();
        
        nene.LeerTripulacionesTxt();
        
        System.out.println(nene.getCEDULA()+ "\n\n" + nene.getNOMBRES() + "\n\n" + nene.getIDS()
                + "\n\n" + nene.getROL() + "\n\n" + nene.getESTADO());
        
        System.out.println("----------Aviones----------");
        AvionesClass sese = new AvionesClass();
        
        sese.LeerAvionesTxt();
        
        System.out.println(sese.getIDS() + "\n\n" + sese.getMODELO() + "\n\n" + sese.getIDS_AERO() 
                + "\n\n" + sese.getESTADO());
    }
    
}