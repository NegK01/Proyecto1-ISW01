package negocio;

import database.readingClasses.HistorialClassR;
import database.readingClasses.AeropuertoClassR;
import database.readingClasses.UsuariosClassR;
import java.text.ParseException;
import java.util.ArrayList;

public class Reporte_3 {

    public ArrayList BuscarPorUsuario(Integer cedulaUsuario) throws ParseException {

        HistorialClassR historial = new HistorialClassR();
        historial.leerHistorialTxt();
        UsuariosClassR usuarios = new UsuariosClassR();
        usuarios.LeerUsuariosTxt();
        AeropuertoClassR aero = new AeropuertoClassR();
        aero.leerAeropuertoTxt();

        ArrayList Compras = new ArrayList();

        for (int i = 0; i < historial.getCEDULA().size(); i++) {

            ArrayList Compra = new ArrayList();

            if (historial.getCEDULA().get(i).equals(cedulaUsuario)) {

                Compra.add(historial.getCEDULA().get(i));

                Integer cedula = historial.getCEDULA().get(i);
                for (int j = 0; j < usuarios.getCEDULA().size(); j++) {

                    if (cedula.equals(usuarios.getCEDULA().get(j))) {
                        String nombre = usuarios.getNOMBRES().get(j);
                        Compra.add(nombre);
                    }
                }

                Integer id_1 = historial.getID_SALIDA().get(i);
                Integer id_2 = historial.getID_LLEGADA().get(i);
                Integer id_3 = historial.getID_ESCALA().get(i);

                String salida = "";
                String llegada = "";
                String escala = "Sin escala";

                for (int x = 0; x < aero.getID().size(); x++) {
                    if (aero.getID().get(x).equals(id_1)) {
                        String aero_1 = aero.getNOMBRE().get(x);

                        salida = aero_1.replace("Aeropuerto Internacional", "");
                        salida = salida.replace("de", "");

                    } else if (aero.getID().get(x).equals(id_2)) {
                        String aero_2 = aero.getNOMBRE().get(x);

                        llegada = aero_2.replace("Aeropuerto Internacional", "");
                        llegada = llegada.replace("de", "");

                    } else if (aero.getID().get(x).equals(id_3)) {
                        String aero_3 = aero.getNOMBRE().get(x);

                        escala = aero_3.replace("Aeropuerto Internacional", "");
                        escala = escala.replace("de", "");
                    }
                }

                Compra.add(salida);
                Compra.add(escala);
                Compra.add(llegada);
                
                Compra.add(historial.getCAN_BOLETOS().get(i));
                
                String asientosTotales = historial.getASIENTOS().get(i);
                asientosTotales = asientosTotales.replace("-", " ");
                
                if (escala.equals("Sin escala")) {
                    
                    Compra.add(asientosTotales);
                    
                } else {
                    asientosTotales = asientosTotales.replace("T", " | ");
                    Compra.add(asientosTotales);
                }
                
                String horas = historial.getDURACIONHORAS().get(i).toString();
                String minutos = historial.getDURACIONMINUTOS().get(i).toString();
                
                Compra.add(horas + ":" + minutos);
                
                Compra.add(historial.getCOSTO().get(i));
                
                Compras.add(Compra);
            }
        }
        
        return Compras;
        
    }
}
