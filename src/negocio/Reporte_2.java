package negocio;

import database.readingClasses.HistorialClassR;
import database.readingClasses.VuelosClassR;
import database.readingClasses.TripulacionesClassR;
import database.readingClasses.AerolineaClassR;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class Reporte_2 {

    private ArrayList DatosAero = new ArrayList();

    private Integer id_Aerolinea;

    public ArrayList VuelosPorAerolinea(String aerolineaSelect) throws ParseException {

        HistorialClassR historial = new HistorialClassR();
        historial.leerHistorialTxt();
        AerolineaClassR aero = new AerolineaClassR();
        aero.leerAerolineaTxt();
        VuelosClassR vuelos = new VuelosClassR();
        vuelos.LeerVuelosTxt();

        id_Aerolinea = 0;
        for (int x = 0; x < aero.getNOMBRE().size(); x++) {
            if (aero.getNOMBRE().get(x).equals(aerolineaSelect)) {
                id_Aerolinea = aero.getID().get(x);
            }
        }
        
        HashSet<Integer> filtrarVuelos = new HashSet<>();
        for (Integer item : historial.getID()) {

            String identificador = item.toString();

            String id_1 = String.valueOf(identificador).substring(0, 3);
            String id_2 = String.valueOf(identificador).substring(3, 6);

            Integer identiParte1 = Integer.parseInt(id_1);
            Integer identiParte2 = Integer.parseInt(id_2);

            filtrarVuelos.add(identiParte1);

            filtrarVuelos.add(identiParte2);
        }

        for (Integer vuelo : filtrarVuelos) {

            for (int i = 0; i < vuelos.getID().size(); i++) {

                if (vuelos.getID().get(i).equals(vuelo) && vuelos.getID_AERO().get(i).equals(id_Aerolinea)) {

                    buscarTripulacion(i);

                }
            }
        }

        return DatosAero;

    }

    public void buscarTripulacion(int i) {
        TripulacionesClassR tripulacion = new TripulacionesClassR();
        tripulacion.LeerTripulacionesTxt();

        VuelosClassR vuelos = new VuelosClassR();
        vuelos.LeerVuelosTxt();

        ArrayList vuelo = new ArrayList();

        String cedulas = vuelos.getID_TRIPULACION().get(i);
        String[] cedulasSplit = cedulas.split("\\.");

//        String cedulaInt1 = String.valueOf(cedulas).substring(0, 9);
//        String cedulaInt2 = String.valueOf(cedulas).substring(10, 19);
        
        Integer cedulaPiloto = Integer.valueOf(cedulasSplit[0]);
        Integer cedulaMosa = Integer.valueOf(cedulasSplit[1]);

        String nombrePiloto = "";
        String nombreMosa = "";

        for (int j = 0; j < tripulacion.getNOMBRES().size(); j++) {
            
            if (tripulacion.getCEDULA().get(j).equals(cedulaPiloto)) {

                nombrePiloto = tripulacion.getNOMBRES().get(j);

            } else if (tripulacion.getCEDULA().get(j).equals(cedulaMosa)) {

                nombreMosa = tripulacion.getNOMBRES().get(j);

            }
        }

        vuelo.add(cedulaPiloto);

        vuelo.add(nombrePiloto);

        vuelo.add(cedulaMosa);

        vuelo.add(nombreMosa);

        Integer id_avion = vuelos.getID_AVION().get(i);
        vuelo.add(id_avion);

        DatosAero.add(vuelo);
    }
}
