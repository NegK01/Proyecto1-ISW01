
package interfaz;

import database.readingClasses.AeropuertoClassR;
import negocio.Busqueda_Vuelos;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBusquedaVuelos extends javax.swing.JDialog {
    
    private String llegada;
    private String salida;
    private String fecha;
    private Date dateFormat;
    
    private AeropuertoClassR aeropuertoClass;
    
    public JDBusquedaVuelos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setSize(690, 430);
        
        Cargar_ComboBox();
    }
    
    public void Cargar_ComboBox() {
        aeropuertoClass = new AeropuertoClassR();
        aeropuertoClass.leerAeropuertoTxt();

        for (String item : aeropuertoClass.getNOMBRE()) {
            JCAeroSalida.addItem(item);
            JCAeroLlegada.addItem(item);
        }
    }
    
    public void Comprobar_Busqueda() {
        
        boolean comprobado = true;
        
        if (JCAeroSalida.getSelectedItem().equals("...")) {
            comprobado = false;
        } else if (JCAeroLlegada.getSelectedItem().equals("...")) {
            comprobado = false;
        } else if (dateFormat == null) {
            comprobado = false;
        } else if (JCAeroSalida.getSelectedItem().equals(JCAeroLlegada.getSelectedItem())) {
            comprobado = false;
        }
        
        if (comprobado == true) {
            salida = (String) JCAeroSalida.getSelectedItem();
            llegada = (String) JCAeroLlegada.getSelectedItem();
            Integer cantidad = (Integer) JSCantidad.getValue();
            
            Busqueda_Vuelos negocio = new Busqueda_Vuelos();
            
            negocio.Mostrar_Vuelos(salida, llegada, cantidad, dateFormat);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTTexto1 = new javax.swing.JTextField();
        JCAeroSalida = new javax.swing.JComboBox<>();
        JTTexto2 = new javax.swing.JTextField();
        JCAeroLlegada = new javax.swing.JComboBox<>();
        JTTexto3 = new javax.swing.JTextField();
        JDCFechaSalida = new com.toedter.calendar.JDateChooser();
        JTTexto4 = new javax.swing.JTextField();
        JSCantidad = new javax.swing.JSpinner();
        JTTexto5 = new javax.swing.JTextField();
        Arriba_Abajo = new javax.swing.JScrollPane();
        JTVuelosDisponibles = new javax.swing.JTable();
        JBSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTTexto1.setText("Aeropuerto de salida:");
        getContentPane().add(JTTexto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 140, -1));

        JCAeroSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCAeroSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCAeroSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(JCAeroSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 370, 30));

        JTTexto2.setText("Aeropuerto de llegada:");
        getContentPane().add(JTTexto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, -1));

        JCAeroLlegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCAeroLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCAeroLlegadaActionPerformed(evt);
            }
        });
        getContentPane().add(JCAeroLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 370, 30));

        JTTexto3.setText("Fecha de salida:");
        getContentPane().add(JTTexto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 130, -1));

        JDCFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDCFechaSalidaPropertyChange(evt);
            }
        });
        JDCFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDCFechaSalidaPropertyChange(evt);
            }
        });
        getContentPane().add(JDCFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 219, 30));
        JDCFechaSalida.getDateEditor().setEnabled(false);
        JDCFechaSalida.getJCalendar().setForeground(Color.cyan);

        Calendar calendar = Calendar.getInstance();
        JDCFechaSalida.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDCFechaSalida.setMaxSelectableDate(calendar.getTime());
        JDCFechaSalida.getDateEditor().setDateFormatString("dd/MM/yyyy");

        JTTexto4.setText("Cantidad de personas:");
        getContentPane().add(JTTexto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        JSCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        getContentPane().add(JSCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 220, -1));

        JTTexto5.setText("Vuelos disponibles actualmente:");
        getContentPane().add(JTTexto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, -1));

        JTVuelosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Aerolinea", "Salida", "Hora salida", "Llegada", "Hora llegada", "Escala", "Precio total", "Duracion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Arriba_Abajo.setViewportView(JTVuelosDisponibles);
        if (JTVuelosDisponibles.getColumnModel().getColumnCount() > 0) {
            JTVuelosDisponibles.getColumnModel().getColumn(0).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(1).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(2).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(3).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(4).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(5).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(6).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(7).setResizable(false);
        }

        getContentPane().add(Arriba_Abajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 610, 110));

        JBSeleccionar.setText("Seleccionar vuelo");
        getContentPane().add(JBSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JDCFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDCFechaSalidaPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
            String date = Formato.format(JDCFechaSalida.getDate());
            fecha = date;
            try {
                dateFormat = Formato.parse(date) ;
            } catch (ParseException e) {
                System.out.println("\u001B[31mERROR:\u001B[0m " + e);
            }
        }
    }//GEN-LAST:event_JDCFechaSalidaPropertyChange

    private void JCAeroSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCAeroSalidaActionPerformed
        Comprobar_Busqueda();
    }//GEN-LAST:event_JCAeroSalidaActionPerformed

    private void JCAeroLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCAeroLlegadaActionPerformed
        Comprobar_Busqueda();
    }//GEN-LAST:event_JCAeroLlegadaActionPerformed

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
            java.util.logging.Logger.getLogger(JDBusquedaVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBusquedaVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBusquedaVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBusquedaVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBusquedaVuelos dialog = new JDBusquedaVuelos(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane Arriba_Abajo;
    private javax.swing.JButton JBSeleccionar;
    private javax.swing.JComboBox<String> JCAeroLlegada;
    private javax.swing.JComboBox<String> JCAeroSalida;
    private com.toedter.calendar.JDateChooser JDCFechaSalida;
    private javax.swing.JSpinner JSCantidad;
    private javax.swing.JTextField JTTexto1;
    private javax.swing.JTextField JTTexto2;
    private javax.swing.JTextField JTTexto3;
    private javax.swing.JTextField JTTexto4;
    private javax.swing.JTextField JTTexto5;
    private javax.swing.JTable JTVuelosDisponibles;
    // End of variables declaration//GEN-END:variables
}
