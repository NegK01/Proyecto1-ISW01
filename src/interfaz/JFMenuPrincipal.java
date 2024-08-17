
package interfaz;

import interfaz.JDBusquedaVuelos;
import interfaz.JDCreacionVuelos;
import interfaz.JDReporte_1;
import interfaz.JFInicioSesión;
import java.text.ParseException;
import javax.swing.JDialog;

public class JFMenuPrincipal extends javax.swing.JFrame {
    
    JDCreacionVuelos jdCreacionVuelos;
    JDBusquedaVuelos jdBusquedaVuelos;
    JDReporte_1 jdReporte_1;
    JDReporte_2 jdReporte_2;
    JDReporte_3 jdReporte_3;

    public JFMenuPrincipal(String usuarioTipo, Integer cedulaActual) throws ParseException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        jdCreacionVuelos = new JDCreacionVuelos(this, rootPaneCheckingEnabled);
        jdBusquedaVuelos = new JDBusquedaVuelos(this, rootPaneCheckingEnabled, cedulaActual);
        jdReporte_1 = new JDReporte_1(this, rootPaneCheckingEnabled);
        jdReporte_2 = new JDReporte_2(this, rootPaneCheckingEnabled);
        jdReporte_3 = new JDReporte_3(this, rootPaneCheckingEnabled);
        
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
        } else {
            JMOpciones.remove(JMBusquedaVuelos);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JMBMenu = new javax.swing.JMenuBar();
        JMOpciones = new javax.swing.JMenu();
        JMCreacionVuelos = new javax.swing.JMenuItem();
        JMBusquedaVuelos = new javax.swing.JMenuItem();
        JMReportes = new javax.swing.JMenu();
        JMReporte1 = new javax.swing.JMenuItem();
        JMReporte2 = new javax.swing.JMenuItem();
        JMReporte = new javax.swing.JMenuItem();
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

        JMReporte1.setText("Reporte 1");
        JMReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMReporte1ActionPerformed(evt);
            }
        });
        JMReportes.add(JMReporte1);

        JMReporte2.setText("Reporte 2");
        JMReporte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMReporte2ActionPerformed(evt);
            }
        });
        JMReportes.add(JMReporte2);

        JMReporte.setText("Reporte 3");
        JMReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMReporteActionPerformed(evt);
            }
        });
        JMReportes.add(JMReporte);

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

    private void JMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMCerrarSesionActionPerformed
        setVisible(false);
        
        JFInicioSesión inicio = new JFInicioSesión();
        inicio.setVisible(true);
    }//GEN-LAST:event_JMCerrarSesionActionPerformed

    private void JMReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMReporte1ActionPerformed
        JDSetVisibleManager(jdReporte_1);
    }//GEN-LAST:event_JMReporte1ActionPerformed

    private void JMReporte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMReporte2ActionPerformed
        JDSetVisibleManager(jdReporte_2);
    }//GEN-LAST:event_JMReporte2ActionPerformed

    private void JMReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMReporteActionPerformed
        JDSetVisibleManager(jdReporte_3);
    }//GEN-LAST:event_JMReporteActionPerformed

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
                //Posible problema por mandar la variable usuarioTipo y cedula, inicioSesion
                try {
                    new JFMenuPrincipal(null, null).setVisible(true);
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
    private javax.swing.JMenuItem JMReporte;
    private javax.swing.JMenuItem JMReporte1;
    private javax.swing.JMenuItem JMReporte2;
    private javax.swing.JMenu JMReportes;
    private javax.swing.JMenu JMSalir;
    // End of variables declaration//GEN-END:variables
}
