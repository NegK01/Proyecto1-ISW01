package negocio;

import database.readingClasses.*;
import database.writtingClasses.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreacionVuelos {

    AerolineaClassR aerolineaClass = new AerolineaClassR();
    AeropuertoClassR aeropuertoClass = new AeropuertoClassR();
    TripulacionesClassR tripulacionesClass = new TripulacionesClassR();
    AvionesClassR avionesClass = new AvionesClassR();
    AvionesClassW avionesClassW = new AvionesClassW();
    VuelosClassW vuelosClassW = new VuelosClassW();
    String avionEncontrado = "";
    Integer ID_Vuelo = 100;
    Integer ID_AeropuertoSalida = 0;
    Integer ID_AeropuertoLlegada = 0;
    List<String> AvionesEncontrados;
    Date salidaDate;
    Date llegadaDate;

    /*
    Unicamente continuarlo cuando se haya terminado el diseno UI de JDCreacionVuelos,
    una vez listo, verificar si las valdiciones se hacen dentro del codigo en JDCreaciones
    o aqui, una vez decidido, agregar los datos a Vuelos.txt y continuar con lo diga el pdf
     */
 /* 
    Actualizacion 28/07
    Los datos de interfaz ya fueron validados en su mismo apartado, interfaz debe enviar los datos a
    negocio y este mismo enviarlos a una clase de escritura de base de datos para agregarlos a un txt
     */
    public CreacionVuelos(String aerolinea, String aeropuertoSalida, String aeropuertoLlegada, Integer precioVuelo,
            String fechaDeSalida, String fechaDeLlegada, Integer horaSalida, Integer horaLlegada,
            Integer minutosSalida, Integer minutosLlegada, Date fechaDateFormat1, Date fechaDateFormat2) {

        // Todos los datos fueron agregados, ahora se deberia de leer que hay en los txt de aeropuerto, 
        // buscando el que nos interesa
        aerolineaClass.leerAerolineaTxt();
        String[] lineaTxtParts;
        Integer ID_Aerolinea = 0;
        Integer indice = 0;
        String avionEncontrado = "";
        AvionesEncontrados = new ArrayList<>();
        

        for (String lineaTxt : aerolineaClass.getLINEACOMPLETA()) {
            if (lineaTxt.contains(aerolinea)) {
                System.out.println(lineaTxt);
                lineaTxtParts = lineaTxt.split(",");
                ID_Aerolinea = Integer.parseInt(lineaTxtParts[0].trim());
                break; // Cerramos una vez encontrado ya que solo hay un dato unico, no hay nada que se repita
            }
        }

        aeropuertoClass.leerAeropuertoTxt();
        for (Integer ID : aeropuertoClass.getID()) {
            if (aeropuertoClass.getNOMBRE().get(indice).equals(aeropuertoSalida)) {
                ID_AeropuertoSalida = aeropuertoClass.getID().get(indice);
            } else if (aeropuertoClass.getNOMBRE().get(indice).equals(aeropuertoLlegada)) {
                ID_AeropuertoLlegada = aeropuertoClass.getID().get(indice);
            }
        }

        avionesClass.LeerAvionesTxt();
        indice = 0; //Restablecemos
        for (Integer ID : avionesClass.getIDS_AERO()) {
            if (ID.equals(ID_Aerolinea)) {
                // Verificamos el estado del avion, 0 = disponible, 1 = en servicio
                if (avionesClass.getESTADO().get(indice) == 0) {
                    AvionesEncontrados.add(avionesClass.getMODELO().get(indice).strip());
                    Integer modificarEstado = 1;

                    avionesClassW.EscribirAvionesTxt(avionesClass.getIDS().get(indice),
                            avionesClass.getMODELO().get(indice),
                            avionesClass.getIDS_AERO().get(indice),
                            modificarEstado);
                    
                    realizarUnionDateFormat(fechaDateFormat1, fechaDateFormat2, horaSalida, minutosSalida, horaLlegada, minutosLlegada);    

                    //DateFormat es el formato original del date, cosa que pide el pdf para enviar la fecha escogida
//                    vuelosClassW.EscribirVuelosTxt(ID_Vuelo, ID_Aerolinea, precioVuelo, fechaDateFormat1,
//                                                   salidaDate, 
//                                                   ID_AeropuertoSalida, fechaDateFormat2,
//                                                   fechaDateFormat2);
                }
                // No hacemos break porque la aerolinea puede tener muchos aviones
            }
            indice++;
            // Sumamos hasta que el id con concuerde con el id de la aerolinea
            // Una vez encontrado, buscamos con el indice la linea especifica para poder
            // obtener los otros datos que se convergen, es decir, el modelo del avion 
            // y otros detalles del mismo
        }

        System.out.println(aerolinea);
        System.out.println(aeropuertoSalida);
        System.out.println(aeropuertoLlegada);
        System.out.println(precioVuelo);
        System.out.println(fechaDeSalida);
        System.out.println(fechaDeLlegada);
        System.out.println(horaSalida);
        System.out.println(horaLlegada);
        System.out.println(minutosSalida);
        System.out.println(minutosLlegada);

    }

    public void realizarUnionDateFormat(Date dateSeleccionadoSalida, Date dateSeleccionadoLlegada, Integer horaSalida, Integer minutosSalida, Integer horaLlegada, Integer minutosLlegada) {
        // Obtener la fecha seleccionada en el JDateChooser

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateStringSalida = sdf.format(dateSeleccionadoSalida);
        String dateStringLlegada = sdf.format(dateSeleccionadoLlegada);

        // Definir una hora específica
        String timeSalida = horaSalida + ":" + minutosSalida; // Puedes cambiar esta hora según sea necesario
        String timeLlegada = horaLlegada + ":" + minutosLlegada; // Puedes cambiar esta hora según sea necesario

        // Combinar la fecha y la hora en una sola cadena
        String dateTimeSalida = dateStringSalida + " " + timeSalida;
        String dateTimeLlegada = dateStringLlegada + " " + timeLlegada;

        // Crear un formato que incluya tanto la fecha como la hora
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm", new Locale("es", "ES"));

        try {
            // Parsear la cadena combinada para obtener un objeto Date
            salidaDate = dateTimeFormatter.parse(dateTimeSalida);
            llegadaDate = dateTimeFormatter.parse(dateTimeLlegada);
            System.out.println("Fecha y hora combinadas: " + salidaDate);
            System.out.println("Fecha y hora combinadas: " + llegadaDate);
        } catch (ParseException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e);
        }
    }

    public boolean creacionVueloExitoso() {
        if (AvionesEncontrados.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
