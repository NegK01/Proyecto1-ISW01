package negocio;

import database.readingClasses.HistorialClassR;
import database.readingClasses.UsuariosClassR;
import database.readingClasses.AeropuertoClassR;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reporte_1 {


    public ArrayList BuscarPorFechas(Date fechaInicial, Date fechaFinal, String fecha1, String fecha2) throws ParseException {

        HistorialClassR historial = new HistorialClassR();
        historial.leerHistorialTxt();
        UsuariosClassR usuarios = new UsuariosClassR();
        usuarios.LeerUsuariosTxt();
        AeropuertoClassR aero = new AeropuertoClassR();
        aero.leerAeropuertoTxt();
        
        ArrayList Compras = new ArrayList();
        
        SimpleDateFormat nuevoFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for (int i = 0; i < historial.getCEDULA().size(); i++) {

            ArrayList Compra = new ArrayList();

            if (historial.getFECHA().get(i).after(fechaInicial)) {
                if (historial.getFECHA().get(i).before(fechaFinal)) {

                    Integer cedula = historial.getCEDULA().get(i);
                    Compra.add(cedula);

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
                    
                    
                    Date fechahistorial = historial.getFECHA().get(i);
                    String fechaFormat = nuevoFormato.format(fechahistorial);
                    
                    Compra.add(fechaFormat);
                    
                    Compras.add(Compra);
                }
            }
        }
        
        return Compras;
        
    }
}
