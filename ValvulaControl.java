public class ValvulaControl extends Dispositivo implements IAccionable {
    private boolean abierta;

    public ValvulaControl(String id, String nombre, double consumo) {
        super(id, nombre, consumo);
        this.abierta = false;
    }

    @Override
    public String ejecutarAccion() {
        this.abierta = !this.abierta;
        return String.format("Valvula %s (id=%s): estado cambiado -> %s",
                getNombre(), getId(), abierta ? "ABRIR" : "CERRAR");
    }

    public boolean isAbierta() { return abierta; }
}