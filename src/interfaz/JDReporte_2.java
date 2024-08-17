package interfaz;

import database.readingClasses.AerolineaClassR;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import negocio.Reporte_2;

public class JDReporte_2 extends javax.swing.JDialog {

    private ArrayList DatosAero = new ArrayList();

    Reporte_2 negocio = new Reporte_2();

    public JDReporte_2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        Cargar_ComboBox();
    }

    public void Cargar_ComboBox() {
        AerolineaClassR aerolinea = new AerolineaClassR();
        aerolinea.leerAerolineaTxt();

        for (String item : aerolinea.getNOMBRE()) {
            JCBAeroSeleccionada.addItem(item);
        }
    }

    public void Cargar_Table() {
        DefaultTableModel Modelo = (DefaultTableModel) JTDatosVuelos.getModel();

        Modelo.setRowCount(0);

        for (int i = 0; i < DatosAero.size(); i++) {

            ArrayList vuelo = new ArrayList();
            vuelo = (ArrayList) DatosAero.get(i);

            Modelo.addRow(new Object[]{vuelo.get(0), vuelo.get(1),
                vuelo.get(2), vuelo.get(3), vuelo.get(4)});
        }

        DatosAero.removeAll(DatosAero);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTexto1 = new javax.swing.JLabel();
        JCBAeroSeleccionada = new javax.swing.JComboBox<>();
        JLTexto2 = new javax.swing.JLabel();
        Arriba_Abajo = new javax.swing.JScrollPane();
        JTDatosVuelos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLTexto1.setText("Aerolinea seleccionada:");

        JCBAeroSeleccionada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCBAeroSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBAeroSeleccionadaActionPerformed(evt);
            }
        });

        JLTexto2.setText("Vuelos comprados pertenecientes a la aerolinea:");

        JTDatosVuelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula piloto", "Nombre piloto", "Cedula Aeromosa", "Nombre aeromosa", "Avion asignado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Arriba_Abajo.setViewportView(JTDatosVuelos);
        if (JTDatosVuelos.getColumnModel().getColumnCount() > 0) {
            JTDatosVuelos.getColumnModel().getColumn(0).setResizable(false);
            JTDatosVuelos.getColumnModel().getColumn(1).setResizable(false);
            JTDatosVuelos.getColumnModel().getColumn(2).setResizable(false);
            JTDatosVuelos.getColumnModel().getColumn(3).setResizable(false);
            JTDatosVuelos.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Arriba_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(JLTexto2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(JLTexto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JCBAeroSeleccionada, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(JLTexto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCBAeroSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JLTexto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Arriba_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBAeroSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBAeroSeleccionadaActionPerformed
        if (!JCBAeroSeleccionada.getSelectedItem().equals("...")) {
            try {

                String aerolineaSelect = (String) JCBAeroSeleccionada.getSelectedItem();
                DatosAero = negocio.VuelosPorAerolinea(aerolineaSelect);

                DefaultTableModel Modelo = (DefaultTableModel) JTDatosVuelos.getModel();
                Modelo.setRowCount(0);

                Cargar_Table();

            } catch (ParseException ex) {
                Logger.getLogger(JDReporte_2.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            DefaultTableModel Modelo = (DefaultTableModel) JTDatosVuelos.getModel();
            Modelo.setRowCount(0);
        }
    }//GEN-LAST:event_JCBAeroSeleccionadaActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDReporte_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDReporte_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDReporte_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDReporte_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDReporte_2 dialog = new JDReporte_2(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> JCBAeroSeleccionada;
    private javax.swing.JLabel JLTexto1;
    private javax.swing.JLabel JLTexto2;
    private javax.swing.JTable JTDatosVuelos;
    // End of variables declaration//GEN-END:variables
}
