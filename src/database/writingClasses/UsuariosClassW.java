
package database.writingClasses;

import database.readingClasses.UsuariosClassR;
import java.util.ArrayList;
import java.io.*;

public class UsuariosClassW {
    private UsuariosClassR usuariosClassR;
    private String TXTSPLIT;
    private String linea;
    
    public UsuariosClassW() {
        usuariosClassR = new UsuariosClassR();
        usuariosClassR.LeerUsuariosTxt();
        TXTSPLIT = ",";
    }
    
    public void GuardarUsuariosTxt(ArrayList datosUsuario) {
        
        try {
            File archivo = new File("src/dataBase/files/Usuarios.txt");
            
            BufferedWriter Lector = new BufferedWriter(new FileWriter(archivo, false));
            
            //Escribimos los datos usuario por usuario
            for (int i = 0; i < usuariosClassR.getCEDULA().size(); i++) {
                
                //Ordenamos los datos para almanecenar con todo y salto de linea
                linea = (usuariosClassR.getCEDULA().get(i) + TXTSPLIT + usuariosClassR.getNOMBRES().get(i) 
                        + TXTSPLIT + usuariosClassR.getEDAD().get(i) + TXTSPLIT + usuariosClassR.getCONTRASEÑA().get(i) 
                        + TXTSPLIT + usuariosClassR.getCORREO().get(i) + TXTSPLIT +  usuariosClassR.getTIPO().get(i));
                
                //Escribimos en el txt la linea preparada
                Lector.write(linea);
                Lector.newLine();
            }
            
            linea = (datosUsuario.get(0) + TXTSPLIT + datosUsuario.get(1) + TXTSPLIT + 
                    datosUsuario.get(4) + TXTSPLIT + datosUsuario.get(2) + TXTSPLIT + 
                    datosUsuario.get(3)+ TXTSPLIT + "0");
            
            
            Lector.write(linea);
            //Cerramos el lector 
            Lector.close();
            
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
}