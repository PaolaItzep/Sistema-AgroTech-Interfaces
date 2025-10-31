import java.util.Objects;

public abstract class Dispositivo implements Comparable<Dispositivo> {
    private String id;
    private String nombre;
    private double consumoElectrico;

    public Dispositivo(String id, String nombre, double consumoElectrico) {
        this.id = id;
        this.nombre = nombre;
        this.consumoElectrico = consumoElectrico;
    }

    public Dispositivo(String id, String nombre) {
        this(id, nombre, 0.0);
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getConsumoElectrico() { return consumoElectrico; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setConsumoElectrico(double consumoElectrico) { this.consumoElectrico = consumoElectrico; }

    @Override
    public int compareTo(Dispositivo o) {
        return Double.compare(this.consumoElectrico, o.consumoElectrico);
    }

    @Override
    public String toString() {
        return String.format("%s [id=%s] - Consumo: %.2f W", nombre, id, consumoElectrico);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dispositivo)) return false;
        Dispositivo that = (Dispositivo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}