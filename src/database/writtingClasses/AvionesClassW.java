package database.writtingClasses;

import database.readingClasses.AvionesClassR;
import java.io.*;

public class AvionesClassW {

    private AvionesClassR avionesClassR;
    private String TXTSPLIT;
    private String linea;

    public AvionesClassW() {
        avionesClassR = new AvionesClassR();
        avionesClassR.LeerAvionesTxt();
        TXTSPLIT = ",";
    }

    public void EscribirAvionesTxt(Integer ID_Avion, String Modelo, Integer IDS_Aero, Integer Estado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/dataBase/files/Aviones.txt"))) {

            for (int i = 0; i < avionesClassR.getIDS().size(); i++) {

                if (avionesClassR.getIDS().get(i).equals(ID_Avion)) {
                    linea = ID_Avion + TXTSPLIT +
                            Modelo + TXTSPLIT +
                            IDS_Aero + TXTSPLIT +
                            Estado;
                } else {
                    linea = avionesClassR.getIDS().get(i) + TXTSPLIT
                            + avionesClassR.getMODELO().get(i) + TXTSPLIT
                            + avionesClassR.getIDS_AERO().get(i) + TXTSPLIT
                            + avionesClassR.getESTADO().get(i);
                }

                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
}
