import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionPedidos {

    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;

    public GestionPedidos() {
        pedidos = new ArrayList<>();
        repartidores = new ArrayList<>();
    }

    public void agregarRepartidor(Repartidor repartidor) {
        repartidores.add(repartidor);
    }
    public void crearPedido(Cliente cliente, List<Producto>productos) {
        Repartidor repartidorDisponible = obtenerRepartidorDisponible();
        if (repartidorDisponible == null) {
            throw new RuntimeException("Repartidores no disponobles");
        }

        Pedido pedido = new Pedido(cliente, productos, repartidorDisponible);
        pedidos.add(pedido);
        repartidorDisponible.setDisponible(false);
        System.out.println("Pedido agregado correctamente: " + pedido);
    }

    public void marcarPedidoComoEntregado(int idPedido){
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == idPedido) {
                pedido.marcarEntregado();
                System.out.println("Pedido marcarado correctamente: " + pedido);
                return;
            } else {
                throw new RuntimeException("Pedido ya esta entregado");
            }
        }
        System.out.println("Pedido nao encontrado");
    }

    public List<Pedido> listarPedidosEntregados() {
        List<Pedido> entregados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.isEntregado()) {
                entregados.add(pedido);
            }
        }
        return entregados;
    }

    private Repartidor obtenerRepartidorDisponible() {
        List<Repartidor> disponibles = new ArrayList<>();
        for (Repartidor repartidor : repartidores) {
            if (repartidor.isDisponible()) {
                disponibles.add(repartidor);
            }
        }
        if (disponibles.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return disponibles.get(rand.nextInt(disponibles.size()));
    }

    public List<Pedido> listarPedidosPendientes() {
        List<Pedido> pendientes = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (!pedido.isEntregado()) {
                pendientes.add(pedido);
            }
        }
        return pendientes;
    }


}
