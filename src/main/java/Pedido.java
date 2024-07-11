import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Pedido {

    private static final AtomicInteger CONTADOR = new AtomicInteger(0);
    private final int id;
    private Cliente cliente;
    private List<Producto> productos;
    private Repartidor repartidor;
    private boolean entregado;

    public Pedido(Cliente cliente, List<Producto> productos, Repartidor repartidor) {
      if (cliente == null || productos == null || productos.isEmpty()) {
          throw new IllegalArgumentException( "El cliente y los productos no puede estar vacio" );
      }
      this.id = CONTADOR.getAndIncrement();
      this.cliente = cliente;
      this.productos = productos;
      this.repartidor = repartidor;
      this.entregado = false;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public void marcarEntregado() {
        this.entregado = true;
        this.repartidor.setDisponible(true);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", productos=" + productos +
                ", repartidor=" + repartidor +
                ", entregado=" + entregado +
                '}';
    }

    //    private void asignarRepartidor() {
//        Repartidor repartidor = new Repartidor("Repartidor" + id, true);
//        this.repartidor = repartidor;
//
//    }


}
