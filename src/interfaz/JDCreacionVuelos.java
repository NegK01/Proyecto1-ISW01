package interfaz;

import database.readingClasses.AeropuertoClassR;
import database.readingClasses.AerolineaClassR;
import database.readingClasses.TripulacionesClassR;
import database.readingClasses.AvionesClassR;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
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
        setResizable(false);

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
            JCAerolinea.addItem(item);
        }

        for (String item : aeropuertoClass.getNOMBRE()) {
            JCAeropuertoSalida.addItem(item);
            JCAeropuertoLlegada.addItem(item);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JCAerolinea = new javax.swing.JComboBox<>();
        JTPrecio = new javax.swing.JTextField();
        JCAeropuertoSalida = new javax.swing.JComboBox<>();
        JCAeropuertoLlegada = new javax.swing.JComboBox<>();
        JDFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        JSHoraSalida = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JDFechaLlegada = new com.toedter.calendar.JDateChooser();
        JSHoraLlegada = new javax.swing.JSpinner();
        int valorMinimo = (Integer) JSHoraSalida.getModel().getValue();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JBAgregar = new javax.swing.JButton();
        JSMinutosSalida = new javax.swing.JSpinner();
        JSMinutosLlegada = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JCAerolinea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jPanel1.add(JCAerolinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 320, -1));

        JTPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTPrecioKeyTyped(evt);
            }
        });
        jPanel1.add(JTPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 110, -1));

        JCAeropuertoSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jPanel1.add(JCAeropuertoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 320, -1));

        JCAeropuertoLlegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jPanel1.add(JCAeropuertoLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 320, -1));

        JDFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDFechaSalidaPropertyChange(evt);
            }
        });
        jPanel1.add(JDFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 200, -1));
        JDFechaSalida.getDateEditor().setEnabled(false);
        JDFechaSalida.getJCalendar().setForeground(Color.cyan);

        Calendar calendar = Calendar.getInstance();
        JDFechaSalida.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDFechaSalida.setMaxSelectableDate(calendar.getTime());
        JDFechaSalida.getDateEditor().setDateFormatString("dd/MM/yyyy");

        jLabel5.setText("Fecha de salida:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 200, -1));

        JSHoraSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        jPanel1.add(JSHoraSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 110, -1));

        jLabel6.setText("Hora y minutos de salida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 230, -1));

        jLabel7.setText("Fecha de llegada:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, -1));

        jLabel8.setText("Hora y minutos de llegada: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 230, -1));

        JDFechaLlegada.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDFechaLlegadaPropertyChange(evt);
            }
        });
        jPanel1.add(JDFechaLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 200, -1));
        JDFechaLlegada.getDateEditor().setEnabled(false);
        JDFechaLlegada.getJCalendar().setForeground(Color.cyan);

        // "calendar" ya esta inicializado por el anterior, por tanto solo lo limpiaremos
        calendar.clear();
        calendar = Calendar.getInstance();
        JDFechaLlegada.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDFechaLlegada.setMaxSelectableDate(calendar.getTime());
        JDFechaLlegada.getDateEditor().setDateFormatString("dd/MM/yyyy");

        JSHoraLlegada.setModel(new javax.swing.SpinnerNumberModel(0, valorMinimo, 23, 1));
        jPanel1.add(JSHoraLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 110, -1));

        jLabel1.setText(" Aerolinea:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 320, -1));

        jLabel2.setText(" Aeropuerto de salida:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setText(" Aeropuerto de llegada:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel4.setText(" Precio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 100, -1));

        JBAgregar.setText("Agregar");
        JBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(JBAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 110, 40));

        JSMinutosSalida.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        jPanel1.add(JSMinutosSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 110, -1));

        JSMinutosLlegada.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        jPanel1.add(JSMinutosLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 110, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText(" :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 10, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText(" :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 10, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* El button valida las entradas y envia los datos a negocio para ser procesados,
       los datos se envian mediante una lista que se estara limpiando tras cada uso
       ya que cada proceso es unitario                                               */
    private void JBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAgregarActionPerformed
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
        String noDebeContener = JCAeropuertoSalida.getItemAt(0);

        // No debe contener es un "..." lo que indica que debe seleccionar una opcion y no dejar 
        // los 3 puntos que son la espera de la entrada de los datos
        try {

            if (JCAeropuertoSalida.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            } else if (JCAeropuertoLlegada.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            } else if (JCAerolinea.getSelectedItem().equals(noDebeContener)) {
                throw new Exception("Complete los datos de entrada.");
            }

            // Validacion, los aeropuertos de salida y llegada deben ser distintos
            if (!((JCAeropuertoLlegada.getEditor().getItem()).equals(JCAeropuertoSalida.getEditor().getItem()))) {

                for (String aeropuertoNombre : aeropuertoClass.getNOMBRE()) {
                    if (JCAeropuertoSalida.getEditor().getItem().equals(aeropuertoNombre)) {
                        aeropuertoSalida = (String) JCAeropuertoSalida.getEditor().getItem();
                    } else if (JCAeropuertoLlegada.getEditor().getItem().equals(aeropuertoNombre)) {
                        aeropuertoLlegada = (String) JCAeropuertoLlegada.getEditor().getItem();
                    }
                }
            } else {
                throw new Exception("El aeropuerto de salida no puede ser el mismo que el de llegada.");
            }

            // Validacion, el precio del vuelo no puede estar vacio
            if (!JTPrecio.getText().isEmpty()) {
                precioVuelo = Integer.parseInt(JTPrecio.getText());
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

                Integer fechaComparada = JDFechaLlegada.getCalendar().compareTo(JDFechaSalida.getCalendar());

                if (!fechaComparada.equals(1)) {
                    throw new Exception("La fecha de llegada no puede ser menor a la de salida.");
                }

            } else {
                throw new Exception("Las fechas no pueden estar vacias.");
            }

            // Validacion extra
            //Si las fechas son iguales, hay que corroborar que la hora sea la adecuada
            if (fechasIguales) {
                horaSalida = (Integer) JSHoraSalida.getValue();
                horaLlegada = (Integer) JSHoraLlegada.getValue();
                minutosSalida = (Integer) JSMinutosSalida.getValue();
                minutosLlegada = (Integer) JSMinutosLlegada.getValue();

                // Calcular la diferencia en minutos entre la hora de salida y la hora de llegada
                int diferenciaHoras = horaLlegada - horaSalida;
                int diferenciaMinutos = minutosLlegada - minutosSalida;
                int minutosTotales = diferenciaHoras * 60 + diferenciaMinutos;

                if (horaSalida == horaLlegada) {
                    if (minutosSalida >= minutosLlegada) {
                        throw new Exception("La hora de salida no puede ser mayor o igual a la de llegada.");
                    } else if (diferenciaMinutos < 60) {
                        throw new Exception("Debe haber al menos una hora de diferencia entre la hora de salida y la hora de llegada.");
                    }
                } else if (horaSalida > horaLlegada) {
                    throw new Exception("La hora de salida no puede ser mayor a la de llegada.");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        aerolinea = (String) JCAerolinea.getSelectedItem();
        horaSalida = (Integer) JSHoraSalida.getValue();
        horaLlegada = (Integer) JSHoraLlegada.getValue();
        minutosSalida = (Integer) JSMinutosSalida.getValue();
        minutosLlegada = (Integer) JSMinutosLlegada.getValue();
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
    }//GEN-LAST:event_JBAgregarActionPerformed

    private void JDFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDFechaSalidaPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(JDFechaSalida.getDate());
            System.out.println(date);
            fecha1 = date;
            // Dado que se pide en el pdf que sea formato Date, ni modo, toca enviarlo asi de descuidado
            dateFormat1 = JDFechaSalida.getDate();
        }
    }//GEN-LAST:event_JDFechaSalidaPropertyChange

    private void JDFechaLlegadaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDFechaLlegadaPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(JDFechaLlegada.getDate());
            System.out.println(date);
            fecha2 = date;
            dateFormat2 = JDFechaLlegada.getDate();
        }
    }//GEN-LAST:event_JDFechaLlegadaPropertyChange

    private void JTPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPrecioKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || JTPrecio.getText().length() > 7) {
            evt.consume();
        }
    }//GEN-LAST:event_JTPrecioKeyTyped

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
    private javax.swing.JButton JBAgregar;
    private javax.swing.JComboBox<String> JCAerolinea;
    private javax.swing.JComboBox<String> JCAeropuertoLlegada;
    private javax.swing.JComboBox<String> JCAeropuertoSalida;
    private com.toedter.calendar.JDateChooser JDFechaLlegada;
    private com.toedter.calendar.JDateChooser JDFechaSalida;
    private javax.swing.JSpinner JSHoraLlegada;
    private javax.swing.JSpinner JSHoraSalida;
    private javax.swing.JSpinner JSMinutosLlegada;
    private javax.swing.JSpinner JSMinutosSalida;
    private javax.swing.JTextField JTPrecio;
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
    // End of variables declaration//GEN-END:variables
}
