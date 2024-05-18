
package app;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import data.Imprimible;
import logic.Bebida;
import logic.Cliente;
import logic.Comida;
import logic.Producto;

/**
 * @user Timur Bogach
 * @date 12 may 2024
 *
 *
 */
public class GestionPedido implements Imprimible {
	private ArrayList<Producto> ListaCatalogo;
	private ArrayList<Producto> ListaCesta;
	private Cliente cliente;

	public GestionPedido(Cliente cliente) {
		this.cliente = cliente;
		this.ListaCatalogo = new ArrayList<>();
		this.ListaCesta = new ArrayList<>();
	}

	public void agregarProductoCatalogo(Producto producto) {
		ListaCatalogo.add(producto);
	}

	public void agregarProductoCesta(Producto producto) {
		ListaCesta.add(producto);
	}

	public void eliminarProductoCatalogo(Producto producto) {
		ListaCesta.remove(producto);
	}

	public void eliminarProductoCesta(Producto producto) {
		ListaCesta.remove(producto);
	}

	public float calcularTotalCesta() {
		float total = 0;
		for (Producto producto : ListaCesta) {
			total += producto.getPrecio();
		}
		return total;
	}

	public void listarProductosCatalogo() {
		if (ListaCatalogo.isEmpty()) {
			System.out.println("No hay productos en el pedido.");
		} else {
			for (Producto uniPr : ListaCatalogo) {
				if (uniPr instanceof Comida) {
					Comida uniComida = (Comida) uniPr;
					System.out.println(uniComida.detalleProducto());
				} else if (uniPr instanceof Bebida) {
					Bebida uniBebida = (Bebida) uniPr;
					System.out.println(uniBebida.detalleProducto());
				} else {
					System.out.println(uniPr.detalleProducto());
				}
			}
		}
	}

	public void listarProductosCesta() {
		if (ListaCesta.isEmpty()) {
			System.out.println("No hay productos en el pedido.");
		} else {
			for (Producto uniPr : ListaCesta) {
				if (uniPr instanceof Comida) {
					Comida uniComida = (Comida) uniPr;
					System.out.println(uniComida.detalleProducto());
				} else if (uniPr instanceof Bebida) {
					Bebida uniBebida = (Bebida) uniPr;
					System.out.println(uniBebida.detalleProducto());
				} else {
					System.out.println(uniPr.detalleProducto());
				}
			}
		}
	}

	public Producto buscarProductoPorNombreCatalogo(String nombre) {
		for (Producto producto : ListaCatalogo) {
			if (producto.getNombre().equalsIgnoreCase(nombre)) {
				return producto;
			}
		}
		return null;
	}

	public Producto buscarProductoPorNombreCesta(String nombre) {
		for (Producto producto : ListaCesta) {
			if (producto.getNombre().equalsIgnoreCase(nombre)) {
				return producto;
			}
		}
		return null;
	}

	public void cargarProductosEnMemoria() {
		// Crear lista de productos

		// COMIDA
		Comida arrozValenciana = new Comida("Arroz a la Valenciana", 14.00f, new GregorianCalendar(2024, 4, 5),
				"Caliente", true, 450.0f, false, new GregorianCalendar(2024, 4, 1));
		Comida tortillaPatatas = new Comida("Tortilla de Patatas", 8.00f, new GregorianCalendar(2024, 5, 18),
				"Caliente", true, 300.0f, false, new GregorianCalendar(2024, 5, 13));
		Comida churrosChocolate = new Comida("Churros con Chocolate", 6.50f, new GregorianCalendar(2024, 5, 17),
				"Caliente", true, 400.0f, false, new GregorianCalendar(2024, 5, 12));
		Comida gazpachoAndaluz = new Comida("Gazpacho Andaluz", 7.50f, new GregorianCalendar(2024, 5, 22), "Fresco",
				true, 250.0f, true, new GregorianCalendar(2024, 5, 16));
		Comida tartaQueso = new Comida("Tarta de Queso", 12.00f, new GregorianCalendar(2024, 5, 25), "Fresco", true,
				500.0f, true, new GregorianCalendar(2024, 5, 15));
		Comida paellaMarinera = new Comida("Paella Marinera", 15.00f, new GregorianCalendar(2024, 5, 30), "Caliente",
				true, 500.0f, false, new GregorianCalendar(2024, 5, 25));
		Comida croquetasJamon = new Comida("Croquetas de Jamón", 9.00f, new GregorianCalendar(2024, 6, 1), "Caliente",
				true, 250.0f, false, new GregorianCalendar(2024, 5, 27));
		Comida ensaladillaRusa = new Comida("Ensaladilla Rusa", 7.00f, new GregorianCalendar(2024, 5, 20), "Fresco",
				true, 300.0f, true, new GregorianCalendar(2024, 5, 15));
		Comida fabadaAsturiana = new Comida("Fabada Asturiana", 12.50f, new GregorianCalendar(2024, 5, 29), "Caliente",
				true, 350.0f, false, new GregorianCalendar(2024, 5, 24));
		Comida flanDeHuevo = new Comida("Flan de Huevo", 5.00f, new GregorianCalendar(2024, 5, 25), "Fresco", true,
				200.0f, true, new GregorianCalendar(2024, 5, 20));

		// BEBIDA
		Bebida refrescoCola = new Bebida("Refresco de Cola", 1.80f, new GregorianCalendar(2024, 5, 28), "Fresco", true,
				false, "330ml");
		Bebida teHelado = new Bebida("Té Helado", 2.20f, new GregorianCalendar(2024, 6, 2), "Fresco", false, false,
				"250ml");
		Bebida smoothieFresa = new Bebida("Smoothie de Fresa", 3.50f, new GregorianCalendar(2024, 5, 27), "Fresco",
				false, true, "300ml");
		Bebida vinoBlanco = new Bebida("Vino Blanco", 10.00f, new GregorianCalendar(2024, 4, 15), "Fresco", false,
				false, "750ml");
		Bebida licorCafe = new Bebida("Licor de Café", 12.00f, new GregorianCalendar(2024, 5, 24), "Fresco", false,
				false, "500ml");
		Bebida aguaMineral = new Bebida("Agua Mineral", 1.50f, new GregorianCalendar(2024, 5, 25), "Fresco", false,
				false, "500ml");
		Bebida cerveza = new Bebida("Cerveza", 2.00f, new GregorianCalendar(2024, 5, 22), "Fresco", true, false,
				"33cl");
		Bebida zumoNaranja = new Bebida("Zumo de Naranja", 2.50f, new GregorianCalendar(2024, 5, 20), "Fresco", false,
				true, "200ml");
		Bebida cafeConLeche = new Bebida("Café con Leche", 1.80f, new GregorianCalendar(2024, 4, 5), "Caliente", false,
				true, "200ml");
		Bebida vinoTinto = new Bebida("Vino Tinto", 8.00f, new GregorianCalendar(2024, 4, 15), "Fresco", false, false,
				"750ml");

		// Añadir productos al catálogo
		ListaCatalogo.add(arrozValenciana);
		ListaCatalogo.add(tortillaPatatas);
		ListaCatalogo.add(churrosChocolate);
		ListaCatalogo.add(gazpachoAndaluz);
		ListaCatalogo.add(tartaQueso);
		ListaCatalogo.add(paellaMarinera);
		ListaCatalogo.add(croquetasJamon);
		ListaCatalogo.add(ensaladillaRusa);
		ListaCatalogo.add(fabadaAsturiana);
		ListaCatalogo.add(flanDeHuevo);
		ListaCatalogo.add(refrescoCola);
		ListaCatalogo.add(teHelado);
		ListaCatalogo.add(smoothieFresa);
		ListaCatalogo.add(vinoBlanco);
		ListaCatalogo.add(licorCafe);
		ListaCatalogo.add(aguaMineral);
		ListaCatalogo.add(cerveza);
		ListaCatalogo.add(zumoNaranja);
		ListaCatalogo.add(cafeConLeche);
		ListaCatalogo.add(vinoTinto);

	}

	@Override
	public String toString() {
		return "GestionPedido{" + "productos=" + ListaCatalogo + ", cliente=" + cliente + '}';
	}

	@Override
	public String generarTicket() {
		StringBuilder ticket = new StringBuilder();
		ticket.append("----- TICKET DE COMPRA -----\n");
		ticket.append("Cliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellidos()).append("\n");
		ticket.append("Fecha: ").append(new GregorianCalendar().getTime()).append("\n");
		ticket.append("\nProductos:\n");

		for (Producto producto : ListaCesta) {
			ticket.append(producto.detalleProducto()).append("\n");
		}

		ticket.append("\nTotal: ").append(calcularTotalCesta()).append(" €\n");
		ticket.append("----------------------------\n");

		return ticket.toString();

	}

	@Override
	public void agregarProducto(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public float calcularTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Producto> getListaCatalogo() {
		return ListaCatalogo;
	}

	public void setListaCatalogo(ArrayList<Producto> listaCatalogo) {
		ListaCatalogo = listaCatalogo;
	}

	public ArrayList<Producto> getListaCesta() {
		return ListaCesta;
	}

	public void setListaCesta(ArrayList<Producto> listaCesta) {
		ListaCesta = listaCesta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
