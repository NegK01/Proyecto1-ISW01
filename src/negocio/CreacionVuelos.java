package negocio;

import database.writingClasses.VuelosClassW;
import database.writingClasses.AvionesClassW;
import database.readingClasses.*;
import database.writingClasses.TripulacionesClassW;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class CreacionVuelos {

    AerolineaClassR aerolineaClass = new AerolineaClassR();
    AeropuertoClassR aeropuertoClass = new AeropuertoClassR();
    TripulacionesClassR tripulacionesClass = new TripulacionesClassR();
    AvionesClassR avionesClass = new AvionesClassR();
    VuelosClassR vuelosClass = new VuelosClassR();
    AvionesClassW avionesClassW = new AvionesClassW();
    VuelosClassW vuelosClassW = new VuelosClassW();
    TripulacionesClassW tripulacionesClassW = new TripulacionesClassW();
    String avionEncontrado = "";
    Integer ID_Vuelo = 100;
    Integer ID_AeropuertoSalida = 0;
    Integer ID_AeropuertoLlegada = 0;
    List<String> AvionesEncontrados;
    Date salidaDate = new Date();
    Date llegadaDate = new Date();
    long horasDiff = 0;
    long minutosDiff = 0;
    String timeSalida;
    String timeLlegada;

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
        Integer ID_Avion = 0;
        String ID_Tripulacion = "";
        String piloto = null;
        String servicioCliente = null;
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
        for (int i = 0; i < aeropuertoClass.getID().size(); i++) {
            if (aeropuertoClass.getNOMBRE().get(i).equals(aeropuertoSalida)) {
                ID_AeropuertoSalida = aeropuertoClass.getID().get(i);
            }
            if (aeropuertoClass.getNOMBRE().get(i).equals(aeropuertoLlegada)) {
                ID_AeropuertoLlegada = aeropuertoClass.getID().get(i);
            }
        }

        tripulacionesClass.LeerTripulacionesTxt();
        indice = 0;
        for (Integer ID : tripulacionesClass.getIDS()) {
            if (ID.equals(ID_Aerolinea)) {
                System.out.println("Revisando ID de aerolínea: " + ID);
                System.out.println("Estado actual: " + tripulacionesClass.getESTADO().get(indice));
                System.out.println("Rol actual: " + tripulacionesClass.getROL().get(indice));

                if (tripulacionesClass.getESTADO().get(indice) == 0) {
                    if (tripulacionesClass.getROL().get(indice).equals("Piloto") && piloto == null) {
                        piloto = String.valueOf(tripulacionesClass.getCEDULA().get(indice));
                        System.out.println("Piloto seleccionado: " + piloto);
                    }
                    if (tripulacionesClass.getROL().get(indice).equals("Servicio al Cliente") && servicioCliente == null) {
                        servicioCliente = String.valueOf(tripulacionesClass.getCEDULA().get(indice));
                        System.out.println("Servicio al Cliente seleccionado: " + servicioCliente);
                    }
                }
            }
            indice++;
        }

//        Integer nuevoEstado = 1;
//        tripulacionesClassW.EscribirTripulacionesTxt(piloto, 1);
//        tripulacionesClassW.EscribirTripulacionesTxt(servicioCliente, 1);
        if (piloto != null && servicioCliente != null) {
            System.out.println("Actualizando estado...");
            tripulacionesClassW.EscribirTripulacionesTxt(piloto, servicioCliente, 1);
            ID_Tripulacion = piloto + "." + servicioCliente;
        } else {
            System.out.println("No se encontró nadie disponible.");
        }

        avionesClass.LeerAvionesTxt();
        indice = 0; //Restablecemos
        for (Integer ID : avionesClass.getIDS_AERO()) {
            if (ID.equals(ID_Aerolinea)) {
                // Ya se encontro la linea que vamos a modificar
                // Guardamos ID del Avion
                ID_Avion = avionesClass.getIDS().get(indice);

                // Verificamos el estado del avion, 0 = disponible, 1 = en servicio
                if (avionesClass.getESTADO().get(indice) == 0) {
                    AvionesEncontrados.add(avionesClass.getMODELO().get(indice).strip());
                    Integer modificarEstado = 1;

                    avionesClassW.EscribirAvionesTxt(avionesClass.getIDS().get(indice),
                            avionesClass.getMODELO().get(indice),
                            avionesClass.getIDS_AERO().get(indice),
                            modificarEstado);

                }
                // No hacemos break porque la aerolinea puede tener muchos aviones
            }
            indice++;
            // Sumamos hasta que el id con concuerde con el id de la aerolinea
            // Una vez encontrado, buscamos con el indice la linea especifica para poder
            // obtener los otros datos que se convergen, es decir, el modelo del avion 
            // y otros detalles del mismo
        }
        realizarUnionDateFormat(fechaDateFormat1, fechaDateFormat2, horaSalida, minutosSalida, horaLlegada, minutosLlegada);
        duracion(salidaDate, llegadaDate);
        generarIDVuelo();


        if (!AvionesEncontrados.isEmpty()) {
            vuelosClassW.EscribirVuelosTxt(ID_Vuelo, ID_Aerolinea, precioVuelo, fechaDeSalida,
                    timeSalida, ID_AeropuertoSalida, fechaDeLlegada, timeLlegada,
                    ID_AeropuertoLlegada, horasDiff, minutosDiff, ID_Avion, ID_Tripulacion);
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
        timeSalida = horaSalida + ":" + minutosSalida; // Puedes cambiar esta hora según sea necesario
        timeLlegada = horaLlegada + ":" + minutosLlegada; // Puedes cambiar esta hora según sea necesario

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

    public void generarIDVuelo() {
        vuelosClass.LeerVuelosTxt();

        ID_Vuelo = ID_Vuelo + 1;

        try {
        // Verifica si la lista no está vacía antes de intentar acceder al último elemento
        if (!vuelosClass.getID().isEmpty()) {
            // Obtiene el último ID y suma 1
            ID_Vuelo = vuelosClass.getID().get(vuelosClass.getID().size() - 1) + 1;
        }
    } catch (Exception e) {
        System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
    }

    }

    public void duracion(Date date1, Date date2) {
        // Asegúrate de que date1 y date2 no sean nulas
        if (date1 == null || date2 == null) {
            JOptionPane.showMessageDialog(null, "Las fechas no pueden ser nulas.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir Date a Calendar
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // Calcular la diferencia en milisegundos
        long diferenciaMilisegundos = Math.abs(calendar2.getTimeInMillis() - calendar1.getTimeInMillis());
        
        // Convertir la diferencia a minutos y horas
        long diferenciaMinutos = diferenciaMilisegundos / (1000 * 60);
        horasDiff = diferenciaMinutos / 60;
        minutosDiff = diferenciaMinutos % 60;
        
        // Imprimir la diferencia
        System.out.println("Diferencia: " + horasDiff + " horas y " + minutosDiff + " minutos.");
    }
}

