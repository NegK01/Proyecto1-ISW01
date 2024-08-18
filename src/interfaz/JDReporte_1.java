package interfaz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.Reporte_1;

public class JDReporte_1 extends javax.swing.JDialog {

    private Date fechaInicio;
    private Date fechaFinal;

    private String fecha1_str;
    private String fecha2_str;

    private ArrayList Compras = new ArrayList();

    Reporte_1 negocio = new Reporte_1();

    public JDReporte_1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public void Cargar_Table() {
        DefaultTableModel Modelo = (DefaultTableModel) JTCompras.getModel();

        Modelo.setRowCount(0);

        for (int i = 0; i < Compras.size(); i++) {

            ArrayList Compra = new ArrayList();
            Compra = (ArrayList) Compras.get(i);

            Modelo.addRow(new Object[]{Compra.get(0), Compra.get(1), Compra.get(2),
                Compra.get(3), Compra.get(4), Compra.get(5)});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTexto1 = new javax.swing.JLabel();
        JDCRangoInicial = new com.toedter.calendar.JDateChooser();
        JLTexto2 = new javax.swing.JLabel();
        JDCRangoFinal = new com.toedter.calendar.JDateChooser();
        JLTexto3 = new javax.swing.JLabel();
        Arriba_Abajo = new javax.swing.JScrollPane();
        JTCompras = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLTexto1.setText("Rango inicial de compras realizadas:");

        JDCRangoInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDCRangoInicialPropertyChange(evt);
            }
        });
        JDCRangoInicial.getDateEditor().setEnabled(false);
        JDCRangoInicial.getJCalendar().setForeground(Color.cyan);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        JDCRangoInicial.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDCRangoInicial.setMaxSelectableDate(calendar.getTime());
        JDCRangoInicial.getDateEditor().setDateFormatString("dd/MM/yyyy");

        JLTexto2.setText("Rango final de compras realizadas:");

        JDCRangoFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDCRangoFinalPropertyChange(evt);
            }
        });
        JDCRangoFinal.getDateEditor().setEnabled(false);
        JDCRangoFinal.getJCalendar().setForeground(Color.cyan);

        calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        JDCRangoFinal.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        JDCRangoFinal.setMaxSelectableDate(calendar.getTime());
        JDCRangoFinal.getDateEditor().setDateFormatString("dd/MM/yyyy");

        JLTexto3.setText("Datos de compras realizadas:");

        JTCompras.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        JTCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Pasajero", "Aeropuerto de salida", "Aeropuerto de escala", "Aeropuerto de salida", "Fecha de compra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTCompras.getTableHeader().setReorderingAllowed(false);
        Arriba_Abajo.setViewportView(JTCompras);
        if (JTCompras.getColumnModel().getColumnCount() > 0) {
            JTCompras.getColumnModel().getColumn(0).setResizable(false);
            JTCompras.getColumnModel().getColumn(0).setPreferredWidth(15);
            JTCompras.getColumnModel().getColumn(1).setResizable(false);
            JTCompras.getColumnModel().getColumn(1).setPreferredWidth(15);
            JTCompras.getColumnModel().getColumn(2).setResizable(false);
            JTCompras.getColumnModel().getColumn(3).setResizable(false);
            JTCompras.getColumnModel().getColumn(4).setResizable(false);
            JTCompras.getColumnModel().getColumn(5).setResizable(false);
            JTCompras.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Arriba_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JLTexto3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JDCRangoInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLTexto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JDCRangoFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLTexto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDCRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLTexto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDCRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(JLTexto3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Arriba_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JDCRangoInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDCRangoInicialPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
            String date = Formato.format(JDCRangoInicial.getDate());
            fecha1_str = date;
            try {
                fechaInicio = Formato.parse(date);
                if (fechaInicio != null && fechaFinal != null) {
                    if (fechaInicio.before(fechaFinal)) {
                        Compras = negocio.BuscarPorFechas(fechaInicio, fechaFinal, fecha1_str, fecha2_str);
                        Cargar_Table();
                    } else {
                        JOptionPane.showMessageDialog(null, "No coinciden fecha de inicio y fecha final",
                                "Mensaje", JOptionPane.CANCEL_OPTION);

                        DefaultTableModel Modelo = (DefaultTableModel) JTCompras.getModel();
                        Modelo.setRowCount(0);
                    }
                }
            } catch (ParseException e) {
                System.out.println("\u001B[31mERROR:\u001B[0m " + e);
            }
        }
    }//GEN-LAST:event_JDCRangoInicialPropertyChange

    private void JDCRangoFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDCRangoFinalPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
            String date = Formato.format(JDCRangoFinal.getDate());
            fecha2_str = date;
            try {
                fechaFinal = Formato.parse(date);
                if (fechaInicio != null && fechaFinal != null) {
                    if (fechaInicio.before(fechaFinal)) {
                        Compras = negocio.BuscarPorFechas(fechaInicio, fechaFinal, fecha1_str, fecha2_str);
                        Cargar_Table();
                    } else {
                        JOptionPane.showMessageDialog(null, "No coinciden fecha de inicio y fecha final",
                                "Mensaje", JOptionPane.CANCEL_OPTION);
                        
                        DefaultTableModel Modelo = (DefaultTableModel) JTCompras.getModel();
                        Modelo.setRowCount(0);
                    }
                }
            } catch (ParseException e) {
                System.out.println("\u001B[31mERROR:\u001B[0m " + e);
            }
        }
    }//GEN-LAST:event_JDCRangoFinalPropertyChange

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDReporte_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDReporte_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDReporte_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDReporte_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDReporte_1 dialog = new JDReporte_1(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser JDCRangoFinal;
    private com.toedter.calendar.JDateChooser JDCRangoInicial;
    private javax.swing.JLabel JLTexto1;
    private javax.swing.JLabel JLTexto2;
    private javax.swing.JLabel JLTexto3;
    private javax.swing.JTable JTCompras;
    // End of variables declaration//GEN-END:variables
}
