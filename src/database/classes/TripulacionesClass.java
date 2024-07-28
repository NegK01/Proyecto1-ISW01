package database.classes;

import java.util.*;
import java.io.*;

public class TripulacionesClass {

    private List<Integer> IDS;
    private List<Integer> CEDULA;
    private List<Integer> ESTADO;
    private List<String> ROL;
    private List<String> NOMBRES;
    private String FILEPATH;
    private String TXT_SPLIT;

    public TripulacionesClass() {
        this.IDS = new ArrayList<>();
        this.CEDULA = new ArrayList<>();
        this.ESTADO = new ArrayList<>();
        this.ROL = new ArrayList<>();
        this.NOMBRES = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Tripulaciones.txt";
        this.TXT_SPLIT = ",";
    }

    public void LeerTripulacionesTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(TXT_SPLIT);
                try {
                    int cedula = Integer.parseInt(partes[0].trim());
                    CEDULA.add(cedula);
                    NOMBRES.add(partes[1].trim());

                    int id = Integer.parseInt(partes[2].trim());
                    IDS.add(id);
                    ROL.add(partes[3].trim());

                    int estado = Integer.parseInt(partes[4].trim());
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

    public List<Integer> getCEDULA() {
        return CEDULA;
    }

    public List<Integer> getESTADO() {
        return ESTADO;
    }

    public List<String> getNOMBRES() {
        return NOMBRES;
    }

    public List<String> getROL() {
        return ROL;
    }
}
