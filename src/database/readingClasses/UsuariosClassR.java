
package database.readingClasses;

import java.util.*;
import java.io.*;

public class UsuariosClassR {
    private List<Integer> EDAD;
    private List<Integer> CEDULA;
    private List<String> TIPO;
    private List<String> CORREO;
    private List<String> NOMBRES;
    private List<String> CONTRASEÑA;
    private String FILEPATH;
    private String TXT_SPLIT;
    
    public UsuariosClassR() {
        this.EDAD = new ArrayList<>();
        this.CEDULA = new ArrayList<>();
        this.TIPO = new ArrayList<>();
        this.CORREO = new ArrayList<>();
        this.NOMBRES = new ArrayList<>();
        this.CONTRASEÑA = new ArrayList<>();
        this.FILEPATH = "src/dataBase/files/Usuarios.txt";
        this.TXT_SPLIT = ",";
    }
    
    public void LeerUsuariosTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(TXT_SPLIT);
                try {
                    int cedula = Integer.parseInt(partes[0].trim());
                    CEDULA.add(cedula);
                    
                    NOMBRES.add(partes[1].trim());
                    
                    int edad = Integer.parseInt(partes[2].trim());
                    EDAD.add(edad); 
                    
                    CONTRASEÑA.add(partes[3].trim());
                    
                    CORREO.add(partes[4].trim());
                    
                    TIPO.add(partes[5].trim());
                    
                    
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mERROR:\u001B[0m " + e);
                }
            }
            
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
    
    public List<Integer> getEDAD() {
        return EDAD;
    }

    public List<Integer> getCEDULA() {
        return CEDULA;
    }

    public List<String> getTIPO() {
        return TIPO;
    }

    public List<String> getCORREO() {
        return CORREO;
    }

    public List<String> getNOMBRES() {
        return NOMBRES;
    }

    public List<String> getCONTRASEÑA() {
        return CONTRASEÑA;
    }
}
