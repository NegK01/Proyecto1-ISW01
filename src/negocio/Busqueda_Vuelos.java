package negocio;

import database.readingClasses.AeropuertoClassR;
import database.readingClasses.VuelosClassR;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Busqueda_Vuelos {

    public ArrayList Mostrar_Vuelos(String salida, String llegada, Integer cantidad, Date fecha) {
        AeropuertoClassR aeropuertoClassR = new AeropuertoClassR();
        aeropuertoClassR.leerAeropuertoTxt();

        VuelosClassR vuelosClassR = new VuelosClassR();
        vuelosClassR.LeerVuelosTxt();

        ArrayList Vuelos = new ArrayList();

        Integer id_salida = 0;
        Integer id_llegada = 0;

        for (int i = 0; i < aeropuertoClassR.getNOMBRE().size(); i++) {
            if (aeropuertoClassR.getNOMBRE().get(i).equals(salida)) {
                id_salida = aeropuertoClassR.getID().get(i);
            }

            if (aeropuertoClassR.getNOMBRE().get(i).equals(llegada)) {
                id_llegada = aeropuertoClassR.getID().get(i);
            }
        }
        
        SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < vuelosClassR.getID().size(); i++) {
            if (vuelosClassR.getID_IN().get(i).equals(id_salida)) {
                if (vuelosClassR.getID_OUT().get(i).equals(id_llegada)) {
                    
                    System.out.println(vuelosClassR.getFECHA_IN().get(i));
                    System.out.println(fecha);
                    
                    if (vuelosClassR.getFECHA_IN().get(i).equals(fecha)) {
                        System.out.println("hay unooooooo");
                    }
                }
            }
        }

        return Vuelos;
    }
}
