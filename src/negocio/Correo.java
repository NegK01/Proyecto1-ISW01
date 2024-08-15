package negocio;

import java.util.*;
import database.readingClasses.AeropuertoClassR;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import database.readingClasses.HistorialClassR;
import database.readingClasses.UsuariosClassR;

public class Correo {

    private boolean compra = true;

    public void Correo() throws java.text.ParseException {
        if (compra == true) {
            generarRecibo();
            QRCodeGenerator();
            EnviarCorreo();
        }
    }

    public void QRCodeGenerator() throws java.text.ParseException {
        try {

            String text = generarRecibo();

            File file = new File("src/database/files/QRCode.png");

            QRCode.from(text)
                    .to(ImageType.PNG)
                    .withSize(300, 300)
                    .writeTo(new FileOutputStream(file));

            System.out.println("Codigo QR generado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar el codigo QR: " + e.getMessage());
        }

    }

    public void EnviarCorreo() throws java.text.ParseException {
        // Configuración del servidor SMTP, usamos el servicio host de gmail
        String host = "smtp.gmail.com";
        String port = "587"; // el puerto por defecto de este host
        final String user = "mandangaymerequetengue@gmail.com"; // Correo con el que enviaremos los datos
        final String password = "anwz qawd elpi tbhw"; // el token de nuestro correo, (recordar deshabilitarlo)

        // Propiedades del correo donde asignamos el host, puerto y autenticacion
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Aqui es para obtener la sesion de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lopezrodriguezlucas10@gmail.com"));
            message.setSubject("Factura Compra de Vuelo.");

            // Crear un cuerpo del email (texto nomas) 
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Escanee el QR para obtener más detalles de su compra.");

            // Creamos un multipart para guardar varios cuerpos en uno
            // agreagamos el cuerpo actual (el texto) al email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Restablecemos el cuerpo para añadir algo nuevo
            messageBodyPart = new MimeBodyPart();
            String filename = "src/database/files/QRCode.png"; // Ruta del archivo que adjuntaremos
            File file = new File(filename); // lo asignamos como File
            messageBodyPart.attachFile(file); // Agregamos el file-archivo al cuerpo 
            multipart.addBodyPart(messageBodyPart); // Con multipart agregamos el nuevo cuerpo con el QR

            // Agregamos al email el contenido de multipart (todos los cuerpos que agregamos)
            message.setContent(multipart);

            // Enviamos el mensaje
            Transport.send(message);

            System.out.println("\u001B[32mCorreo enviado exitosamente.\u001B[0m ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generarRecibo() throws java.text.ParseException {
        HistorialClassR historial = new HistorialClassR();
        historial.leerHistorialTxt();

        UsuariosClassR usuarios = new UsuariosClassR();
        usuarios.LeerUsuariosTxt();

        AeropuertoClassR aero = new AeropuertoClassR();
        aero.leerAeropuertoTxt();
        
        
        Integer factura = historial.getCEDULA().size() - 1;

        
        Date fecha = historial.getFECHA().get(factura);
        Integer identificador = historial.getID().get(factura);

        Integer cedula = historial.getCEDULA().get(factura);

        String nombre = "";
        for (int i = 0; i < usuarios.getCEDULA().size(); i++) {
            if (usuarios.getCEDULA().get(i).equals(cedula)) {
                nombre = usuarios.getNOMBRES().get(i);
            }
        }

        Integer id_1 = historial.getID_SALIDA().get(factura);
        Integer id_2 = historial.getID_LLEGADA().get(factura);
        Integer id_3 = historial.getID_ESCALA().get(factura);

        String salida = "";
        String llegada = "";
        String escala = "Sin escala";

        for (int j = 0; j < aero.getID().size(); j++) {
            if (aero.getID().get(j).equals(id_1)) {
                String aero_1 = aero.getNOMBRE().get(j);
                
                salida = aero_1.replace("Aeropuerto Internacional", "");
                salida = salida.replace("de", "");

            } else if (aero.getID().get(j).equals(id_2)) {
                String aero_2 = aero.getNOMBRE().get(j);
                
                llegada = aero_2.replace("Aeropuerto Internacional", "");
                llegada = llegada.replace("de", "");

            } else if (aero.getID().get(j).equals(id_3)) {
                String aero_3 = aero.getNOMBRE().get(j);
                
                escala = aero_3.replace("Aeropuerto Internacional", "");
                escala = escala.replace("de", "");
            }
        }
        
        String asientos = historial.getASIENTOS().get(factura);
        Integer cantidad = historial.getCAN_BOLETOS().get(factura);
        Integer horas = historial.getDURACIONHORAS().get(factura);
        Integer minutos = historial.getDURACIONMINUTOS().get(factura);
        Double total = historial.getCOSTO().get(factura);
        

        String recibo = """
                      ---------------------------------------------
                        TU TRANSACCIÓN SE REALIZO CORRECTAMENTE
                      ---------------------------------------------
                      Fecha: %s
                      Número de recibo: %s
                      
                      - - - - - - - - - - - - - - - - - - - - - - -
                      
                      Recibido de: %s
                      ID del cliente: %s
                      
                      - - - - - - - - - - - - - - - - - - - - - - -
                      
                      Aeropuerto de salida:  %s
                      Aeropuerto de llegada: %s
                      Aeropuerto de escala:  %s
                      
                      Asientos asignados: %s
                      
                      - - - - - - - - - - - - - - - - - - - - - - -
                      
                      Cantidad de Boletos: %s
                      Duracion total: %s:%s
                      
                      Total: %s
                      
                      ---------------------------------------------
                      """;

        String text = String.format(recibo, fecha, identificador, nombre, 
                cedula, salida, llegada, escala, asientos, cantidad, 
                horas,minutos, total);

        System.out.println(text);

        return text;
    }
}
