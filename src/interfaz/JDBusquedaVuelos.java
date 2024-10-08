package interfaz;

import database.readingClasses.AerolineaClassR;
import database.readingClasses.AeropuertoClassR;
import negocio.Busqueda_Vuelos;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        setSize(815, 430);

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

        ArrayList Vuelos = new ArrayList();

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

            Vuelos = negocio.Mostrar_Vuelos(salida, llegada, dateFormat);

            cargar_Vuelos(Vuelos);
        } else {
            DefaultTableModel Modelo = (DefaultTableModel) JTVuelosDisponibles.getModel();
            Modelo.setRowCount(0);
        }
    }

    public void cargar_Vuelos(ArrayList Vuelos) {
        DefaultTableModel Modelo = (DefaultTableModel) JTVuelosDisponibles.getModel();

        Modelo.setRowCount(0);

        for (int i = 0; i < Vuelos.size(); i++) {

            ArrayList vuelo = new ArrayList();
            vuelo = (ArrayList) Vuelos.get(i);

            Modelo.addRow(new Object[]{vuelo.get(0), vuelo.get(1), vuelo.get(2), vuelo.get(3),
                vuelo.get(4), vuelo.get(5), vuelo.get(6), vuelo.get(7)});
        }
    }

    public void Vuelo_Seleccionado() {
        AeropuertoClassR aeropuertoClassR = new AeropuertoClassR();
        aeropuertoClassR.leerAeropuertoTxt();
        
        AerolineaClassR aerolineaClassR = new AerolineaClassR();
        aerolineaClassR.leerAerolineaTxt();
        
        int vainaSeleccionadaRow;
        
        try {
            vainaSeleccionadaRow = JTVuelosDisponibles.getSelectedRow();

            String aero = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 0);
            String salida_in = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 1);
            String hora_in = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 2);
            String llegada_out = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 3);
            String hora_out = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 4);
            String escala = (String) JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 5);
            String precio = JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 6).toString();
            String duracion = JTVuelosDisponibles.getValueAt(vainaSeleccionadaRow, 7).toString();
            String cantidad = JSCantidad.getValue().toString();
            
            for (int j = 0; j < aerolineaClassR.getNOMBRE().size(); j++) {
                if (aerolineaClassR.getNOMBRE().get(j).contains(aero)) {
                    aero = aerolineaClassR.getID().get(j).toString();
                    
                }
            }
            
            for (int i = 0; i < aeropuertoClassR.getNOMBRE().size(); i++) {
                if (aeropuertoClassR.getNOMBRE().get(i).contains(salida_in)) {
                    salida_in = aeropuertoClassR.getID().get(i).toString();
                    
                } else if (aeropuertoClassR.getNOMBRE().get(i).contains(llegada_out)) {
                    llegada_out = aeropuertoClassR.getID().get(i).toString();
                    
                } else if (aeropuertoClassR.getNOMBRE().get(i).contains(escala)) {
                    escala = aeropuertoClassR.getID().get(i).toString();
                    
                } else if (escala.contains("Sin escala")) {
                    escala = "000";
                }
            }

            String vuelo_seleccionado = aero + " " + salida_in + " " + hora_in + " " + llegada_out 
                    + " " + hora_out + " " + escala + " " + precio + " " + duracion + " " + cantidad;

            System.out.println(vuelo_seleccionado);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error...Seleccione un vuelo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTTexto1 = new javax.swing.JLabel();
        JCAeroSalida = new javax.swing.JComboBox<>();
        JTTexto2 = new javax.swing.JLabel();
        JCAeroLlegada = new javax.swing.JComboBox<>();
        JTTexto3 = new javax.swing.JLabel();
        JDCFechaSalida = new com.toedter.calendar.JDateChooser();
        JTTexto4 = new javax.swing.JLabel();
        JSCantidad = new javax.swing.JSpinner();
        JTTexto5 = new javax.swing.JLabel();
        Arriba_Abajo = new javax.swing.JScrollPane();
        JTVuelosDisponibles = new javax.swing.JTable();
        JBSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTTexto1.setText("Aeropuerto de salida:");
        getContentPane().add(JTTexto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 360, 30));

        JCAeroSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCAeroSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCAeroSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(JCAeroSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 370, 30));

        JTTexto2.setText("Aeropuerto de llegada:");
        getContentPane().add(JTTexto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 360, 30));

        JCAeroLlegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCAeroLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCAeroLlegadaActionPerformed(evt);
            }
        });
        getContentPane().add(JCAeroLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 370, 30));

        JTTexto3.setText("Fecha de salida:");
        getContentPane().add(JTTexto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 210, 30));

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
        getContentPane().add(JDCFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 219, 30));
        JDCFechaSalida.getDateEditor().setEnabled(false);
        JDCFechaSalida.getJCalendar().setForeground(Color.cyan);

        Calendar calendar = Calendar.getInstance();
        JDCFechaSalida.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDCFechaSalida.setMaxSelectableDate(calendar.getTime());
        JDCFechaSalida.getDateEditor().setDateFormatString("dd/MM/yyyy");

        JTTexto4.setText("Cantidad de boletos:");
        getContentPane().add(JTTexto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 210, 30));

        JSCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        getContentPane().add(JSCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 220, 30));

        JTTexto5.setText("Vuelos disponibles:");
        getContentPane().add(JTTexto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 250, 30));

        JTVuelosDisponibles.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        JTVuelosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aerolinea", "Aeropuerto salida", "Hora", "Aeropuerto llegada", "Hora", "Escala", "Precio total", "Duracion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        JTVuelosDisponibles.getTableHeader().setReorderingAllowed(false);
        Arriba_Abajo.setViewportView(JTVuelosDisponibles);
        if (JTVuelosDisponibles.getColumnModel().getColumnCount() > 0) {
            JTVuelosDisponibles.getColumnModel().getColumn(0).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(0).setPreferredWidth(100);
            JTVuelosDisponibles.getColumnModel().getColumn(1).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(1).setPreferredWidth(100);
            JTVuelosDisponibles.getColumnModel().getColumn(2).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(2).setPreferredWidth(30);
            JTVuelosDisponibles.getColumnModel().getColumn(3).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(3).setPreferredWidth(100);
            JTVuelosDisponibles.getColumnModel().getColumn(4).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(4).setPreferredWidth(30);
            JTVuelosDisponibles.getColumnModel().getColumn(5).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(5).setPreferredWidth(100);
            JTVuelosDisponibles.getColumnModel().getColumn(6).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(6).setPreferredWidth(50);
            JTVuelosDisponibles.getColumnModel().getColumn(7).setResizable(false);
            JTVuelosDisponibles.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        getContentPane().add(Arriba_Abajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 740, 100));

        JBSeleccionar.setText("Seleccionar vuelo");
        JBSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSeleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(JBSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JDCFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDCFechaSalidaPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
            String date = Formato.format(JDCFechaSalida.getDate());
            fecha = date;
            try {
                dateFormat = Formato.parse(date);
                Comprobar_Busqueda();
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

    private void JBSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSeleccionarActionPerformed
        Vuelo_Seleccionado();
    }//GEN-LAST:event_JBSeleccionarActionPerformed

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
    private javax.swing.JLabel JTTexto1;
    private javax.swing.JLabel JTTexto2;
    private javax.swing.JLabel JTTexto3;
    private javax.swing.JLabel JTTexto4;
    private javax.swing.JLabel JTTexto5;
    private javax.swing.JTable JTVuelosDisponibles;
    // End of variables declaration//GEN-END:variables
}
