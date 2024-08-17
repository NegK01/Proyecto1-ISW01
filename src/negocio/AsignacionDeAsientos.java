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
    private Integer[][] asientosMatrizEscala = new Integer[filas][columnas];
    private String asientosCompradosXY;
    private String asientosCompradosXYEscala;
    private Inicio_Registro inicio_Registro;
    private HistorialClassR historialClassR;
    private HistorialClassW historialClassW;
    private JFMenuPrincipal jFMenuPrincipal;
    private VuelosClassR vuelosClassR;
    private Integer IDHistorial = 0;
    private boolean asientoEncontrado = false;
    private boolean isEscala = false;

    public AsignacionDeAsientos() {
        this.inicio_Registro = new Inicio_Registro();
        this.historialClassW = new HistorialClassW();
        this.historialClassR = new HistorialClassR();
        this.vuelosClassR = new VuelosClassR();
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
        vuelosClassR.LeerVuelosTxt();

        String identiParte1 = String.valueOf(identificador).substring(0, 3); // Los primeros 3 digitos
        String identiParte2 = String.valueOf(identificador).substring(3, 6); // Los siguientes 3 digitos
        System.out.println(identiParte1);
        System.out.println(identiParte2);
        // dividir en dos partes y ver en historial si ya se hiz compras con ese id
        // si es asi se llena la matriz
        asignarAsiento(cantBoletos, Integer.parseInt(identiParte1), Integer.parseInt(identiParte2));

        if (asientoEncontrado) {
            if (isEscala) {
                historialClassW.EscribirHistorialTxt(identificador, cedula, salida_aero, llegada_aero, escala,
                        fechaHoraFormateada, cantBoletos, asientosCompradosXY + "T" + asientosCompradosXYEscala, duracion, precio);
            } else {
                historialClassW.EscribirHistorialTxt(identificador, cedula, salida_aero, llegada_aero, escala,
                        fechaHoraFormateada, cantBoletos, asientosCompradosXY, duracion, precio);
            }

            // cada compra es una linea distinta pero con datos actualizados
            // puede que se repita el id del identificador, por ello vamos a agarrar siempre el ultimo, validar eso
        }

        matriz();
    }

    public void asignarAsiento(String cantidadBoletos, Integer identiPart1, Integer identiPart2) {
        // Inicializa la matriz en 0
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientosMatriz[i][j] = 0;
                asientosMatrizEscala[i][j] = 0;
            }
        }

        // Cargar asientos ocupados del historial
//        for (int i = 0; i < historialClassR.getID().size(); i++) {
        /* 
            Identificador actual 101000
            verifica que el iterador del momento sea igual a 101000 si es asi, ya encontro la primera compra de asiento
            se llena ese asiento comprado y sigue buscando todos los demas que sean 101000
         */
        // -- Si identificador termina en 000 / 101000 identificador
        if (identiPart2.equals(000)) {
            isEscala = false;
            // Verificar si los 3 primeros digitos de 101000 (101) de vuelo directo, tiene asientos comprados
            // en escalas de 101102 o 102101, mas graficamente 101xxx o xxx101, es idependiente de lo que haya en las x
            for (int j = 0; j < historialClassR.getID().size(); j++) { // ciclo del tamaño de la lista completa
                String IDIteracion = String.valueOf(historialClassR.getID().get(j)); // Tomamos cada una de las iteraciones para dividirlo 
                Integer IDIteraPart1 = Integer.valueOf(String.valueOf(IDIteracion).substring(0, 3)); // Los primeros 3 digitos
                Integer IDIteraPart2 = Integer.valueOf(String.valueOf(IDIteracion).substring(3, 6)); // Los siguientes 3 digitos

                //Puede ser la primera salida para ir a una escala 101102 / 101xxx
                // Buscamos todos los que inician con 101
                // --- Ver si la parte 1 del iterador actual es igual a 101, si es asi, agarramos el primer T si es de escala
                if (IDIteraPart1.equals(identiPart1)) { // if 101-XXX
                    /*
                        Una vez validado que sea igual, significa que entrataran todos los que inician con 101
                     */
                    // if 101-000
                    if (IDIteraPart2.equals(000)) { // Si termina en 000, se trata de un vuelo directo, cual no tiene T, no hace falta dividir
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String asientosXY[] = historialClassR.getASIENTOS().get(j).split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);

                            asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado

                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = historialClassR.getASIENTOS().get(j).split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String asientosXY[] = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatriz[asientoX][asientoY] = 1;

                            }
                        }
                    }
                    // if 101-102
                    if (!IDIteraPart2.equals(000)) { // Si no termina en 000, se trata de una escala, hay que usar T
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte1 = Tpartes[0];
                        String Tparte2 = Tpartes[1];
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String asientosXY[] = Tparte1.split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);

                            asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado

                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = Tparte1.split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String asientosXY[] = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatriz[asientoX][asientoY] = 1;

                            }
                        }
                    }

                    //O directamente la salida de la escala 102101 / xxx101
                    // Buscamos todos los que terminan con 101
                    //
                    // --- Ver si la parte 2 del iterador actual es igual a 101, si es asi, agarramos el segundo T si es de escala
                    // if 102-101
                } else if (IDIteraPart2.equals(identiPart1)) { // if (xxx-)101 == 101
                    //Ya añadimos todos los de 101XXX, independientemente si XXX es 000 o 101
                    //falta agregar todos los de XXX101

                    if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte1 = Tpartes[0];
                        String Tparte2 = Tpartes[1];
                        String asientosXY[] = Tparte2.split(",");
                        Integer asientoX = Integer.valueOf(asientosXY[0]);
                        Integer asientoY = Integer.valueOf(asientosXY[1]);

                        asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado

                    }

                    if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte1 = Tpartes[0];
                        String Tparte2 = Tpartes[1];
                        String asientosTotales[] = Tparte2.split("-");
                        for (int k = 0; k < asientosTotales.length; k++) {
                            String asientosXY[] = asientosTotales[k].split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatriz[asientoX][asientoY] = 1;

                        }
                    }
                }
            }
        } else if (!identiPart2.equals(000)) { // 101102 identificador / Esto indica que la compra es de una escala
            /* 
                Para la escala, debemos de llenar todas las de escalas 101-102
                
            
            
            Si es una escala, llenar la matriz 1 con todos los asientos
            luego llenar la matriz 2 con todos los asientos
             */
            isEscala = true;
            for (int j = 0; j < historialClassR.getID().size(); j++) { // ciclo del tamaño de la lista completa
                String IDIteracion = String.valueOf(historialClassR.getID().get(j)); // Tomamos cada una de las iteraciones para dividirlo 
                Integer IDIteraPart1 = Integer.valueOf(String.valueOf(IDIteracion).substring(0, 3)); // Los primeros 3 digitos
                Integer IDIteraPart2 = Integer.valueOf(String.valueOf(IDIteracion).substring(3, 6)); // Los siguientes 3 digitos

                // --- Ver si la parte 1 del iterador actual es igual a 101, si es asi, agarramos el primer T si es de escala
                if (IDIteraPart1.equals(identiPart1)) {

                    if (IDIteraPart2.equals(000)) { // Si termina en 000, se trata de un vuelo directo, cual no tiene T, no hace falta dividir
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String asientosXY[] = historialClassR.getASIENTOS().get(j).split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = historialClassR.getASIENTOS().get(j).split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String asientosXY[] = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatriz[asientoX][asientoY] = 1;
                            }
                        }
                    }

                    if (!IDIteraPart2.equals(000)) { // Si no termina en 000, se trata de una escala, hay que usar T
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte1 = Tpartes[0];
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String[] asientosXY = Tparte1.split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = Tparte1.split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String[] asientosXY = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatriz[asientoX][asientoY] = 1;
                            }
                        }
                    }
                }
                //mdd
                if (IDIteraPart1.equals(identiPart2)) { // Si es el dos, vamos a guardar la matriz de escala

                    if (IDIteraPart2.equals(000)) { // Si termina en 000, se trata de un vuelo directo, cual no tiene T, no hace falta dividir
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String asientosXY[] = historialClassR.getASIENTOS().get(j).split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatrizEscala[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = historialClassR.getASIENTOS().get(j).split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String asientosXY[] = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatrizEscala[asientoX][asientoY] = 1;
                            }
                        }
                    }

                    if (!IDIteraPart2.equals(000)) { // Si no termina en 000, se trata de una escala, hay que usar T
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte1 = Tpartes[0];
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String[] asientosXY = Tparte1.split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatrizEscala[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = Tparte1.split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String[] asientosXY = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatrizEscala[asientoX][asientoY] = 1;
                            }
                        }
                    }
                }//mdd
                if (IDIteraPart2.equals(identiPart1)) {

                    if (IDIteraPart2.equals(identiPart1)) { // Si no termina en 000, se trata de una escala, hay que usar T
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte2 = Tpartes[1];
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String[] asientosXY = Tparte2.split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatriz[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = Tparte2.split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String[] asientosXY = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatriz[asientoX][asientoY] = 1;
                            }
                        }
                    }
                }//mdd 
                if (IDIteraPart2.equals(identiPart2)) {

                    if (IDIteraPart2.equals(identiPart2)) { // Si no termina en 000, se trata de una escala, hay que usar T
                        String[] Tpartes = historialClassR.getASIENTOS().get(j).split("T");
                        String Tparte2 = Tpartes[1];
                        if (historialClassR.getCAN_BOLETOS().get(j) == 1) {
                            String[] asientosXY = Tparte2.split(",");
                            Integer asientoX = Integer.valueOf(asientosXY[0]);
                            Integer asientoY = Integer.valueOf(asientosXY[1]);
                            asientosMatrizEscala[asientoX][asientoY] = 1; // Marcar asiento como ocupado
                        }
                        // Asignacion de asientos ocupados (+2 asientos) 
                        if (historialClassR.getCAN_BOLETOS().get(j) >= 2) {
                            String asientosTotales[] = Tparte2.split("-");
                            for (int k = 0; k < asientosTotales.length; k++) {
                                String[] asientosXY = asientosTotales[k].split(",");
                                Integer asientoX = Integer.valueOf(asientosXY[0]);
                                Integer asientoY = Integer.valueOf(asientosXY[1]);
                                asientosMatrizEscala[asientoX][asientoY] = 1;
                            }
                        }
                    }
                }
            }

        }

//            }
//      }
        asientoEncontrado = false;
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

//         Mostrar la matriz de asientos para depuración
//        System.out.println("\u001B[36mMatriz principal de asientos:\u001B[0m");
//
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                System.out.print(asientosMatriz[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("\u001B[36mFin matriz principal de asiento...\u001B[0m");
//        
//        System.out.println("\u001B[32mMatriz escala de asientos:\u001B[0m");
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                System.out.print(asientosMatrizEscala[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("\u001B[32mFin matriz escala de asiento...\u001B[0m");
    }

    public void boleto1() {
        // Asignar un solo asiento
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {

                if (asientosMatriz[x][y] == 0 && (y != 3)) { // Siempre y cuando el campo este vacio validamos
                    if ((y == 0) && (asientosMatriz[x][y + 1] == 0)) {
                        asientosCompradosXY = x + "," + y; // Asignar asiento
                        asientoEncontrado = true;
                    } else if ((y < 6) && (asientosMatriz[x][y - 1] == 0) && (asientosMatriz[x][y + 1] == 0)) {
                        // Si es menor nos permite hacer un +1
                        // 6 es el maximo de Y
                        asientosCompradosXY = x + "," + y; // Agregar asiento asignado
                        asientoEncontrado = true;
                        // Si esta en un punto en el que no podemos usar +1, eso quiere decir que ya es el tope
                        // entonces solo valdiamos que atras este libre para cumplir con la regla
                    } else if ((y == 6) && (asientosMatriz[x][y - 1] == 0)) {
                        asientosCompradosXY = x + "," + y; // Agregar asiento asignado
                        asientoEncontrado = true;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
            if (asientoEncontrado) {
                break;
            }
        }

        // validamos si se encontraron asientos anteriormente y si la compra es de una escala
        if (isEscala && asientoEncontrado) {
            // ponemos false para ver si cambia a true, si lo hace, quiere decir que si se pudo asignar
            // asiento en la segunda matriz
            asientoEncontrado = false;

            for (int x1 = 0; x1 < filas; x1++) {
                for (int y1 = 0; y1 < columnas; y1++) {

                    if (asientosMatrizEscala[x1][y1] == 0 && (y1 != 3)) { // Siempre y cuando el campo este vacio validamos
                        if ((y1 == 0) && (asientosMatrizEscala[x1][y1 + 1] == 0)) {
                            asientosCompradosXYEscala = x1 + "," + y1; // Agregar asiento asignado
                            asientoEncontrado = true;
                        } else if ((y1 < 6) && (asientosMatrizEscala[x1][y1 - 1] == 0) && (asientosMatrizEscala[x1][y1 + 1] == 0)) { // 6 es el maximo de Y
                            // Si es menor nos permite hacer un +1
                            asientosCompradosXYEscala = x1 + "," + y1; // Agregar asiento asignado
                            asientoEncontrado = true;

                            // Si esta en un punto en el que no podemos usar +1, eso quiere decir que ya es el tope
                            // entonces solo valdiamos que atras este libre para cumplir con la regla
                        } else if ((y1 == 6) && (asientosMatrizEscala[x1][6 - 1] == 0)) {
                            asientosCompradosXYEscala = x1 + "," + y1; // Agregar asiento asignado
                            asientoEncontrado = true;
                        }
                    }
                    if (asientoEncontrado) {
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }

        // Siempre se va asignar matriz principal si se encuentra asiento
        // luego valida si el vuelo es de escala, si es asi, quiere decir que 
        // el asiento si fue asignado y si es de escala
        if (asientoEncontrado) {
            // Entre las opciones de arriba, solo se podra ejecutar 1, por tanto, no hay problema se usamos este boolean
            String[] asientosXY = asientosCompradosXY.split(","); // Solo es la compra de 1 boleto, por tanto (x,y)
            Integer asientoX = Integer.valueOf(asientosXY[0]); // (X,y)
            Integer asientoY = Integer.valueOf(asientosXY[1]); // (x,Y)
            asientosMatriz[asientoX][asientoY] = 1;

            if (isEscala) {
                String[] asientosXYEscala = asientosCompradosXYEscala.split(",");
                Integer asientoXEscala = Integer.valueOf(asientosXYEscala[0]);
                Integer asientoYEscala = Integer.valueOf(asientosXYEscala[1]);
                asientosMatrizEscala[asientoXEscala][asientoYEscala] = 1;
            }
        } else {
            System.out.println("\u001B[31mERROR:\u001B[0m " + " no hay asientos disponibles.");
        }
    }

    public void boleto2() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas - 1; y++) {

                if (asientosMatriz[x][y] == 0 && ((y != 3) && (y != 2) && (y != 6))) {
                    if ((y == 0) && (asientosMatriz[x][y + 1] == 0)) {
                        asientosMatriz[x][0] = 1;
                        asientosMatriz[x][0 + 1] = 1;
                        Integer posiciony1 = y + 1;
                        asientosCompradosXY = x + "," + y + "-" + x + "," + posiciony1; // Agregar asiento asignado
                        asientoEncontrado = true;
                    } else if ((y < 5) && (asientosMatriz[x][y - 1] == 0) && (asientosMatriz[x][y + 1] == 0) && (asientosMatriz[x][y + 2] == 0)) { // 4 + 2 = 6, 6 es el maximo, como son 2 boletos van continuos
                        asientosMatriz[x][y] = 1;
                        asientosMatriz[x][y + 1] = 1;
                        Integer posiciony1 = y + 1;
                        asientosCompradosXY = x + "," + y + "-" + x + "," + posiciony1; // Agregar asiento asignado
                        asientoEncontrado = true;
                    } else if ((y == 5) && (asientosMatriz[x][y - 1] == 0) && (asientosMatriz[x][y + 1] == 0)) {
                        asientosMatriz[x][y] = 1;
                        asientosMatriz[x][y + 1] = 1;
                        Integer posiciony1 = y + 1;
                        asientosCompradosXY = x + "," + y + "-" + x + "," + posiciony1; // Agregar asiento asignado
                        asientoEncontrado = true;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
            if (asientoEncontrado) {
                break;
            }
        }

        if (isEscala && asientoEncontrado) {
            // ponemos false para ver si cambia a true, si lo hace, quiere decir que si se pudo asignar
            // asiento en la segunda matriz
            asientoEncontrado = false;

            for (int x1 = 0; x1 < filas; x1++) {
                for (int y1 = 0; y1 < columnas - 1; y1++) {

                    if (asientosMatrizEscala[x1][y1] == 0 && ((y1 != 3) && (y1 != 2) && (y1 != 6))) {
                        if ((y1 == 0) && (asientosMatrizEscala[x1][y1 + 1] == 0)) {
                            asientosMatrizEscala[x1][0] = 1;
                            asientosMatrizEscala[x1][0 + 1] = 1;
                            Integer posiciony1 = y1 + 1;
                            asientosCompradosXYEscala = x1 + "," + y1 + "-" + x1 + "," + posiciony1; // Agregar asiento asignado
                            asientoEncontrado = true;
                        } else if ((y1 < 5) && (asientosMatrizEscala[x1][y1 - 1] == 0) && (asientosMatrizEscala[x1][y1 + 1] == 0) && (asientosMatrizEscala[x1][y1 + 2] == 0)) { // 4 + 2 = 6, 6 es el maximo, como son 2 boletos van continuos
                            asientosMatrizEscala[x1][y1] = 1;
                            asientosMatrizEscala[x1][y1 + 1] = 1;
                            Integer posiciony1 = y1 + 1;
                            asientosCompradosXYEscala = x1 + "," + y1 + "-" + x1 + "," + posiciony1; // Agregar asiento asignado
                            asientoEncontrado = true;
                        } else if ((y1 == 5) && (asientosMatrizEscala[x1][y1 - 1] == 0) && (asientosMatrizEscala[x1][y1 + 1] == 0)) {
                            asientosMatrizEscala[x1][y1] = 1;
                            asientosMatrizEscala[x1][y1 + 1] = 1;
                            Integer posiciony1 = y1 + 1;
                            asientosCompradosXYEscala = x1 + "," + y1 + "-" + x1 + "," + posiciony1; // Agregar asiento asignado
                            asientoEncontrado = true;
                        }
                    }
                    if (asientoEncontrado) {
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }

        // Siempre se va asignar matriz principal si se encuentra asiento
        // luego valida si el vuelo es de escala, si es asi, quiere decir que 
        // el asiento si fue asignado y si es de escala
        if (asientoEncontrado) {
            // Entre las opciones de arriba, solo se podra ejecutar 1, por tanto, no hay problema se usamos este boolean
            String[] asientosXY = asientosCompradosXY.split("-"); // Es la compra de varios boletos, (x,y)-(x,y)
            for (int i = 0; i < asientosXY.length; i++) {
                String[] asientosXY1 = asientosXY[i].split(",");
                Integer asientoX = Integer.valueOf(asientosXY1[0]); // (X,y)
                Integer asientoY = Integer.valueOf(asientosXY1[1]); // (x,Y)
                asientosMatriz[asientoX][asientoY] = 1;
            }
            if (isEscala) {
                String[] asientosXYEscala = asientosCompradosXYEscala.split("-");
                for (int i = 0; i < asientosXYEscala.length; i++) {
                    String[] asientosXYEscala1 = asientosXYEscala[i].split(",");
                    Integer asientoXEscala = Integer.valueOf(asientosXYEscala1[0]);
                    Integer asientoYEscala = Integer.valueOf(asientosXYEscala1[1]);
                    asientosMatrizEscala[asientoXEscala][asientoYEscala] = 1;
                }
            }
        } else {
            System.out.println("\u001B[31mERROR:\u001B[0m " + " no hay asientos disponibles.");
        }
    }

    public void boleto3() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas - 1; y++) {
                if ((y == 0 || y == 4)) {
                    if (asientosMatriz[x][y] == 0) {
                        if ((y == 0) && (asientosMatriz[x][y + 1] == 0) && (asientosMatriz[x][y + 2] == 0)
                                && (asientosMatriz[x][y + 3] == 0)) {
                            asientosMatriz[x][0] = 1;
                            asientosMatriz[x][0 + 1] = 1;
                            asientosMatriz[x][0 + 2] = 1;
                            Integer posiciony1 = y + 1;
                            Integer posiciony2 = y + 2;
                            asientosCompradosXY = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2); // Agregar asiento asignado
                            asientoEncontrado = true;
                        } else if ((y == 4) && (asientosMatriz[x][y - 1] == 0) && (asientosMatriz[x][y + 1] == 0)
                                && (asientosMatriz[x][y + 2] == 0)) {
                            asientosMatriz[x][4] = 1;
                            asientosMatriz[x][4 + 1] = 1;
                            asientosMatriz[x][4 + 2] = 1;
                            Integer posiciony1 = y + 1;
                            Integer posiciony2 = y + 2;
                            asientosCompradosXY = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2); // Agregar asiento asignado
                            asientoEncontrado = true;
                        }
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
            if (asientoEncontrado) {
                break;
            }
        }

        if (isEscala && asientoEncontrado) {
            // ponemos false para ver si cambia a true, si lo hace, quiere decir que si se pudo asignar
            // asiento en la segunda matriz
            asientoEncontrado = false;

            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas - 1; y++) {
                    if ((y == 0 || y == 4)) {
                        if (asientosMatrizEscala[x][y] == 0) {
                            if ((y == 0) && (asientosMatrizEscala[x][y + 1] == 0) && (asientosMatrizEscala[x][y + 2] == 0)
                                    && (asientosMatrizEscala[x][y + 3] == 0)) {
                                asientosMatrizEscala[x][y] = 1;
                                asientosMatrizEscala[x][y + 1] = 1;
                                asientosMatrizEscala[x][y + 2] = 1;
                                Integer posiciony1 = y + 1;
                                Integer posiciony2 = y + 2;
                                asientosCompradosXYEscala = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2); // Agregar asiento asignado
                                asientoEncontrado = true;
                            } else if ((y == 4) && (asientosMatrizEscala[x][y - 1] == 0) && (asientosMatrizEscala[x][y + 1] == 0)
                                    && (asientosMatrizEscala[x][y + 2] == 0)) {
                                asientosMatrizEscala[x][4] = 1;
                                asientosMatrizEscala[x][4 + 1] = 1;
                                asientosMatrizEscala[x][4 + 2] = 1;
                                Integer posiciony1 = y + 1;
                                Integer posiciony2 = y + 2;
                                asientosCompradosXYEscala = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2); // Agregar asiento asignado
                                asientoEncontrado = true;
                            }
                        }
                    }
                    if (asientoEncontrado) {
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }

        // Siempre se va asignar matriz principal si se encuentra asiento
        // luego valida si el vuelo es de escala, si es asi, quiere decir que 
        // el asiento si fue asignado y si es de escala
        if (asientoEncontrado) {
            // Entre las opciones de arriba, solo se podra ejecutar 1, por tanto, no hay problema se usamos este boolean
            String[] asientosXY = asientosCompradosXY.split("-"); // Es la compra de varios boletos, (x,y)-(x,y)-(x,y)
            for (int i = 0; i < asientosXY.length; i++) {
                String[] asientosXY1 = asientosXY[i].split(",");
                Integer asientoX = Integer.valueOf(asientosXY1[0]); // (X,y)
                Integer asientoY = Integer.valueOf(asientosXY1[1]); // (x,Y)
                asientosMatriz[asientoX][asientoY] = 1;
            }
            if (isEscala) {
                String[] asientosXYEscala = asientosCompradosXYEscala.split("-");
                for (int i = 0; i < asientosXYEscala.length; i++) {
                    String[] asientosXYEscala1 = asientosXYEscala[i].split(",");
                    Integer asientoXEscala = Integer.valueOf(asientosXYEscala1[0]);
                    Integer asientoYEscala = Integer.valueOf(asientosXYEscala1[1]);
                    asientosMatrizEscala[asientoXEscala][asientoYEscala] = 1;
                }
            }
        } else {
            System.out.println("\u001B[31mERROR:\u001B[0m " + " no hay asientos disponibles.");
        }
    }

    public void boleto4() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas - 1; y++) {
                if ((y == 0 || y == 2)) {
                    if (asientosMatriz[x][y] == 0) {
                        if ((y == 0) && (asientosMatriz[x][y + 1] == 0) && (asientosMatriz[x][y + 2] == 0)
                                && (asientosMatriz[x][y + 3] == 0) && (asientosMatriz[x][y + 4] == 0)
                                && (asientosMatriz[x][y + 5] == 0)) {
                            asientosMatriz[x][0] = 1;
                            asientosMatriz[x][0 + 1] = 1;
                            asientosMatriz[x][0 + 2] = 1; // 3 es la columna que no se toca puesto que es el pasadillo
                            asientosMatriz[x][0 + 4] = 1;
                            Integer posiciony1 = y + 1;
                            Integer posiciony2 = y + 2;
                            Integer posiciony4 = y + 4;
                            asientosCompradosXY = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2) + "-" + (x + "," + posiciony4); // Agregar asiento asignado
                            asientoEncontrado = true;
                        } else if ((y == 2) && (asientosMatriz[x][y - 1] == 0) && (asientosMatriz[x][y + 1] == 0)
                                && (asientosMatriz[x][y + 2] == 0) && (asientosMatriz[x][y + 3] == 0)
                                && (asientosMatriz[x][y + 4] == 0)) {
                            asientosMatriz[x][2] = 1; // No hay 3 pq no
                            asientosMatriz[x][2 + 2] = 1;
                            asientosMatriz[x][2 + 3] = 1;
                            asientosMatriz[x][2 + 4] = 1;
                            Integer posiciony1 = y + 2;
                            Integer posiciony3 = y + 3;
                            Integer posiciony4 = y + 4;
                            asientosCompradosXY = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony3) + "-" + (x + "," + posiciony4); // Agregar asiento asignado
                            asientoEncontrado = true;
                        }
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
            if (asientoEncontrado) {
                break;
            }
        }

        if (isEscala && asientoEncontrado) {
            // ponemos false para ver si cambia a true, si lo hace, quiere decir que si se pudo asignar
            // asiento en la segunda matriz
            asientoEncontrado = false;

            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas - 1; y++) {
                    if ((y == 0 || y == 2)) {
                        if (asientosMatrizEscala[x][y] == 0) {
                            if ((y == 0) && (asientosMatrizEscala[x][y + 1] == 0) && (asientosMatrizEscala[x][y + 2] == 0)
                                    && (asientosMatrizEscala[x][y + 3] == 0) && (asientosMatrizEscala[x][y + 4] == 0)
                                    && (asientosMatrizEscala[x][y + 5] == 0)) {
                                asientosMatrizEscala[x][0] = 1;
                                asientosMatrizEscala[x][0 + 1] = 1;
                                asientosMatrizEscala[x][0 + 2] = 1; // 3 es la columna que no se toca puesto que es el pasadillo
                                asientosMatrizEscala[x][0 + 4] = 1;
                                Integer posiciony1 = y + 1;
                                Integer posiciony2 = y + 2;
                                Integer posiciony4 = y + 4;
                                asientosCompradosXYEscala = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2) + "-" + (x + "," + posiciony4); // Agregar asiento asignado
                                asientoEncontrado = true;
                            } else if ((y == 2) && (asientosMatrizEscala[x][y - 1] == 0) && (asientosMatrizEscala[x][y + 1] == 0)
                                    && (asientosMatrizEscala[x][y + 2] == 0) && (asientosMatrizEscala[x][y + 3] == 0)
                                    && (asientosMatrizEscala[x][y + 4] == 0)) {
                                asientosMatrizEscala[x][2] = 1; // No hay 3 pq no
                                asientosMatrizEscala[x][2 + 2] = 1;
                                asientosMatrizEscala[x][2 + 3] = 1;
                                asientosMatrizEscala[x][2 + 4] = 1;
                                Integer posiciony1 = y + 2;
                                Integer posiciony3 = y + 3;
                                Integer posiciony4 = y + 4;
                                asientosCompradosXYEscala = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony3) + "-" + (x + "," + posiciony4); // Agregar asiento asignado
                                asientoEncontrado = true;
                            }
                        }
                    }
                    if (asientoEncontrado) {
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }

        // Siempre se va asignar matriz principal si se encuentra asiento
        // luego valida si el vuelo es de escala, si es asi, quiere decir que 
        // el asiento si fue asignado y si es de escala
        if (asientoEncontrado) {
            // Entre las opciones de arriba, solo se podra ejecutar 1, por tanto, no hay problema se usamos este boolean
            String[] asientosXY = asientosCompradosXY.split("-"); // Es la compra de varios boletos, (x,y)-(x,y)-(x,y)-(x,y)
            for (int i = 0; i < asientosXY.length; i++) {
                String[] asientosXY1 = asientosXY[i].split(",");
                Integer asientoX = Integer.valueOf(asientosXY1[0]); // (X,y)
                Integer asientoY = Integer.valueOf(asientosXY1[1]); // (x,Y)
                asientosMatriz[asientoX][asientoY] = 1;
            }
            if (isEscala) {
                String[] asientosXYEscala = asientosCompradosXYEscala.split("-");
                for (int i = 0; i < asientosXYEscala.length; i++) {
                    String[] asientosXYEscala1 = asientosXYEscala[i].split(",");
                    Integer asientoXEscala = Integer.valueOf(asientosXYEscala1[0]);
                    Integer asientoYEscala = Integer.valueOf(asientosXYEscala1[1]);
                    asientosMatrizEscala[asientoXEscala][asientoYEscala] = 1;
                }
            }
        } else {
            System.out.println("\u001B[31mERROR:\u001B[0m " + " no hay asientos disponibles.");
        }
    }

    public void boleto5() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas - 1; y++) {
                if (y == 0) {
                    if (asientosMatriz[x][y] == 0) {
                        if ((y == 0) && (asientosMatriz[x][y + 1] == 0) && (asientosMatriz[x][y + 2] == 0)
                                && (asientosMatriz[x][y + 3] == 0) && (asientosMatriz[x][y + 4] == 0)
                                && (asientosMatriz[x][y + 5] == 0) && (asientosMatriz[x][y + 6] == 0)) {
                            asientosMatriz[x][0] = 1;
                            asientosMatriz[x][0 + 1] = 1;
                            asientosMatriz[x][0 + 2] = 1; // 3 es la columna que no se toca puesto que es el pasadillo
                            asientosMatriz[x][0 + 4] = 1;
                            asientosMatriz[x][0 + 5] = 1;
                            Integer posiciony1 = y + 1;
                            Integer posiciony2 = y + 2;
                            Integer posiciony4 = y + 4;
                            Integer posiciony5 = y + 5;
                            asientosCompradosXY = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2) + "-" + (x + "," + posiciony4) + "-" + (x + "," + posiciony5); // Agregar asiento asignado
                            asientoEncontrado = true;
                        }
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
            if (asientoEncontrado) {
                break;
            }
        }

        if (isEscala && asientoEncontrado) {
            // ponemos false para ver si cambia a true, si lo hace, quiere decir que si se pudo asignar
            // asiento en la segunda matriz
            asientoEncontrado = false;

            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas - 1; y++) {
                    if (y == 0) {
                        if (asientosMatrizEscala[x][y] == 0) {
                            if ((y == 0) && (asientosMatrizEscala[x][y + 1] == 0) && (asientosMatrizEscala[x][y + 2] == 0)
                                    && (asientosMatrizEscala[x][y + 3] == 0) && (asientosMatrizEscala[x][y + 4] == 0)
                                    && (asientosMatrizEscala[x][y + 5] == 0) && (asientosMatrizEscala[x][y + 6] == 0)) {
                                asientosMatrizEscala[x][0] = 1;
                                asientosMatrizEscala[x][0 + 1] = 1;
                                asientosMatrizEscala[x][0 + 2] = 1; // 3 es la columna que no se toca puesto que es el pasadillo
                                asientosMatrizEscala[x][0 + 4] = 1;
                                asientosMatrizEscala[x][0 + 5] = 1;
                                Integer posiciony1 = y + 1;
                                Integer posiciony2 = y + 2;
                                Integer posiciony4 = y + 4;
                                Integer posiciony5 = y + 5;
                                asientosCompradosXYEscala = (x + "," + y) + "-" + (x + "," + posiciony1) + "-" + (x + "," + posiciony2) + "-" + (x + "," + posiciony4) + "-" + (x + "," + posiciony5); // Agregar asiento asignado
                                asientoEncontrado = true;
                            }
                        }
                    }
                    if (asientoEncontrado) {
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }

        // Siempre se va asignar matriz principal si se encuentra asiento
        // luego valida si el vuelo es de escala, si es asi, quiere decir que 
        // el asiento si fue asignado y si es de escala
        if (asientoEncontrado) {
            // Entre las opciones de arriba, solo se podra ejecutar 1, por tanto, no hay problema se usamos este boolean
            String[] asientosXY = asientosCompradosXY.split("-"); // Es la compra de varios boletos, (x,y)-(x,y)-(x,y)-(x,y)-(x,y)
            for (int i = 0; i < asientosXY.length; i++) {
                String[] asientosXY1 = asientosXY[i].split(",");
                Integer asientoX = Integer.valueOf(asientosXY1[0]); // (X,y)
                Integer asientoY = Integer.valueOf(asientosXY1[1]); // (x,Y)
                asientosMatriz[asientoX][asientoY] = 1;
            }
            if (isEscala) {
                String[] asientosXYEscala = asientosCompradosXYEscala.split("-");
                for (int i = 0; i < asientosXYEscala.length; i++) {
                    String[] asientosXYEscala1 = asientosXYEscala[i].split(",");
                    Integer asientoXEscala = Integer.valueOf(asientosXYEscala1[0]);
                    Integer asientoYEscala = Integer.valueOf(asientosXYEscala1[1]);
                    asientosMatrizEscala[asientoXEscala][asientoYEscala] = 1;
                }
            }
        } else {
            System.out.println("\u001B[31mERROR:\u001B[0m " + " no hay asientos disponibles.");
        }
    }

    public void guardarCedulaActual(Integer cedulaActual) {
        cedula = cedulaActual;
    }

    public void matriz() {
//         Mostrar la matriz de asientos para depuración
        System.out.println("test main:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(asientosMatriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Fin matriz test...");

        System.out.println("test escala:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(asientosMatrizEscala[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Fin matriz test...");
    }
}
