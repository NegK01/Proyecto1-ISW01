package interfaz;

import database.readingClasses.HistorialClassR;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import negocio.Reporte_3;
import java.util.HashSet;

public class JDReporte_3 extends javax.swing.JDialog {

    private ArrayList Compras = new ArrayList();
    
    Reporte_3 negocio = new Reporte_3();

    public JDReporte_3(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

        Cargar_ComboBox();
    }

    public void Cargar_ComboBox() throws ParseException {
        HistorialClassR historial = new HistorialClassR();
        historial.leerHistorialTxt();
        
        HashSet<Integer> filtrarCedulas = new HashSet<>();
        
        for (Integer item : historial.getCEDULA()) {
            filtrarCedulas.add(item);
        }

        for (Integer cedula : filtrarCedulas) {
            JCBCedulaSelect.addItem(cedula.toString());
        }
    }
    
    public void cargar_Compras() {
        DefaultTableModel Modelo = (DefaultTableModel) JTComprasPasajero.getModel();

        Modelo.setRowCount(0);

        for (int i = 0; i < Compras.size(); i++) {

            ArrayList Compra = new ArrayList();
            Compra = (ArrayList) Compras.get(i);

            Modelo.addRow(new Object[]{Compra.get(0), Compra.get(1), Compra.get(2),Compra.get(3),
                Compra.get(4), Compra.get(5),Compra.get(6),Compra.get(7),Compra.get(8)});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTexto1 = new javax.swing.JLabel();
        JCBCedulaSelect = new javax.swing.JComboBox<>();
        JLTexto2 = new javax.swing.JLabel();
        Arriba_Abajo = new javax.swing.JScrollPane();
        JTComprasPasajero = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLTexto1.setText("Cedula del cliente a buscar:");

        JCBCedulaSelect.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        JCBCedulaSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        JCBCedulaSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBCedulaSelectActionPerformed(evt);
            }
        });

        JLTexto2.setText("Compras realizadas por el cliente:");

        JTComprasPasajero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Salida", "Escala", "Llegada", "Boletos", "Asientos", "Duracion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTComprasPasajero.getTableHeader().setReorderingAllowed(false);
        Arriba_Abajo.setViewportView(JTComprasPasajero);
        if (JTComprasPasajero.getColumnModel().getColumnCount() > 0) {
            JTComprasPasajero.getColumnModel().getColumn(0).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(0).setPreferredWidth(30);
            JTComprasPasajero.getColumnModel().getColumn(1).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(1).setPreferredWidth(40);
            JTComprasPasajero.getColumnModel().getColumn(2).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(2).setPreferredWidth(80);
            JTComprasPasajero.getColumnModel().getColumn(3).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(3).setPreferredWidth(80);
            JTComprasPasajero.getColumnModel().getColumn(4).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(4).setPreferredWidth(80);
            JTComprasPasajero.getColumnModel().getColumn(5).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(5).setPreferredWidth(20);
            JTComprasPasajero.getColumnModel().getColumn(6).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(6).setPreferredWidth(151);
            JTComprasPasajero.getColumnModel().getColumn(7).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(7).setPreferredWidth(20);
            JTComprasPasajero.getColumnModel().getColumn(8).setResizable(false);
            JTComprasPasajero.getColumnModel().getColumn(8).setPreferredWidth(15);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JCBCedulaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(JLTexto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLTexto2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Arriba_Abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(JLTexto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCBCedulaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(JLTexto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Arriba_Abajo, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBCedulaSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBCedulaSelectActionPerformed
        if (!JCBCedulaSelect.getSelectedItem().equals("...")) {
            
            try {
                String cedulaStr = JCBCedulaSelect.getSelectedItem().toString();
                Integer cedula = Integer.parseInt(cedulaStr);
                Compras = negocio. BuscarPorUsuario(cedula);
                cargar_Compras();
            } catch (ParseException ex) {
                Logger.getLogger(JDReporte_3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            DefaultTableModel Modelo = (DefaultTableModel) JTComprasPasajero.getModel();
            Modelo.setRowCount(0);
        }
    }//GEN-LAST:event_JCBCedulaSelectActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDReporte_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDReporte_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDReporte_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDReporte_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDReporte_3 dialog = null;
                try {
                    dialog = new JDReporte_3(new javax.swing.JFrame(), true);
                } catch (ParseException ex) {
                    Logger.getLogger(JDReporte_3.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JComboBox<String> JCBCedulaSelect;
    private javax.swing.JLabel JLTexto1;
    private javax.swing.JLabel JLTexto2;
    private javax.swing.JTable JTComprasPasajero;
    // End of variables declaration//GEN-END:variables
}
