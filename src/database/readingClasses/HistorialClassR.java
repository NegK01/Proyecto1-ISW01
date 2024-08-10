package database.readingClasses;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HistorialClassR {

    private List<Integer> ID;
    private List<Integer> CEDULA;
    private List<Integer> ID_SALIDA;
    private List<Integer> ID_LLEGADA;
    private List<Integer> ID_ESCALA;
    private List<Date> FECHA;
    private List<String> FECHAFORMATEADA;
    private List<Integer> CAN_BOLETOS;
    private List<String> ASIENTOS;
    private List<Integer> DURACIONHORAS;
    private List<Integer> DURACIONMINUTOS;
    private List<Integer> COSTO;
    private String FILEPATH;
    private String TXT_SPLIT;

    public HistorialClassR() {
        this.ID = new ArrayList<>();
        this.CEDULA = new ArrayList<>();
        this.ID_SALIDA = new ArrayList<>();
        this.ID_LLEGADA = new ArrayList<>();
        this.ID_ESCALA = new ArrayList<>();
        this.FECHA = new ArrayList<>();
        this.FECHAFORMATEADA = new ArrayList<>();
        this.CAN_BOLETOS = new ArrayList<>();
        this.ASIENTOS = new ArrayList<>();
        this.DURACIONHORAS = new ArrayList<>();
        this.DURACIONMINUTOS = new ArrayList<>();
        this.COSTO = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Historial.txt";
        this.TXT_SPLIT = ";";
    }

    public void leerHistorialTxt() throws ParseException {
        Date fecha = null;
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat nuevoFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(TXT_SPLIT);
                try {
                    ID.add(Integer.parseInt(partes[0].trim()));
                    CEDULA.add(Integer.parseInt(partes[1].trim()));
                    ID_SALIDA.add(Integer.parseInt(partes[2].trim()));
                    ID_LLEGADA.add(Integer.parseInt(partes[3].trim()));
                    ID_ESCALA.add(Integer.parseInt(partes[4].trim()));

                    String fechaStr = partes[5].trim();
                    fecha = formatoOriginal.parse(fechaStr);
                    FECHA.add(fecha);

                    CAN_BOLETOS.add(Integer.parseInt(partes[6].trim()));
                    ASIENTOS.add(partes[7].trim());
                    DURACIONHORAS.add(Integer.parseInt(partes[8].trim()));
                    DURACIONMINUTOS.add(Integer.parseInt(partes[9].trim()));
                    COSTO.add(Integer.parseInt(partes[10].trim()));

                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }

        if (fecha != null) {
            String fechaFormateada = nuevoFormato.format(fecha);
            FECHAFORMATEADA.add(fechaFormateada);
        }
    }

    public List<Integer> getID() {
        return ID;
    }

    public List<Integer> getCEDULA() {
        return CEDULA;
    }

    public List<Integer> getID_SALIDA() {
        return ID_SALIDA;
    }

    public List<Integer> getID_LLEGADA() {
        return ID_LLEGADA;
    }

    public List<Integer> getID_ESCALA() {
        return ID_ESCALA;
    }

    public List<Date> getFECHA() {
        return FECHA;
    }

    public List<String> getFECHAFORMATEADA() {
        return FECHAFORMATEADA;
    }

    public List<Integer> getCAN_BOLETOS() {
        return CAN_BOLETOS;
    }

    public List<String> getASIENTOS() {
        return ASIENTOS;
    }

    public List<Integer> getDURACIONHORAS() {
        return DURACIONHORAS;
    }

    public List<Integer> getDURACIONMINUTOS() {
        return DURACIONMINUTOS;
    }

    public List<Integer> getCOSTO() {
        return COSTO;
    }

    public String getFILEPATH() {
        return FILEPATH;
    }

    public String getTXT_SPLIT() {
        return TXT_SPLIT;
    }
}
