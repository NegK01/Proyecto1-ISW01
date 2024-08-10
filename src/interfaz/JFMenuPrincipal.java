
package interfaz;

import interfaz.JDBusquedaVuelos;
import interfaz.JDCreacionVuelos;
import interfaz.JDReportes;
import interfaz.JFInicioSesión;
import javax.swing.JDialog;

public class JFMenuPrincipal extends javax.swing.JFrame {
    
    JDCreacionVuelos jdCreacionVuelos;
    JDBusquedaVuelos jdBusquedaVuelos;
    JDReportes jdReportes;

    public JFMenuPrincipal(String usuarioTipo) {
        initComponents();
        setLocationRelativeTo(null);
        
        jdCreacionVuelos = new JDCreacionVuelos(this, rootPaneCheckingEnabled);
        jdBusquedaVuelos = new JDBusquedaVuelos(this, rootPaneCheckingEnabled);
        jdReportes = new JDReportes(this, rootPaneCheckingEnabled);
        
        HabilitarOpciones(usuarioTipo);
    }

    public void JDSetVisibleManager(JDialog JDialogSeleccionado) {
        JDialogSeleccionado.setVisible(true);
    }
    
    public void HabilitarOpciones(String usuarioTipo) {
        //En el caso de ser pasajero se van a remover las opciones de admin
        //esto no va a afectar en los siguientes inicios de sesion
        if (usuarioTipo.equals("0")) {
            JMOpciones.remove(JMCreacionVuelos);
            JMOpciones.remove(JMReportes);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JMBMenu = new javax.swing.JMenuBar();
        JMOpciones = new javax.swing.JMenu();
        JMCreacionVuelos = new javax.swing.JMenuItem();
        JMBusquedaVuelos = new javax.swing.JMenuItem();
        JMReportes = new javax.swing.JMenuItem();
        JMCerrarSesion = new javax.swing.JMenuItem();
        JMSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMOpciones.setText("Opciones");

        JMCreacionVuelos.setText("Creación de vuelos");
        JMCreacionVuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMCreacionVuelosActionPerformed(evt);
            }
        });
        JMOpciones.add(JMCreacionVuelos);

        JMBusquedaVuelos.setText("Búsqueda de vuelos");
        JMBusquedaVuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMBusquedaVuelosActionPerformed(evt);
            }
        });
        JMOpciones.add(JMBusquedaVuelos);

        JMReportes.setText("Reportes");
        JMReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMReportesActionPerformed(evt);
            }
        });
        JMOpciones.add(JMReportes);

        JMCerrarSesion.setText("Cerrar sesión");
        JMCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMCerrarSesionActionPerformed(evt);
            }
        });
        JMOpciones.add(JMCerrarSesion);

        JMBMenu.add(JMOpciones);

        JMSalir.setText("Salir");
        JMSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JMSalirMouseClicked(evt);
            }
        });
        JMBMenu.add(JMSalir);

        setJMenuBar(JMBMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_JMSalirMouseClicked

    private void JMCreacionVuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMCreacionVuelosActionPerformed
        JDSetVisibleManager(jdCreacionVuelos);
    }//GEN-LAST:event_JMCreacionVuelosActionPerformed

    private void JMBusquedaVuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMBusquedaVuelosActionPerformed
        JDSetVisibleManager(jdBusquedaVuelos);
    }//GEN-LAST:event_JMBusquedaVuelosActionPerformed

    private void JMReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMReportesActionPerformed
        JDSetVisibleManager(jdReportes);
    }//GEN-LAST:event_JMReportesActionPerformed

    private void JMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMCerrarSesionActionPerformed
        setVisible(false);
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
    }//GEN-LAST:event_JMCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Posible problema por mandar la variable usuarioTipo, inicioSesion
                try {
                    new JFMenuPrincipal(null).setVisible(true);
                } catch (Exception e) {
                    System.out.println("Fue null");
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar JMBMenu;
    private javax.swing.JMenuItem JMBusquedaVuelos;
    private javax.swing.JMenuItem JMCerrarSesion;
    private javax.swing.JMenuItem JMCreacionVuelos;
    private javax.swing.JMenu JMOpciones;
    private javax.swing.JMenuItem JMReportes;
    private javax.swing.JMenu JMSalir;
    // End of variables declaration//GEN-END:variables
}
