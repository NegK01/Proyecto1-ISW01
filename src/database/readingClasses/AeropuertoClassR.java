package database.readingClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoClassR {
    private List<Integer> IDS;
    private List<String> NOMBRES;
    private List<String> PAISES;
    private String FILEPATH;
    private String TXT_SPLIT;
    
    public AeropuertoClassR() {
        this.IDS = new ArrayList<>();
        this.NOMBRES = new ArrayList<>();
        this.PAISES = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Aeropuertos.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void leerAeropuertoTxt(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(",");
                try {
                    int id = Integer.parseInt(partes[0].trim()); // Sacamos la parte 0 que es ID
                    IDS.add(id); // Agregamos el ID al cumolo de IDS
                    NOMBRES.add(partes[1].trim());
                    PAISES.add(partes[2].trim());
                    
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

    public List<String> getPAISES() {
        return PAISES;
    }
}
