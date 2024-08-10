package database.readingClasses;

import java.util.*;
import java.io.*;

public class AerolineaClassR {
    private List<Integer> IDS;
    private List<String> NOMBRES;
    private List<String> LINEACOMPLETA;
    private String FILEPATH;
    private String TXT_SPLIT;

    public AerolineaClassR() {
        this.IDS = new ArrayList<>();
        this.NOMBRES = new ArrayList<>();
        this.LINEACOMPLETA = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Aerolineas.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void leerAerolineaTxt(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(TXT_SPLIT);
                try {
                    int id = Integer.parseInt(partes[0].trim()); // Sacamos la parte 0 que es ID
                    IDS.add(id); // Agregamos el ID al cumolo de IDS
                    NOMBRES.add(partes[1].trim());
                    LINEACOMPLETA.add(linea.trim());
                    
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e);
                }
            }
            
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }

    public List<Integer> getID() {
        return IDS;
    }

    public List<String> getNOMBRE() {
        return NOMBRES; 
    }

    public List<String> getLINEACOMPLETA() {
        return LINEACOMPLETA;
    }
}
