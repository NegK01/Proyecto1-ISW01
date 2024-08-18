package negocio;

public class Matriz {

    private String asientosCompradosPath;
    private String asientosDisponiblesPath;
    private String asientosOcupadosPath;
    private String pasilloPath;

    public Matriz() {
        asientosCompradosPath = "src/database/files/asientoComprado.png";
        asientosDisponiblesPath = "src/database/files/asientoDisponible.png";
        asientosOcupadosPath = "src/database/files/asientoOcupado.png";
        pasilloPath = "src/database/files/pasillo.png";
    }

    public String getAsientosCompradosPath() {
        return asientosCompradosPath;
    }

    public String getAsientosDisponiblesPath() {
        return asientosDisponiblesPath;
    }

    public String getAsientosOcupadosPath() {
        return asientosOcupadosPath;
    }

    public String getPasilloPath() {
        return pasilloPath;
    }

}
