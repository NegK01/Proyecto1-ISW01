package interfaz;

import database.classes.AeropuertoClass;
import database.classes.AerolineaClass;
import database.classes.TripulacionesClass;
import database.classes.AvionesClass;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;

public class JDCreacionVuelos extends javax.swing.JDialog {

    private AerolineaClass aerolineaClass;
    private AeropuertoClass aeropuertoClass;
    private TripulacionesClass tripulacionesClass;
    private AvionesClass avionesClass;
    private boolean fechasIguales = false;
    private String fecha1;
    private String fecha2;

    public JDCreacionVuelos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        cargarDatos();

    }

    public void cargarDatos() {
        aerolineaClass = new AerolineaClass();
        aerolineaClass.leerAerolineaTxt();

        aeropuertoClass = new AeropuertoClass();
        aeropuertoClass.leerAeropuertoTxt();

        tripulacionesClass = new TripulacionesClass();
        tripulacionesClass.LeerTripulacionesTxt();

        avionesClass = new AvionesClass();
        avionesClass.LeerAvionesTxt();

        for (String item : aerolineaClass.getNOMBRE()) {
            jComboBox4.addItem(item);
        }

        for (String item : aeropuertoClass.getNOMBRE()) {
            jComboBox5.addItem(item);
            jComboBox6.addItem(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jSpinner4 = new javax.swing.JSpinner();
        int valorMinimo = (Integer) jSpinner3.getModel().getValue();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, -1));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 110, -1));

        jPanel1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 320, -1));

        jPanel1.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 320, -1));

        jDateChooser3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser3PropertyChange(evt);
            }
        });
        jPanel1.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, -1));
        jDateChooser3.getDateEditor().setEnabled(false);
        jDateChooser3.getJCalendar().setForeground(Color.cyan);

        Calendar calendar = Calendar.getInstance();
        jDateChooser3.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        jDateChooser3.setMaxSelectableDate(calendar.getTime());
        jDateChooser3.getDateEditor().setDateFormatString("dd/MM/yyyy");

        jLabel5.setText("Fecha de salida:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, -1));

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));
        jPanel1.add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 110, -1));

        jLabel6.setText("Hora de salida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 110, -1));

        jLabel7.setText("Fecha de llegada:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, -1));

        jLabel8.setText("Hora de llegada:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 110, -1));

        jDateChooser4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser4PropertyChange(evt);
            }
        });
        jPanel1.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 130, -1));
        jDateChooser4.getDateEditor().setEnabled(false);
        jDateChooser4.getJCalendar().setForeground(Color.cyan);

        // "calendar" ya esta inicializado por el anterior, por tanto solo lo limpiaremos
        calendar.clear();
        calendar = Calendar.getInstance();
        jDateChooser4.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        jDateChooser4.setMaxSelectableDate(calendar.getTime());
        jDateChooser4.getDateEditor().setDateFormatString("dd/MM/yyyy");

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0, valorMinimo, 24, 1));
        jPanel1.add(jSpinner4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 110, -1));

        jLabel1.setText(" Aerolinea:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel2.setText(" Aeropuerto de salida:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setText(" Aeropuerto de llegada:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel4.setText(" Precio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* El button valida las entradas y envia los datos a negocio para ser procesados,
       los datos se envian mediante una lista que se estara limpiando tras cada uso
       ya que cada proceso es unitario                                               */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Validacion, el precio del vuelo no puede estar vacio
            if (!jTextField2.getText().isEmpty()) {
                
            } else {
                throw new Exception("El precio del vuelo no puede estar vacio.");
            }
            
            
            if (!((jComboBox6.getEditor().getItem()).equals(jComboBox5.getEditor().getItem()))) {
                
            } else {
                throw new Exception("El aeropuerto de salida no puede ser el mismo que el de llegada.");
            }
            
            
            // Validacion, las fechas no pueden estar nulas
            if (fecha1 != null && fecha2 != null) {
                // Se verifica que sean iguales o no
                if (fecha2.equals(fecha1)) {
                    fechasIguales = true;
                } else {
                    fechasIguales = false;
                }
            } else {
                throw new Exception("Las fechas no pueden estar vacias.");
            }

            //Si las fechas son iguales, hay que corroborar que la hora sea la adecuada
            if (fechasIguales) {
                Integer horaSalida = (Integer) jSpinner3.getValue();
                Integer horaLlegada = (Integer) jSpinner4.getValue();
                // Si no se cumple el caso de que la hora de salida sea menor al de llegada, estamos con un error en el tiempo  
                if (!(horaSalida < horaLlegada)) {
                    throw new Exception("La hora de salida no puede ser mayor o igual a la de llegada.");
                }
            }
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Hay que recoger un retorno por parte de negocio, esto para validar que realmente se agrego
        JOptionPane.showMessageDialog(rootPane, "Datos añadidos correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(jDateChooser3.getDate());
            System.out.println(date);
            fecha1 = date;

        }
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void jDateChooser4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser4PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(jDateChooser4.getDate());
            System.out.println(date);
            fecha2 = date;

        }
    }//GEN-LAST:event_jDateChooser4PropertyChange

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || jTextField2.getText().length() > 7) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(JDCreacionVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDCreacionVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDCreacionVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDCreacionVuelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDCreacionVuelos dialog = new JDCreacionVuelos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
