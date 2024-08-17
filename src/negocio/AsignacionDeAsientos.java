package negocio;

import database.readingClasses.*;
import database.writingClasses.*;
import interfaz.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AsignacionDeAsientos {

    private String aero;
    private Integer salida_aero;
    private String hora_salida;
    private Integer llegada_aero;
    private String hora_llegada;
    private Integer escala;
    private String precio;
    private String duracion;
    private String cantBoletos;
    private Integer identificador;
    private Integer cedula;
    int filas = 6;
    int columnas = 7;
    private Integer[][] asientosMatriz = new Integer[filas][columnas];
    private String asientosCompradosXY;
    private Inicio_Registro inicio_Registro;
    private HistorialClassR historialClassR;
    private HistorialClassW historialClassW;
    private JFMenuPrincipal jFMenuPrincipal;
    private Integer IDHistorial = 0;
    private boolean asientoEncontrado = false;

    public AsignacionDeAsientos() {
        this.inicio_Registro = new Inicio_Registro();
        this.historialClassW = new HistorialClassW();
        this.historialClassR = new HistorialClassR();
        this.asientosCompradosXY = "";
    }

    public void AsignacionDeAsientos(String listaVueloSeleccionado) throws ParseException {
        System.out.println("Lista: " + listaVueloSeleccionado);

        // Habran exclusivamente 10 partes
        String[] partesVueloSeleccionado = listaVueloSeleccionado.split(",");

        aero = partesVueloSeleccionado[0];
        salida_aero = Integer.parseInt(partesVueloSeleccionado[1]);
        hora_salida = partesVueloSeleccionado[2];
        llegada_aero = Integer.parseInt(partesVueloSeleccionado[3]);
        hora_llegada = partesVueloSeleccionado[4];
        escala = Integer.parseInt(partesVueloSeleccionado[5]);
        precio = partesVueloSeleccionado[6];
        duracion = partesVueloSeleccionado[7];
        cantBoletos = partesVueloSeleccionado[8];
        identificador = Integer.parseInt(partesVueloSeleccionado[9]); // parse para que funcione correctamente con equals
        cedula = Integer.parseInt(partesVueloSeleccionado[10]);

        System.out.println("Aeropuerto de salida: " + aero);
        System.out.println("Salida (hora): " + salida_aero);
        System.out.println("Hora de salida: " + hora_salida);
        System.out.println("Aeropuerto de llegada: " + llegada_aero);
        System.out.println("Hora de llegada: " + hora_llegada);
        System.out.println("Escala: " + escala);
        System.out.println("Precio: " + precio);
        System.out.println("Duracion: " + duracion);
        System.out.println("Cantidad de boletos: " + cantBoletos);
        System.out.println("Identificador: " + identificador);
        System.out.println("Cedula Actual: " + cedula);

        LocalDateTime horaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String fechaHoraFormateada = horaActual.format(formato);

        historialClassR.leerHistorialTxt();
        asignarAsiento(cantBoletos);

        if (asientoEncontrado) {
            historialClassW.EscribirHistorialTxt(identificador, cedula, salida_aero, llegada_aero, escala,
                    fechaHoraFormateada, cantBoletos, asientosCompradosXY, duracion, precio);

            // cada compra es una linea distinta pero con datos actualizados
            // puede que se repita el id del identificador, por ello vamos a agarrar siempre el ultimo, validar eso
        }

    }

    public void asignarAsiento(String cantidadBoletos) {
        // Inicializa la matriz en 0
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientosMatriz[i][j] = 0;
            }
        }

        // Cargar asientos ocupados del historial
        for (int i = 0; i < historialClassR.getID().size(); i++) {
            
            if (historialClassR.getID().get(i).equals(identificador)) {

                if (historialClassR.getCAN_BOLETOS().get(i) == 1) { // 0,0 o si es 0,0 0,1
                    String asientosXY[] = historialClassR.getASIENTOS().get(i).split(",");
                    Integer asientoX = Integer.parseInt(asientosXY[0]);
                    Integer asientoY = Integer.parseInt(asientosXY[1]);
                  
                    asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                }

                if (historialClassR.getCAN_BOLETOS().get(i) >= 2) {
                    String asientosTotales[] = historialClassR.getASIENTOS().get(i).split("-");
                    for (int j = 0; j < asientosTotales.length; j++) {
                        String asientosXY[] = asientosTotales[j].split(",");
                        Integer asientoX = Integer.parseInt(asientosXY[0]);
                        Integer asientoY = Integer.parseInt(asientosXY[1]);
                        asientosMatriz[asientoX][asientoY] = 1;
                    }
                }
            }
        }

        // Llamar al método correspondiente basado en la cantidad de boletos
        switch (cantidadBoletos) {
            case "1" ->
                boleto1();
            case "2" ->
                boleto2();
            case "3" ->
                boleto3();
            case "4" ->
                boleto4();
            case "5" ->
                boleto5();
            default ->
                throw new AssertionError("Cantidad de boletos no soportada: " + cantidadBoletos);
        }

        // Mostrar la matriz de asientos para depuración
        System.out.println("Matriz de asientos:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(asientosMatriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Fin matriz de asiento...");

    }

    public void boleto1() {
        // Asignar un solo asiento
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                if (asientosMatriz[x][y] == 0 && (y != 1 && y != 3 && y != 5)) {
                    asientosMatriz[x][y] = 1;
                    asientosCompradosXY = x + "," + y; // Agregar asiento asignado
                    asientoEncontrado = true;
                    return;
                }
            }
        }
    }

    public void boleto2() {
        // Asignar nuevos asientos para 2 boletos consecutivos en la misma fila
        boolean asientoAsignado = false;

        for (int x = 1; x < filas; x++) {
            for (int y = 0; y < columnas - 1; y++) { // Nos detenemos en la penúltima columna
                // Aseguramos que el par de asientos no se encuentren en la columna deshabilitada
                if ((y != 2 && y != 3 && y != 6) && asientosMatriz[x - 1][y] == 0 && asientosMatriz[x][y] == 0 && asientosMatriz[x][y + 1] == 0) {
                    // Asignar los dos asientos consecutivos
                    asientosMatriz[x][y] = 1;
                    asientosMatriz[x][y + 1] = 1;
                    asientosCompradosXY = x + "," + y + "-" + x + "," + (y + 1); // Agregar los asientos asignados
                    asientoAsignado = true;
                    asientoEncontrado = true;
                    break;
                }
            }
            if (asientoAsignado) {
                break;
            }
        }

    }

    public void boleto3() {
        // Implementar la lógica para asignar 3 boletos
    }

    public void boleto4() {
        // Implementar la lógica para asignar 4 boletos
    }

    public void boleto5() {
        // Implementar la lógica para asignar 5 boletos
    }

    public void guardarCedulaActual(Integer cedulaActual) {
        cedula = cedulaActual;
    }

    public void matriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientosMatriz[i][j] = 0;
            }
        }
    }
}
