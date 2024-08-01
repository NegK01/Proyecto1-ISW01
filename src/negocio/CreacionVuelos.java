package negocio;

import database.classes.AeropuertoClass;
import database.classes.AerolineaClass;
import database.classes.TripulacionesClass;
import database.classes.AvionesClass;


public class CreacionVuelos {
    AerolineaClass aerolineaClass = new AerolineaClass();
    AeropuertoClass aeropuertoClass = new AeropuertoClass();
    TripulacionesClass tripulacionesClass = new TripulacionesClass();
    AvionesClass avionesClass = new AvionesClass();
    
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
    
    public CreacionVuelos(String aerolinea, String aeropuertoSalida, String aeropuertoLlegada , Integer precioVuelo, 
            String fechaDeSalida, String fechaDeLlegada, Integer horaSalida, Integer horaLLegada,
            Integer minutosSalida, Integer minutosLLegada) {
        
        // Todos los datos fueron agregados, ahora se deberia de leer que hay en los txt de aeropuerto, 
        // buscando el que nos interesa
        aerolineaClass.leerAerolineaTxt();
        System.out.println(aerolinea);
        
        for (String lineaTxt : aerolineaClass.getLINEACOMPLETA()) {
            if (lineaTxt.contains(aerolinea)) {
                System.out.println("tru");
                System.out.println(lineaTxt);
            }
        }
        
        
        System.out.println(aerolinea);
        System.out.println(aeropuertoSalida);
        System.out.println(aeropuertoLlegada);
        System.out.println(precioVuelo);
        System.out.println(fechaDeSalida);
        System.out.println(fechaDeLlegada);
        System.out.println(horaSalida);
        System.out.println(horaLLegada);
        System.out.println(minutosSalida);
        System.out.println(minutosLLegada);
        
        
    }
    
    
    
    
}
