package interfaz;

import interfaz.JDRegistroPasajeros;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.*;

public class JFInicioSesión extends javax.swing.JFrame {
    JDBusquedaVuelos enviarCedula = new JDBusquedaVuelos(this, rootPaneCheckingEnabled, null);
    Inicio_Registro negocio = new Inicio_Registro();

    public JFInicioSesión() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

        Restringir_Datos();
    }

    //Metodo para negar diferentes tipos de parametros al teclado
    public void Restringir_Datos() {
        JTCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                //Se consume en el caso de que no sea un digito o supere la cantidad de 9
                if (!Character.isDigit(c) || JTCedula.getText().length() > 8) {
                    e.consume();
                }
            }
        });

        JPContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                //Se consume en el caso de que sea un espacio o supere cantidad
                if (Character.isSpaceChar(c) || JPContraseña.getText().length() > 19) {
                    e.consume();
                }
            }
        });
    }

    public void Comprobar_Inicio() throws ParseException {
        //Variable string que almasena si es admin o pasajero
        String usuarioTipo = "";

        if (JTCedula.getText().length() > 8 && JPContraseña.getText().length() > 0) {

            //Almacenamos datos de interfaz
            int cedula = Integer.parseInt(JTCedula.getText());
            String contraseña = JPContraseña.getText();

            //Se llama la funcion que tiene de datos las variables de la interfaz y 
            //si existe el usuario almacena su tipo
            usuarioTipo = negocio.DarAcceso(cedula, contraseña);

            //Existe y obtiene ecceso a el menu principal
            if (usuarioTipo.equals("0") || usuarioTipo.equals("1")) {
                //Se cierra el inicio de sesion y despues se abre el menu de opciones
                setVisible(false);
                
                JFMenuPrincipal menu = new JFMenuPrincipal(usuarioTipo, cedula);
                menu.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                        "Mensaje", JOptionPane.NO_OPTION);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Rellene correctamente los Espacios",
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
        JPContraseña = new javax.swing.JPasswordField();
        JBInicioSesión = new javax.swing.JButton();
        JBRegistro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Inicio de sesión:");

        JLTexto1.setText("  Contraseña:");

        JTCedula.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        JLTexto2.setText("  Cedula:");

        JPContraseña.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        JBInicioSesión.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JBInicioSesión.setText("Ingresar");
        JBInicioSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBInicioSesiónActionPerformed(evt);
            }
        });

        JBRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JBRegistro.setText("Registraste");
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
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLTexto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPContraseña)
                    .addComponent(JTCedula)
                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBRegistro)
                        .addGap(18, 18, 18)
                        .addComponent(JBInicioSesión, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JLTexto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLTexto2)
                .addGap(5, 5, 5)
                .addComponent(JTCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(JLTexto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(JBInicioSesión, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBInicioSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBInicioSesiónActionPerformed
        try {
            Comprobar_Inicio();
        } catch (ParseException ex) {
            Logger.getLogger(JFInicioSesión.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JBInicioSesiónActionPerformed

    private void JBRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegistroActionPerformed
        JDRegistroPasajeros registro = new JDRegistroPasajeros(this, rootPaneCheckingEnabled);
        registro.setVisible(true);
    }//GEN-LAST:event_JBRegistroActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFInicioSesión.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFInicioSesión.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFInicioSesión.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFInicioSesión.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFInicioSesión().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBInicioSesión;
    private javax.swing.JButton JBRegistro;
    private javax.swing.JLabel JLTexto1;
    private javax.swing.JLabel JLTexto2;
    private javax.swing.JPasswordField JPContraseña;
    private javax.swing.JTextField JTCedula;
    private javax.swing.JLabel Titulo;
    // End of variables declaration//GEN-END:variables
}
