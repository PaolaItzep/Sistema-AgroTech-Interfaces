public class EstacionMeteorologica extends Dispositivo implements IMedible, IRegistrable {
    private double temperaturaUltima;
    private double lluviaUltima;

    public EstacionMeteorologica(String id, String nombre, double consumo) {
        super(id, nombre, consumo);
        this.temperaturaUltima = Double.NaN;
        this.lluviaUltima = Double.NaN;
    }

    @Override
    public String medir() {
        double temp = Math.round((10 + Math.random() * 25) * 10.0) / 10.0;
        double lluvia = Math.round((0 + Math.random() * 20) * 10.0) / 10.0;
        this.temperaturaUltima = temp;
        this.lluviaUltima = lluvia;
        return String.format("Estacion %s (id=%s): temp=%.1f°C, lluvia=%.1f mm", getNombre(), getId(), temp, lluvia);
    }

    @Override
    public String registrar() {
        return String.format("Estacion %s (id=%s): datos registrados (temp=%.1f°C, lluvia=%.1f mm).",
                getNombre(), getId(), temperaturaUltima, lluviaUltima);
    }
}