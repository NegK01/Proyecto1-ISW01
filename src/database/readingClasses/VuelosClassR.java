package database.readingClasses;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class VuelosClassR {
    private List<Integer> ID;
    private List<Integer> ID_AERO;
    private List<Integer> PRECIO;
    private List<Date> FECHA_IN;
    private List<Integer> ID_IN;
    private List<Date> FECHA_OUT;
    private List<Integer> ID_OUT;
    private List<LocalTime> DURACION;
    private List<Integer> ID_AVION;
    private List<String> ID_TRIPULACION;
    private String FILEPATH;
    private String TXT_SPLIT;
    
    public VuelosClassR() {
        this.ID = new ArrayList<>();
        this.ID_AERO = new ArrayList<>();
        this.PRECIO = new ArrayList<>();
        this.FECHA_IN = new ArrayList<>();
        this.ID_IN = new ArrayList<>();
        this.FECHA_OUT = new ArrayList<>();
        this.ID_OUT = new ArrayList<>();
        this.DURACION = new ArrayList<>();
        this.ID_AVION = new ArrayList<>();
        this.ID_TRIPULACION = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Vuelos.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void LeerVuelosTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            
            SimpleDateFormat formato_Fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(TXT_SPLIT);
                try {
                    int id = Integer.parseInt(partes[0]);
                    ID.add(id);
                    
                    int id_aero = Integer.parseInt(partes[1]);
                    ID_AERO.add(id_aero);
                    
                    int precio = Integer.parseInt(partes[2]);
                    PRECIO.add(precio);
                    
                    Date fechas_in = formato_Fecha.parse(partes[3] + " " + partes[4]);
                    FECHA_IN.add(fechas_in);
                    
                    int id_in = Integer.parseInt(partes[5]);
                    ID_IN.add(id_in);
                    
                    Date fechas_out = formato_Fecha.parse(partes[6] + " " + partes[7]);
                    FECHA_OUT.add(fechas_out);
                    
                    int id_out = Integer.parseInt(partes[8]);
                    ID_OUT.add(id_out);
                    
//                    int hora = Integer.parseInt(partes[9]);
//                    int minuto = Integer.parseInt(partes[10]);
//                    LocalTime duracion = LocalTime.of(hora, minuto);
//                    DURACION.add(duracion);
                    
                    int id_avion = Integer.parseInt(partes[11]);
                    ID_AVION.add(id_avion);
                    
                    ID_TRIPULACION.add(partes[12]);
                    
                } catch (ParseException e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e);
                }
            }
            
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }

    public List<Integer> getID() {
        return ID;
    }

    public List<Integer> getID_AERO() {
        return ID_AERO;
    }

    public List<Integer> getPRECIO() {
        return PRECIO;
    }

    public List<Date> getFECHA_IN() {
        return FECHA_IN;
    }

    public List<Integer> getID_IN() {
        return ID_IN;
    }

    public List<Date> getFECHA_OUT() {
        return FECHA_OUT;
    }

    public List<Integer> getID_OUT() {
        return ID_OUT;
    }

    public List<LocalTime> getDURACION() {
        return DURACION;
    }

    public List<Integer> getID_AVION() {
        return ID_AVION;
    }

    public List<String> getID_TRIPULACION() {
        return ID_TRIPULACION;
    }
}
