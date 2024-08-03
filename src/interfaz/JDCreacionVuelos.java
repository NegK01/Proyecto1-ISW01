package interfaz;

import database.readingClasses.AeropuertoClassR;
import database.readingClasses.AerolineaClassR;
import database.readingClasses.TripulacionesClassR;
import database.readingClasses.AvionesClassR;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.Timer;
import negocio.CreacionVuelos;

public class JDCreacionVuelos extends javax.swing.JDialog {

    private AerolineaClassR aerolineaClass;
    private AeropuertoClassR aeropuertoClass;
    private TripulacionesClassR tripulacionesClass;
    private AvionesClassR avionesClass;
    private boolean fechasIguales = false;
    private String fecha1;
    private String fecha2;
    private Date dateFormat1;
    private Date dateFormat2;

    public JDCreacionVuelos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cargarDatos();
    }

    public void cargarDatos() {
        aerolineaClass = new AerolineaClassR();
        aerolineaClass.leerAerolineaTxt();

        aeropuertoClass = new AeropuertoClassR();
        aeropuertoClass.leerAeropuertoTxt();

        tripulacionesClass = new TripulacionesClassR();
        tripulacionesClass.LeerTripulacionesTxt();

        avionesClass = new AvionesClassR();
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
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, -1));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 110, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jPanel1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 320, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
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

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        jPanel1.add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 110, -1));

        jLabel6.setText("Hora y minutos de salida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 200, -1));

        jLabel7.setText("Fecha de llegada:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, -1));

        jLabel8.setText("Hora y minutos de llegada: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 200, -1));

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

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0, valorMinimo, 23, 1));
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

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 80, -1));

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        jPanel1.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 80, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText(" :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 10, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText(" :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 10, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* El button valida las entradas y envia los datos a negocio para ser procesados,
       los datos se envian mediante una lista que se estara limpiando tras cada uso
       ya que cada proceso es unitario                                               */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String aerolinea = "";
        String aeropuertoSalida = "";
        String aeropuertoLlegada = "";
        Integer precioVuelo = 0;
        String fechaDeSalida = "";
        String fechaDeLlegada = "";
        Integer horaSalida = 0;
        Integer minutosSalida = 0;
        Integer horaLlegada = 0;
        Integer minutosLlegada = 0;
        String noDebeContener = jComboBox5.getItemAt(0);

        // No debe contener es un "..." lo que indica que debe seleccionar una opcion y no dejar 
        // los 3 puntos que son la espera de la entrada de los datos
        try {

            if (jComboBox5.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            } else if (jComboBox6.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            } else if (jComboBox4.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            }

            // Validacion, los aeropuertos de salida y llegada deben ser distintos
            if (!((jComboBox6.getEditor().getItem()).equals(jComboBox5.getEditor().getItem()))) {

                for (String aeropuertoNombre : aeropuertoClass.getNOMBRE()) {
                    if (jComboBox5.getEditor().getItem().equals(aeropuertoNombre)) {
                        aeropuertoSalida = (String) jComboBox5.getEditor().getItem();
                    } else if (jComboBox6.getEditor().getItem().equals(aeropuertoNombre)) {
                        aeropuertoLlegada = (String) jComboBox6.getEditor().getItem();
                    }
                }
            } else {
                throw new Exception("El aeropuerto de salida no puede ser el mismo que el de llegada.");
            }

            // Validacion, el precio del vuelo no puede estar vacio
            if (!jTextField2.getText().isEmpty()) {
                precioVuelo = Integer.parseInt(jTextField2.getText());
            } else {
                throw new Exception("El precio del vuelo no puede estar vacio.");
            }

            // Validacion, las fechas no pueden estar nulas
            if (fecha1 != null && fecha2 != null) {
                // Conque no sean vacias es todo lo que nos importa desde un principio
                fechaDeSalida = fecha1;
                fechaDeLlegada = fecha2;

                // Ahora se verifica si son iguales o no
                if (fechaDeLlegada.equals(fechaDeSalida)) {
                    fechasIguales = true;
                } else {
                    fechasIguales = false;
                }

                Integer fechaComparada = jDateChooser4.getCalendar().compareTo(jDateChooser3.getCalendar());

                if (!fechaComparada.equals(1)) {
                    throw new Exception("La fecha de llegada no puede ser menor a la de salida.");
                }

            } else {
                throw new Exception("Las fechas no pueden estar vacias.");
            }

            // Validacion extra
            //Si las fechas son iguales, hay que corroborar que la hora sea la adecuada
            if (fechasIguales) {
                horaSalida = (Integer) jSpinner3.getValue();
                horaLlegada = (Integer) jSpinner4.getValue();
                minutosSalida = (Integer) jSpinner1.getValue();
                minutosLlegada = (Integer) jSpinner2.getValue();
                // Si no se cumple el caso de que la hora de salida sea menor al de llegada, estamos con un error en el tiempo  
                if (!(horaSalida < horaLlegada)) {
                    if (!(minutosSalida < minutosLlegada)) { // Si no se cumple la condicion correcta, se lanza un error
                        throw new Exception("La hora de salida no puede ser mayor o igual a la de llegada.");
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        aerolinea = (String) jComboBox4.getSelectedItem();
        horaSalida = (Integer) jSpinner3.getValue();
        horaLlegada = (Integer) jSpinner4.getValue();
        minutosSalida = (Integer) jSpinner1.getValue();
        minutosLlegada = (Integer) jSpinner2.getValue();
        // Una vez todo validado, se realiza el envio de los datos a negocio
        CreacionVuelos creacionVuelos = new CreacionVuelos(aerolinea,
                aeropuertoSalida, aeropuertoLlegada, precioVuelo, fechaDeSalida,
                fechaDeLlegada, horaSalida, horaLlegada, minutosSalida, minutosLlegada,
                dateFormat1, dateFormat2);

        JOptionPane.showMessageDialog(rootPane, "Realizando operacion.", "Info", JOptionPane.INFORMATION_MESSAGE);

        if (creacionVuelos.creacionVueloExitoso()) {
            JOptionPane.showMessageDialog(rootPane, "Se ha realizado la creacion exitosamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay aviones disponibles para esta aerolinea.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(jDateChooser3.getDate());
            System.out.println(date + "1");
            fecha1 = date;
            // Dado que se pide en el pdf que sea formato Date, ni modo, toca enviarlo asi de descuidado
            dateFormat1 = jDateChooser3.getDate();

        }
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void jDateChooser4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser4PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(jDateChooser4.getDate());
            System.out.println(date);
            fecha2 = date;
            dateFormat2 = jDateChooser4.getDate();
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
