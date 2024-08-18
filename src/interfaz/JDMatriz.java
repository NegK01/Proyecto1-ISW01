package interfaz;

import database.readingClasses.HistorialClassR;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import negocio.Matriz;

public class JDMatriz extends javax.swing.JDialog {

    private JLabel[][] labels;
    private JLabel[][] labelsEscala;
    private Integer filas = 6;
    private Integer columnas = 7;
    private HistorialClassR historialClassR = new HistorialClassR();
    private Matriz matrizNegocio = new Matriz();
    private Integer[][] asientosMatriz;
    private Integer[][] asientosMatrizEscala;
    private Integer ultimaCompra;
    private Integer identipart2;

    public JDMatriz(java.awt.Frame parent, boolean modal, Integer[][] asientosMatriz, Integer[][] asientosMatrizEscala) throws ParseException {
        super(parent, modal);
        setLocationRelativeTo(null);
        initComponents();
        jPanelMatriz.setLayout(new GridLayout(filas, columnas));
        jPanelMatrizEscala.setLayout(new GridLayout(filas, columnas));
        this.asientosMatriz = asientosMatriz;
        this.asientosMatrizEscala = asientosMatrizEscala;
        historialClassR.leerHistorialTxt();
        ultimaCompra = historialClassR.getID().size() - 1;
        identipart2 = Integer.valueOf(String.valueOf(historialClassR.getID().get(ultimaCompra)).substring(3, 6));

        jPanelMatriz.setVisible(true);
        jLabel1.setVisible(true);
        jPanelMatrizEscala.setVisible(true);
        labels = new JLabel[filas][columnas];
        labelsEscala = new JLabel[filas][columnas];
        generarMatriz();
        rellenarMatriz();
        jPanelMatriz.setVisible(true);
        jLabel1.setVisible(true);
        jPanelMatrizEscala.setVisible(true);
    }

    public void generarMatriz() throws ParseException {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                labels[i][j] = new JLabel();
                if (j == 3) {
                    labels[i][j].setIcon(new ImageIcon(matrizNegocio.getPasilloPath()));
                } else if (asientosMatriz[i][j] == 0) {
                    labels[i][j].setIcon(new ImageIcon(matrizNegocio.getAsientosDisponiblesPath()));
                } else if (asientosMatriz[i][j] == 1) {
                    // todos los asignados en 1 son rojos, se cambiara a azul despues si coincide con la compra actual
                    labels[i][j].setIcon(new ImageIcon(matrizNegocio.getAsientosOcupadosPath()));
                }
                jPanelMatriz.add(labels[i][j]);
            }
        }

        // siempre vamos a ocupar la primera, pero en caso de que sea compra tambien de una escala
        // entraremos a este for a generar la matriz y añadirla al panel
        if (!identipart2.equals(000)) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    labelsEscala[i][j] = new JLabel();
                    if (j == 3) {
                        labelsEscala[i][j].setIcon(new ImageIcon(matrizNegocio.getPasilloPath()));
                    } else if (asientosMatrizEscala[i][j] == 0) {
                        labelsEscala[i][j].setIcon(new ImageIcon(matrizNegocio.getAsientosDisponiblesPath()));
                    } else if (asientosMatrizEscala[i][j] == 1) {
                        // todos los asignados en 1 son rojos, se cambiara a azul despues si coincide con la compra actual
                        labelsEscala[i][j].setIcon(new ImageIcon(matrizNegocio.getAsientosOcupadosPath()));
                    }
                    jPanelMatrizEscala.add(labelsEscala[i][j]);
                }
            }
        }
    }

    public void rellenarMatriz() throws ParseException {
        if (identipart2.equals(000)) { // vuelo directo, 1 matriz
            if (historialClassR.getCAN_BOLETOS().get(ultimaCompra) == 1) {
                // Compra de 1 boleto, (x,y)
                String asientosXY[] = historialClassR.getASIENTOS().get(ultimaCompra).split(",");
                Integer x = Integer.valueOf(asientosXY[0]);
                Integer y = Integer.valueOf(asientosXY[1]);
                labels[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado

            } else if (historialClassR.getCAN_BOLETOS().get(ultimaCompra) >= 2) {
                // Compra de 2 o mas boletos
                String asientosTotales[] = historialClassR.getASIENTOS().get(ultimaCompra).split("-");
                for (int k = 0; k < asientosTotales.length; k++) {
                    String asientosXY[] = asientosTotales[k].split(",");
                    Integer x = Integer.valueOf(asientosXY[0]);
                    Integer y = Integer.valueOf(asientosXY[1]);
                    labels[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado

                }
            }
        } else { // vuelo con escala, 2 matrices
            String[] Tpartes = historialClassR.getASIENTOS().get(ultimaCompra).split("T");
            String Tparte1 = Tpartes[0];
            String Tparte2 = Tpartes[1];
            if (historialClassR.getCAN_BOLETOS().get(ultimaCompra) == 1) {
                String[] asientosXY = Tparte1.split(",");
                Integer x = Integer.valueOf(asientosXY[0]);
                Integer y = Integer.valueOf(asientosXY[1]);

                labels[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado

                asientosXY = Tparte2.split(",");
                x = Integer.valueOf(asientosXY[0]);
                y = Integer.valueOf(asientosXY[1]);

                labelsEscala[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado
            }
            // Asignacion de asientos comprados (+2 asientos) 
            if (historialClassR.getCAN_BOLETOS().get(ultimaCompra) >= 2) {
                String[] asientosTotales = Tparte1.split("-");
                String[] asientosTotalesEscala = Tparte2.split("-");
                for (int k = 0; k < asientosTotales.length; k++) { // Los asientos totales seran de la misma cantidad en ambos casos
                    String[] asientosXY = asientosTotales[k].split(",");
                    Integer x = Integer.valueOf(asientosXY[0]);
                    Integer y = Integer.valueOf(asientosXY[1]);

                    labels[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado

                    // label ya se lleno con parte 1, ahora toca el 2
                    asientosXY = asientosTotalesEscala[k].split(",");
                    x = Integer.valueOf(asientosXY[0]);
                    y = Integer.valueOf(asientosXY[1]);

                    labelsEscala[x][y].setIcon(new ImageIcon(matrizNegocio.getAsientosCompradosPath())); // Marcar asiento como comprado
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMatriz = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelMatrizEscala = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanelMatrizLayout = new javax.swing.GroupLayout(jPanelMatriz);
        jPanelMatriz.setLayout(jPanelMatrizLayout);
        jPanelMatrizLayout.setHorizontalGroup(
            jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        jPanelMatrizLayout.setVerticalGroup(
            jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanelMatrizEscalaLayout = new javax.swing.GroupLayout(jPanelMatrizEscala);
        jPanelMatrizEscala.setLayout(jPanelMatrizEscalaLayout);
        jPanelMatrizEscalaLayout.setHorizontalGroup(
            jPanelMatrizEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jPanelMatrizEscalaLayout.setVerticalGroup(
            jPanelMatrizEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelMatrizEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMatrizEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JDMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDMatriz dialog = null;
                try {
                    dialog = new JDMatriz(new javax.swing.JFrame(), true, null, null);
                } catch (ParseException ex) {
                    Logger.getLogger(JDMatriz.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMatriz;
    private javax.swing.JPanel jPanelMatrizEscala;
    // End of variables declaration//GEN-END:variables
}
