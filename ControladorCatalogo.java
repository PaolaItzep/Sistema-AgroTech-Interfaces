import java.util.*;
import java.util.stream.Collectors;

public class ControladorCatalogo {
    private final List<Dispositivo> catalogo;

    public ControladorCatalogo() {
        this.catalogo = new ArrayList<>();
    }

    public List<Dispositivo> getCatalogo() {
        return Collections.unmodifiableList(catalogo);
    }

    public void initCatalogo() {
        catalogo.clear();
        catalogo.add(new SensorHumedad("S-H-001", "SensorHumedad Norte", 1.2));
        catalogo.add(new SensorHumedad("S-H-002", "SensorHumedad Sur", 1.1));
        catalogo.add(new EstacionMeteorologica("E-M-001", "Estacion Principal", 5.0));
        catalogo.add(new EstacionMeteorologica("E-M-002", "Estacion Montaña", 4.5));
        catalogo.add(new DronRiego("D-R-001", "Dron Alfa", 50.0));
        catalogo.add(new DronRiego("D-R-002", "Dron Beta", 55.5));
        catalogo.add(new ValvulaControl("V-001", "Valvula Sector A", 2.0));
        catalogo.add(new ValvulaControl("V-002", "Valvula Sector B", 1.8));
        catalogo.add(new SensorHumedad("S-H-003", "SensorHumedad Este", 1.0));
        catalogo.add(new Dispositivo("DIS-LOG-001", "RegistroGenérico", 0.5) {});
    }

    public List<Dispositivo> listar() {
        return getCatalogo();
    }

    public Optional<Dispositivo> buscarPorId(String id) {
        return catalogo.stream().filter(d -> d.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<Dispositivo> buscarPorNombre(String nombre) {
        return catalogo.stream()
                .filter(d -> d.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    public List<Dispositivo> buscarPorNombre(String nombre, boolean parcial) {
        if (!parcial) return buscarPorNombre(nombre);
        String lower = nombre.toLowerCase();
        return catalogo.stream()
                .filter(d -> d.getNombre().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public List<Dispositivo> ordenarPorConsumo(boolean ascendente) {
        List<Dispositivo> copia = new ArrayList<>(catalogo);
        copia.sort(ascendente ? Comparator.naturalOrder() : Comparator.reverseOrder());
        return copia;
    }

    public List<String> ejecutarCapacidades(Dispositivo d) {
        List<String> resultados = new ArrayList<>();
        if (d instanceof IMedible) resultados.add(((IMedible) d).medir());
        if (d instanceof IAccionable) resultados.add(((IAccionable) d).ejecutarAccion());
        if (d instanceof IRegistrable) resultados.add(((IRegistrable) d).registrar());
        if (resultados.isEmpty()) resultados.add("Este dispositivo no implementa capacidades medibles/accionables/registrables.");
        return resultados;
    }
}