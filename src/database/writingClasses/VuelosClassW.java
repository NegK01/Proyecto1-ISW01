package database.writingClasses;

import java.io.*;
import java.util.*;


public class VuelosClassW {
    private String linea;
    private String TXTSPLIT;
    
    
    public VuelosClassW(){
        TXTSPLIT = ",";
    }
    
    
    public void EscribirVuelosTxt(Integer ID_Vuelo, Integer ID_Aerolinea, Integer precioVuelo,
            String fechaDeSalida, String horaSalida, Integer ID_AeropuertoSalida,
            String fechaDeLlegada, String horaLlegada, Integer ID_AeropuertoLlegada,
            long horasDiff, long minutosDiff, Integer ID_Avion, String ID_Tripulacion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/files/Vuelos.txt", true))){
            
            linea = ID_Vuelo + TXTSPLIT + ID_Aerolinea + TXTSPLIT + precioVuelo + TXTSPLIT +
                    fechaDeSalida + TXTSPLIT + horaSalida + TXTSPLIT + ID_AeropuertoSalida + TXTSPLIT + 
                    fechaDeLlegada + TXTSPLIT + horaLlegada + TXTSPLIT + ID_AeropuertoLlegada + TXTSPLIT +
                    horasDiff + TXTSPLIT + minutosDiff + TXTSPLIT + ID_Avion + TXTSPLIT + ID_Tripulacion;
            
            bw.write(linea);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
}
    