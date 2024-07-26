package negocio;

import java.util.*;
import java.io.*;

public class AerolineaClass {
    private List<Integer> IDS;
    private List<String> NOMBRES;
    private String FILEPATH;
    private String TXT_SPLIT;

    public AerolineaClass() {
        this.IDS = new ArrayList<>();
        this.NOMBRES = new ArrayList<>();
        this.FILEPATH = "src/dataBase/Aerolineas.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void leerAerolineaTxt(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(",");
                try {
                    int id = Integer.parseInt(partes[0].trim()); // Sacamos la parte 0 que es ID
                    IDS.add(id); // Agregamos el ID al cumolo de IDS
                    NOMBRES.add(partes[1].trim());
                    
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
}
