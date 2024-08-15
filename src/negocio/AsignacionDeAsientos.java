package negocio;

public class AsignacionDeAsientos {

    private String aero;
    private String salida_in;
    private String hora_in;
    private String llegada_out;
    private String hora_out;
    private String escala;
    private String precio;
    private String duracion;
    private String cantidad;
    int filas = 6;
    int columnas = 7;
    private Integer[][] matriz = new Integer[filas][columnas];
    private Inicio_Registro inicio_Registro = new Inicio_Registro();
    private Integer IDHistorial = 0;

    public void AsignacionDeAsientos(String listaVueloSeleccionado) {
        System.out.println(listaVueloSeleccionado);

        // Habran exclusivamente 9 partes
        String[] partesVueloSeleccionado = listaVueloSeleccionado.split(",");

        aero = partesVueloSeleccionado[0];
        salida_in = partesVueloSeleccionado[1];
        hora_in = partesVueloSeleccionado[2];
        llegada_out = partesVueloSeleccionado[3];
        hora_out = partesVueloSeleccionado[4];
        escala = partesVueloSeleccionado[5];
        precio = partesVueloSeleccionado[6];
        duracion = partesVueloSeleccionado[7];
        cantidad = partesVueloSeleccionado[8];

        /*
            Asigna el valor 0 a cada celda de la matriz 
            (0 = asiento disponible, 1 = ocupado)
         */
//        if (inicio_Registro.getCedulaActualDelUsr()) {
//            
//        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = 0;
            }
        }

        boolean asientoEncontrado = false;

        if (cantidad.equals("1")) {
            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas; y++) {
                    if (matriz[x][y].equals(0) && y != 1 || y != 3 || y != 5) {
                        System.out.println(matriz[x][y] + " " + x + " " + y);
                        matriz[x][y] = 1;
                        System.out.println(matriz[x][y] + " " + x + " " + y);
                        asientoEncontrado = true;
                        break;
                    }
                }
                if (asientoEncontrado) {
                    break;
                }
            }
        }
        
        System.out.println("ojo");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        switch (cantidad) {
            case "1" -> {
            }
            case "2" -> {
            }
            case "3" -> {
            }
            case "4" -> {
            }
            case "5" -> {
            }
            default ->
                throw new AssertionError();
        }

    }
    
    
    
    public void generarIdHisotial() {
        IDHistorial = 200;
        
        IDHistorial = IDHistorial + 1;
        
        try {
            
        } catch (Exception e) {
        }
    }
}
