
package database.readingClasses;

import java.util.*;
import java.io.*;

public class AvionesClassR {
    private List<Integer> IDS;
    private List<Integer> IDS_AERO;
    private List<Integer> ESTADO;
    private List<String> MODELO;
    private String FILEPATH;
    private String TXT_SPLIT;

    public AvionesClassR() {
        this.IDS = new ArrayList<>();
        this.IDS_AERO = new ArrayList<>();
        this.ESTADO = new ArrayList<>();
        this.MODELO = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Aviones.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void LeerAvionesTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(TXT_SPLIT);
                try {
                    int id = Integer.parseInt(partes[0].trim());
                    IDS.add(id); 
                    MODELO.add(partes[1].trim());
                    
                    int id_Aero = Integer.parseInt(partes[2].trim());
                    IDS_AERO.add(id_Aero);
                    
                    int estado = Integer.parseInt(partes[3].trim());
                    ESTADO.add(estado);
                    
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e);
                }
            }
            
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
    
    public List<Integer> getIDS() {
        return IDS;
    }

    public List<Integer> getIDS_AERO() {
        return IDS_AERO;
    }

    public List<Integer> getESTADO() {
        return ESTADO;
    }

    public List<String> getMODELO() {
        return MODELO;
    }
}
