public class SensorHumedad extends Dispositivo implements IMedible {
    private double humedadUltima;

    public SensorHumedad(String id, String nombre, double consumo) {
        super(id, nombre, consumo);
        this.humedadUltima = Double.NaN;
    }

    @Override
    public String medir() {
        double med = Math.round((20 + Math.random() * 60) * 10.0) / 10.0;
        this.humedadUltima = med;
        return String.format("SensorHumedad %s (id=%s): humedad=%.1f%%", getNombre(), getId(), med);
    }

    public double getHumedadUltima() { return humedadUltima; }
}