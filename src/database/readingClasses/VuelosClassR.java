package database.readingClasses;

import java.io.*;
import java.util.*;

public class VuelosClassR {
    private List<Integer> IDS_Vuelos;
    
    public VuelosClassR(){
        this.IDS_Vuelos = new ArrayList<>();
    }
    
    
    public void LeerVuelosTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/database/files/Vuelos.txt"))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(",");
                try {
                    int id = Integer.parseInt(partes[0].trim());
                    IDS_Vuelos.add(id);
                } catch (Exception e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e);
                }
            }
            
            
        } catch (Exception e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e);
        }
    }

    public List<Integer> getID() {
        return IDS_Vuelos;
        
        // agregar los demas datos
        
    }
    
    
    
    
}
