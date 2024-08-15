package negocio;

import database.readingClasses.UsuariosClassR;
import database.writingClasses.UsuariosClassW;
import java.util.ArrayList;

public class Inicio_Registro {

    //Para poder utilizar la clase usuarios
    UsuariosClassR usuariosR = new UsuariosClassR();
    UsuariosClassW usuariosW = new UsuariosClassW();

    public String DarAcceso(int cedulaDada, String contraseñaDada) {
        //Leemos el txt
        usuariosR.LeerUsuariosTxt();

        String usuarioTipo = "";

        //Se revisa de uno en uno las datos que hay en las listas
        for (int i = 0; i < usuariosR.getCEDULA().size(); i++) {

            //Para ver si existe el usuario en las listas de la clase
            if (usuariosR.getCEDULA().get(i).equals(cedulaDada)
                    && usuariosR.getCONTRASEÑA().get(i).equals(contraseñaDada)) {

                //Almacenamos el tipo de ususario
                usuarioTipo = usuariosR.getTIPO().get(i);
                break;
            }
        }

        return usuarioTipo;
    }

    public boolean RegristrarUsuario(ArrayList datosUsuario) {

        //Para verificar si se puede hacer el proceso para actualizar
        //Tambien hay que usarlo para mandar un mensaje de aviso
        boolean validar = true;

        //Recorremos la lista de cedulas para verificar si exixte el usuario
        for (int i = 0; i < usuariosR.getCEDULA().size(); i++) {
            if (usuariosR.getCEDULA().get(i).equals(datosUsuario.get(0))) {
                //Si existe cambia el booleano y no deja actualizar
                validar = false;
                break;
            }
        }

        if (validar == true) {
            usuariosW.GuardarUsuariosTxt(datosUsuario);
        }

        //Mandamos a JDRegistroPasajeros para enseñar mensaje
        return validar;
    }
}
