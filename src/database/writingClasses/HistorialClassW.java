package database.writingClasses;

import database.readingClasses.HistorialClassR;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class HistorialClassW {

    private String TXTSPLIT;
    private String linea;
    private String[] asientosXY;
    private HistorialClassR historialClassR;

    public HistorialClassW() {
        this.TXTSPLIT = ";";
        this.historialClassR = new HistorialClassR();
    }

    public void EscribirHistorialTxt(Integer IDidentificador, Integer cedula, Integer aero_salida,
            Integer aero_llegada, Integer aero_escala, String fechaYHoraCompra, String cantBoletos,
            String asientosComprados, String duracionTotal, String CostoTotal) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/files/Historial.txt", true))) {
            String duracionHM[] = duracionTotal.split(":");
            linea = IDidentificador + TXTSPLIT + cedula + TXTSPLIT + aero_salida + TXTSPLIT
                    + aero_llegada + TXTSPLIT + aero_escala + TXTSPLIT + fechaYHoraCompra + TXTSPLIT
                    + cantBoletos + TXTSPLIT + asientosComprados + TXTSPLIT + duracionHM[0] + TXTSPLIT 
                    + duracionHM[1] + TXTSPLIT + CostoTotal;

            bw.write(linea);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("\u001B[31mERROR:\u001B[0m " + e.getMessage());
        }
    }
}
