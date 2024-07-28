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
}
