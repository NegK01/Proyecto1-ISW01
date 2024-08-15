package database.writingClasses;

import database.readingClasses.TripulacionesClassR;
import java.io.*;

public class TripulacionesClassW {
    private TripulacionesClassR tripulacionesClass;
    private String linea;
    private String TXTSPLIT;
    
    
    
    public TripulacionesClassW() {
        tripulacionesClass = new TripulacionesClassR();
        tripulacionesClass.LeerTripulacionesTxt();
        TXTSPLIT = ",";
    }
        
public void EscribirTripulacionesTxt(String cedulaPiloto, String cedulaServicioCliente, Integer nuevoEstado) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/files/Tripulaciones.txt"))) {
        System.out.println("Escribiendo datos en el archivo...");
        
        for (int i = 0; i < tripulacionesClass.getCEDULA().size(); i++) {
            String cedulaActual = String.valueOf(tripulacionesClass.getCEDULA().get(i));
            String rolActual = tripulacionesClass.getROL().get(i);
            Integer estadoActual = tripulacionesClass.getESTADO().get(i);
            
            if (cedulaActual.equals(cedulaPiloto) || cedulaActual.equals(cedulaServicioCliente)) {
                linea = cedulaActual + TXTSPLIT +
                        tripulacionesClass.getNOMBRES().get(i) + TXTSPLIT +
                        tripulacionesClass.getIDS().get(i) + TXTSPLIT +
                        rolActual + TXTSPLIT +
                        nuevoEstado;
                
                System.out.println("Actualizando estado para: " + cedulaActual + " a " + nuevoEstado);
            } else {
                linea = cedulaActual + TXTSPLIT +
                        tripulacionesClass.getNOMBRES().get(i) + TXTSPLIT +
                        tripulacionesClass.getIDS().get(i) + TXTSPLIT +
                        rolActual + TXTSPLIT +
                        estadoActual;
            }
            bw.write(linea);
            bw.newLine();
        }
    } catch (Exception e) {
        System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
    }
}
}
