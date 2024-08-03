package database.writtingClasses;

import java.io.*;
import java.util.*;


public class VuelosClassW {
 
    
    
    
    public VuelosClassW(){
        
    }
    
    
    // Cambiar ID_Vuelo a Integer
    public void EscribirVuelosTxt(Integer ID_Vuelo, Integer ID_Aerolinea, Integer precioVuelo,
            Date dateFormat1, Date dateFormat2) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/database/file/Vuelos.txt", true))){
            br.write(ID_Vuelo);
            br.newLine();
        } catch (Exception e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e);
        }
    }
}
