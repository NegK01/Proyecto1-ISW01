
package interfaz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import negocio.Inicio_Registro;

public class JDRegistroPasajeros extends javax.swing.JDialog {
    
    Inicio_Registro negocio = new Inicio_Registro();

    public JDRegistroPasajeros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        RestingirRegistro();
    }
    
    //Toda la funcion que le limita como digitar en los espacios de registro
    public void RestingirRegistro() {
        JTCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                //Se consume si no es un digito o si supera los 9 digitos
                if (!Character.isDigit(c) || JTCedula.getText().length() > 8) {
                    e.consume();
                }
            }
        });
        
        JTNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                //Se consume si no son letras o si no son espacios
                if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
                    e.consume();
                }
            }
        });
        
        JPContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                //Se consume si son espacios o si supera cantidad
                if (Character.isSpaceChar(c) || JPContraseña.getText().length() > 19) {
                    e.consume();
                }
            }
        });
        
        JTCorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                //Limitamos que no haya espacios y que sean mayusculas
                if (Character.isSpaceChar(c)) {
                    e.consume();
                }
            }
        });
    }
    
    public void RegistrarUsuario() {
        //Variables para almacenar
        int cedula;
        String nombre;
        String contraseña;
        String correo;
        
        //booleano para de mandar los datos o enseñar error
        boolean validarDatos = true;
        //booleano para mandar el mensaje de confirmacion
        boolean validarMensaje = false;
        
        //Lista en donde almacenaremos y despues mandaremos los datos del registro
        ArrayList datosUsuario = new ArrayList();
        
        
        //Si lso datos estan correctamente se almacenan en la lista
        if (JTCedula.getText().length() > 8) {
            cedula = Integer.parseInt(JTCedula.getText());
            datosUsuario.add(cedula);
            
        } else {
            validarDatos = false;
        }
        
        
        if (JTNombre.getText().length() > 0) {
            nombre = JTNombre.getText();
            datosUsuario.add(nombre);
            
        } else {
            validarDatos = false;
        }
        
        
        if (JPContraseña.getText().length() > 0) {
            contraseña = JPContraseña.getText();
            datosUsuario.add(contraseña);
            
        } else {
            validarDatos = false;
        }
        
        
        if (JTCorreo.getText().contains("@gmail.com")) {
            correo = JTCorreo.getText().toLowerCase();
            datosUsuario.add(correo);
            
        } else {
            validarDatos = false;
        }
        
        
        int edad = Integer.parseInt(JSEdad.getValue().toString());
        datosUsuario.add(edad);
        
        
        //Si no hubo ningun problema se llama la funcion para procesar lso datos
        if (validarDatos == true) {
            //Mandamos de parametro la lista con los datos
            validarMensaje = negocio.RegristrarUsuario(datosUsuario);
            
            //Dependiendo del booleano de RegistrarUsuario enseña un mensaje u otro
            if (validarMensaje == true) {
                //Se cierra la ventana de registro despues de lograr guardar el dato
                setVisible(false);
                
                //Enseña mensaje de confirmacion
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente",
                    "Error", JOptionPane.NO_OPTION);
                
            } else {
                JOptionPane.showMessageDialog(null, "Cedula de usuario ya registrado",
                    "Error", JOptionPane.OK_CANCEL_OPTION);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Error el registrar usuario",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        JLTexto1 = new javax.swing.JLabel();
        JTCedula = new javax.swing.JTextField();
        JLTexto2 = new javax.swing.JLabel();
        JTNombre = new javax.swing.JTextField();
        JLTexto3 = new javax.swing.JLabel();
        JSEdad = new javax.swing.JSpinner();
        JLTexto4 = new javax.swing.JLabel();
        JPContraseña = new javax.swing.JPasswordField();
        JLTexto5 = new javax.swing.JLabel();
        JTCorreo = new javax.swing.JTextField();
        JBRegistro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Titulo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Registrarse:");

        JLTexto1.setText("  Cedula:");

        JLTexto2.setText("  Nombre:");

        JLTexto3.setText("  Edad:");

        JSEdad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        JSEdad.setModel(new javax.swing.SpinnerNumberModel(18, 18, 100, 1));

        JLTexto4.setText("  Contraseña:");

        JLTexto5.setText("  Correo electronico:");

        JBRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JBRegistro.setText("Registrarse");
        JBRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JPContraseña, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(JLTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JSEdad))
                            .addComponent(Titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTCedula, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLTexto2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(JLTexto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLTexto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLTexto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JBRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLTexto1)
                .addGap(2, 2, 2)
                .addComponent(JTCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLTexto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLTexto4)
                .addGap(2, 2, 2)
                .addComponent(JPContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLTexto5)
                .addGap(4, 4, 4)
                .addComponent(JTCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegistroActionPerformed
        RegistrarUsuario();
    }//GEN-LAST:event_JBRegistroActionPerformed

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDRegistroPasajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDRegistroPasajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDRegistroPasajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDRegistroPasajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDRegistroPasajeros dialog = new JDRegistroPasajeros(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBRegistro;
    private javax.swing.JLabel JLTexto1;
    private javax.swing.JLabel JLTexto2;
    private javax.swing.JLabel JLTexto3;
    private javax.swing.JLabel JLTexto4;
    private javax.swing.JLabel JLTexto5;
    private javax.swing.JPasswordField JPContraseña;
    private javax.swing.JSpinner JSEdad;
    private javax.swing.JTextField JTCedula;
    private javax.swing.JTextField JTCorreo;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JLabel Titulo;
    // End of variables declaration//GEN-END:variables
}
