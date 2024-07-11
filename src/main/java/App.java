import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    private GestionPedidos gestionPedidos;

    public App() {
        gestionPedidos = new GestionPedidos();

        gestionPedidos.agregarRepartidor(new Repartidor("Flash"));
        gestionPedidos.agregarRepartidor(new Repartidor("Rapido y buena gente"));
        gestionPedidos.agregarRepartidor(new Repartidor("A pata"));
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println( ANSI_GREEN + " Menu Gestion Pedidos" + ANSI_RESET);
            System.out.println(" 1. Crear un nuevo pedido");
            System.out.println(" 2. Marcar pedido como entregado");
            System.out.println("3. Listar los pedidos pendientes");
            System.out.println("4. Listar los pedidos entregados");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearNuevoPedido(scanner);
                    break;
                case 2:
                    marcarPedidoComoEntregado(scanner);
                    break;
                case 3:
                    listarPedidosPendientes();
                    break;
                case 4:
                    listarPedidosEntregados();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private void crearNuevoPedido(Scanner scanner) {
        Cliente cliente = null;
        List<Producto> productos = null;
        boolean datosValidos = false;

        while (!datosValidos) {
            try {
                System.out.println(ANSI_PURPLE + "Ingrese el nombre del cliente: ");
                String nombreCliente = scanner.nextLine();
                System.out.println("Ingrese la direccion del cliente: ");
                String direccionCliente = scanner.nextLine();
                cliente = new Cliente(nombreCliente, direccionCliente);

                productos = new ArrayList<>();
                String opcionProducto;

                do {
                    System.out.println(ANSI_YELLOW  + "Seleccione el producto para agregar al pedido: " + ANSI_RESET);
                    System.out.println("1. Burrito");
                    System.out.println("2. Hamburguesa");
                    System.out.println("3. Kebab");
                    System.out.println("4. Pizza");
                    System.out.println(ANSI_BLUE + "5. Terminar la seleccion de los productos");
                    System.out.print("Seleccione una opcion: ");
                    opcionProducto = scanner.nextLine();

                    switch (opcionProducto) {
                        case "1":
                            productos.add(new Burrito());
                            break;
                        case "2":
                            productos.add(new Hamburguesa());
                            break;
                        case "3":
                            productos.add(new Kebab());
                            break;
                        case "4":
                            productos.add(new Pizza());
                            break;
                        case "5":
                            System.out.println("Saliendo de seleccionar los productos!...");
                            break;
                        default:
                            System.out.println(ANSI_RED  + "opcion no validad, intente de nuevo" + ANSI_RESET);
                    }
                } while (!opcionProducto.equals("5"));

                gestionPedidos.crearPedido(cliente, productos);
                datosValidos = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca nuevamente los datos del pedido.");
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void marcarPedidoComoEntregado(Scanner scanner) {
        System.out.print(ANSI_PURPLE + "Ingrese el ID del pedido a marcar como entregado: " + ANSI_RESET);
        int idPedido = scanner.nextInt();
        scanner.nextLine();
        gestionPedidos.marcarPedidoComoEntregado(idPedido);
    }

    private void listarPedidosPendientes() {
        List<Pedido> pedidosPendientes = gestionPedidos.listarPedidosPendientes();
        if (pedidosPendientes.isEmpty()) {
            System.out.println(ANSI_PURPLE + "No hay pedidos pendientes." + ANSI_RESET);
        } else {
            System.out.println("Pedidos pendientes:");
            for (Pedido pedido : pedidosPendientes) {
                System.out.println(pedido);
            }
        }
    }

    private void listarPedidosEntregados() {
        List<Pedido> pedidosEntregados = gestionPedidos.listarPedidosEntregados();
        if (pedidosEntregados.isEmpty()) {
            System.out.println(ANSI_PURPLE + "No hay pedidos entregados." + ANSI_RESET);
        } else {
            System.out.println("Pedidos entregados:");
            for (Pedido pedido : pedidosEntregados) {
                System.out.println(pedido);
            }
        }
    }
}
