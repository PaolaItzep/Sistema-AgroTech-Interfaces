import java.util.*;

public class MainApp {
    private final ControladorCatalogo controlador;
    private final Scanner scanner;

    public MainApp() {
        this.controlador = new ControladorCatalogo();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        controlador.initCatalogo();
        boolean running = true;
        while (running) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1": listarDispositivos(); break;
                case "2": buscarPorId(); break;
                case "3": buscarPorNombre(); break;
                case "4": ordenarPorConsumo(); break;
                case "5": ejecutarCapacidades(); break;
                case "0": System.out.println("Saliendo. ¡Hasta luego!"); running = false; break;
                default: System.out.println("Opción no válida.");
            }
            if (running) { System.out.println("\nPresiona Enter para continuar..."); scanner.nextLine(); }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("=== Catálogo de Dispositivos - Cooperativa Agro ===");
        System.out.println("1. Listar todos los equipos");
        System.out.println("2. Buscar equipo por ID");
        System.out.println("3. Buscar equipo por nombre");
        System.out.println("4. Ordenar catálogo por consumo eléctrico");
        System.out.println("5. Ejecutar capacidades de un equipo");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void listarDispositivos() {
        System.out.println("\n--- Lista de dispositivos ---");
        for (Dispositivo d : controlador.listar()) System.out.println(d);
    }

    private void buscarPorId() {
        System.out.print("Introduce el ID: ");
        String id = scanner.nextLine().trim();
        controlador.buscarPorId(id)
            .ifPresentOrElse(
                d -> System.out.println("Encontrado:\n" + d),
                () -> System.out.println("No se encontró un dispositivo con ese ID.")
            );
    }

    private void buscarPorNombre() {
        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("¿Búsqueda parcial? (s/n): ");
        boolean parcial = scanner.nextLine().trim().equalsIgnoreCase("s");
        List<Dispositivo> encontrados = controlador.buscarPorNombre(nombre, parcial);
        if (encontrados.isEmpty()) System.out.println("No se encontraron dispositivos.");
        else encontrados.forEach(System.out::println);
    }

    private void ordenarPorConsumo() {
        System.out.print("¿Ascendente? (s/n): ");
        boolean asc = scanner.nextLine().trim().equalsIgnoreCase("s");
        for (Dispositivo d : controlador.ordenarPorConsumo(asc)) System.out.println(d);
    }

    private void ejecutarCapacidades() {
        System.out.print("Introduce el ID: ");
        String id = scanner.nextLine().trim();
        controlador.buscarPorId(id).ifPresentOrElse(d -> {
            System.out.println("Ejecutando capacidades de " + d.getNombre() + ":");
            for (String r : controlador.ejecutarCapacidades(d)) System.out.println("- " + r);
        }, () -> System.out.println("No se encontró ese dispositivo."));
    }

    public static void main(String[] args) { new MainApp().run(); }
}