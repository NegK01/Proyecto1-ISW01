package negocio;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Correo {

    private boolean compra = true;

    public void Correo() {
        if (compra) {
            QRCodeGenerator();
            EnviarCorreo();
        }
    }

    public void QRCodeGenerator() {
        try {
            String text = "QR Texto \nPrueba linea";  // Texto que tendra nuestro QR
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

    public void EnviarCorreo() {
        // Configuración del servidor SMTP, usamos el servicio host de gmail
        String host = "smtp.gmail.com";
        String port = "587"; // el puerto por defecto de este host
        final String user = "mauricio.or99@gmail.com"; // Correo con el que enviaremos los datos
        final String password = ""; // el token de nuestro correo, (recordar deshabilitarlo)

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

}
