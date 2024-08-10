package negocio;

import database.readingClasses.AeropuertoClassR;
import database.readingClasses.VuelosClassR;
import database.readingClasses.AerolineaClassR;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Busqueda_Vuelos {

    //Variables que solo se usa en mostrar_vuelos
    private Date fecha2;

    //Variables que se llenar en mostrar_vuelos y se usan en datos_vuelos
    private Integer aerolinea;
    private Integer aeropuerto_Escala;

    private String nombre_Aerolinea;
    private String nombre_Escala = "Sin escala";

    //Variables que se usan en varias funciones
    private String salida;
    private String llegada;

    //Variables Encontrar_id_in_out
    private Integer id_salida;
    private Integer id_llegada;

    //Variables finales para rellenar la lista en datos_Vuelos
    private Integer id_vuelo = 0;
    private Integer id_escala1 = 0;
    private Integer id_escala2 = 0;

    //Lista con todos los posibles datos de los vuelos
    private ArrayList Vuelos_Totales = new ArrayList();

    public void Encontrar_id_in_out(String aeropuerto1, String aeropuerto2) {
        AeropuertoClassR aeropuertoClassR = new AeropuertoClassR();
        aeropuertoClassR.leerAeropuertoTxt();

        salida = aeropuerto1;
        llegada = aeropuerto2;

        //Conseguir los id del los aeropuertos elegidos
        for (int i = 0; i < aeropuertoClassR.getNOMBRE().size(); i++) {
            if (aeropuertoClassR.getNOMBRE().get(i).equals(salida)) {
                id_salida = aeropuertoClassR.getID().get(i);
            }

            if (aeropuertoClassR.getNOMBRE().get(i).equals(llegada)) {
                id_llegada = aeropuertoClassR.getID().get(i);
            }
        }
    }

    public ArrayList Mostrar_Vuelos(String aeropuerto1, String aeropuerto2, Date fecha1) {
        //Leectura de todos los txt
        AeropuertoClassR aeropuertoClassR = new AeropuertoClassR();
        aeropuertoClassR.leerAeropuertoTxt();

        AerolineaClassR aerolineaClassR = new AerolineaClassR();
        aerolineaClassR.leerAerolineaTxt();

        VuelosClassR vuelosClassR = new VuelosClassR();
        vuelosClassR.LeerVuelosTxt();

        //Primera funcion que necesito llamar
        Encontrar_id_in_out(aeropuerto1, aeropuerto2);

        //Se busca de vuelo en vuelo para conseguir uno directo o una escala
        for (int i = 0; i < vuelosClassR.getID().size(); i++) {

            //Limpiar variables por si acaso
            nombre_Escala = "Sin escala";
            id_vuelo = 0;
            id_escala1 = 0;
            id_escala2 = 0;

            //Formato para solo tenr la feca sin horas
            SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");

            //Cambio el formato de la feca del vuelo para que no tenga en cuenta las horas
            String date = Formato.format(vuelosClassR.getFECHA_IN().get(i));
            try {
                fecha2 = Formato.parse(date);
            } catch (ParseException e) {
                System.out.println("\u001B[31mERROR:\u001B[0m " + e);
            }

            //Si tienen la mismas fechas el vuelo y el filtro selecccionado
            if (fecha2.equals(fecha1)) {

                //Si el id de la salida es igaul al seleccionado
                if (vuelosClassR.getID_IN().get(i).equals(id_salida)) {

                    //Para sacar el nombre de la aerolinea
                    aerolinea = vuelosClassR.getID_AERO().get(i);
                    for (int y = 0; y < aerolineaClassR.getID().size(); y++) {
                        if (aerolineaClassR.getID().get(y).equals(aerolinea)) {
                            nombre_Aerolinea = aerolineaClassR.getNOMBRE().get(y);
                        }
                    }

                    //Si el id de la llegada es igual al seleccionado, se encontro un vuelo directo
                    if (vuelosClassR.getID_OUT().get(i).equals(id_llegada)) {
                        id_vuelo = vuelosClassR.getID().get(i);

                    } else { //Si no se encuentra uno directo se pasa a buscar una posible escala

                        for (int j = 0; j < vuelosClassR.getID().size(); j++) {
                            if (vuelosClassR.getID_IN().get(j).equals(vuelosClassR.getID_OUT().get(i))) {

                                //Para despues sacar el nombre del aeropuerto de la escala
                                aeropuerto_Escala = vuelosClassR.getID_OUT().get(i);

                                for (int x = 0; x < aeropuertoClassR.getID().size(); x++) {
                                    if (aeropuertoClassR.getID().get(x).equals(aeropuerto_Escala)) {
                                        nombre_Escala = aeropuertoClassR.getNOMBRE().get(x);
                                    }
                                }

                                if (vuelosClassR.getFECHA_OUT().get(i).before(vuelosClassR.getFECHA_IN().get(j))) {

                                    //Sacamos solamente las oras de las dos fechas pra comparar
                                    int hora1 = vuelosClassR.getFECHA_OUT().get(i).getHours();
                                    int hora2 = vuelosClassR.getFECHA_IN().get(j).getHours();

                                    //Si las horas de las escalas coinciden
                                    if (hora1 < hora2) {
                                        if (vuelosClassR.getID_OUT().get(j).equals(id_llegada)) {
                                            id_escala1 = vuelosClassR.getID().get(i);
                                            id_escala2 = vuelosClassR.getID().get(j);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                datos_Vuelos();

            }
        }

        return Vuelos_Totales;
    }

    public void datos_Vuelos() {
        VuelosClassR vuelosClassR = new VuelosClassR();
        vuelosClassR.LeerVuelosTxt();

        ArrayList Vuelo = new ArrayList();

        if (id_vuelo != 0) { //Vuelo directo
            for (int x = 0; x < vuelosClassR.getID().size(); x++) {

                if (vuelosClassR.getID().get(x).equals(id_vuelo)) {

                    Vuelo.add(nombre_Aerolinea);

                    String salida_modi = salida.replace("Aeropuerto Internacional", "");
                    salida_modi = salida_modi.replace("de", "");
                    Vuelo.add(salida_modi.strip());

                    String hora_Salida = vuelosClassR.getFECHA_IN().get(x).getHours() + ":"
                            + vuelosClassR.getFECHA_IN().get(x).getMinutes();

                    Vuelo.add(hora_Salida);

                    String llegada_modi = llegada.replace("Aeropuerto Internacional", "");
                    llegada_modi = llegada_modi.replace("de", "");
                    Vuelo.add(llegada_modi.strip());

                    String hora_llegada = vuelosClassR.getFECHA_OUT().get(x).getHours() + ":"
                            + vuelosClassR.getFECHA_OUT().get(x).getMinutes();

                    Vuelo.add(hora_llegada);

                    Vuelo.add(nombre_Escala);

                    Vuelo.add(vuelosClassR.getPRECIO().get(x));

                    Vuelo.add(vuelosClassR.getHORAS().get(x) + ":" + vuelosClassR.getMINUTOS().get(x));

                    Vuelos_Totales.add(Vuelo);
                }
            }

        } else if (id_escala1 != 0 && id_escala2 != 0) { //Escala

            int precio1 = 0;
            int precio2 = 0;
            int precio_Total = 0;

            int hora1 = 0;
            int hora2 = 0;

            int minuto1 = 0;
            int minuto2 = 0;

            int horas_Totales = 0;
            int minutos_Totales = 0;

            for (int x = 0; x < vuelosClassR.getID().size(); x++) {

                if (vuelosClassR.getID().get(x).equals(id_escala1)) {
                    
                    Vuelo.add(nombre_Aerolinea);

                    String salida_modi = salida.replace("Aeropuerto Internacional", "");
                    salida_modi = salida_modi.replace("de", "");
                    Vuelo.add(salida_modi.strip());

                    String hora_Salida = vuelosClassR.getFECHA_IN().get(x).getHours() + ":"
                            + vuelosClassR.getFECHA_IN().get(x).getMinutes();

                    Vuelo.add(hora_Salida);

                    precio1 = vuelosClassR.getPRECIO().get(x);

                    hora1 = vuelosClassR.getHORAS().get(x);
                    minuto1 = vuelosClassR.getMINUTOS().get(x);
                }

                if (vuelosClassR.getID().get(x).equals(id_escala2)) {

                    String llegada_modi = llegada.replace("Aeropuerto Internacional", "");
                    llegada_modi = llegada_modi.replace("de", "");
                    Vuelo.add(llegada_modi.strip());

                    String hora_llegada = vuelosClassR.getFECHA_OUT().get(x).getHours() + ":"
                            + vuelosClassR.getFECHA_OUT().get(x).getMinutes();

                    Vuelo.add(hora_llegada);

                    precio2 = vuelosClassR.getPRECIO().get(x);

                    hora2 = vuelosClassR.getHORAS().get(x);
                    minuto2 = vuelosClassR.getMINUTOS().get(x);
                }

            }
            
            String escala_modi = nombre_Escala.replace("Aeropuerto Internacional", "");
            escala_modi = escala_modi.replace("de", "");
            Vuelo.add(escala_modi.strip());

            precio_Total = precio1 + precio2;
            Vuelo.add(precio_Total);

            horas_Totales = hora1 + hora2;
            minutos_Totales = minuto1 + minuto2;

            Vuelo.add(horas_Totales + ":" + minutos_Totales);

            Vuelos_Totales.add(Vuelo);
        }
    }
}
