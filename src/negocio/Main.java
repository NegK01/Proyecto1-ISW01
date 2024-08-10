package negocio;

import com.formdev.flatlaf.FlatDarculaLaf;
import database.readingClasses.HistorialClassR;
import interfaz.JFInicioSesión;
import java.text.ParseException;
import java.time.LocalDateTime;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws ParseException {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        LocalDateTime ahora = LocalDateTime.now();
        System.out.println(ahora);
        
        HistorialClassR historialClassR = new HistorialClassR();
        historialClassR.leerHistorialTxt();
        System.out.println(historialClassR.getID());
        System.out.println(historialClassR.getCEDULA());
        System.out.println(historialClassR.getID_SALIDA());
        System.out.println(historialClassR.getID_LLEGADA());
        System.out.println(historialClassR.getID_ESCALA());
        System.out.println(historialClassR.getFECHA());
        System.out.println(historialClassR.getFECHAFORMATEADA());
        System.out.println(historialClassR.getCAN_BOLETOS());
        System.out.println(historialClassR.getASIENTOS());
        System.out.println(historialClassR.getDURACIONHORAS());
        System.out.println(historialClassR.getDURACIONMINUTOS() );
        System.out.println(historialClassR.getCOSTO());
        System.out.println(historialClassR.getFILEPATH());
        System.out.println(historialClassR.getTXT_SPLIT());
        
        Correo correo = new Correo();
//        correo.Correo();
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
        
        
    }
    
}