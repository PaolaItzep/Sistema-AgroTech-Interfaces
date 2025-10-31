public class DronRiego extends Dispositivo implements IAccionable, IRegistrable {
    private boolean ultimaAccionRiego;

    public DronRiego(String id, String nombre, double consumo) {
        super(id, nombre, consumo);
        this.ultimaAccionRiego = false;
    }

    @Override
    public String ejecutarAccion() {
        this.ultimaAccionRiego = true;
        return String.format("DronRiego %s (id=%s): acción ejecutada -> riego completado.", getNombre(), getId());
    }

    @Override
    public String registrar() {
        return String.format("DronRiego %s (id=%s): registro creado (último riego: %s).",
                getNombre(), getId(), ultimaAccionRiego ? "sí" : "no");
    }
}