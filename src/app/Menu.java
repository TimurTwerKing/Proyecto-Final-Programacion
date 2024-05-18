
package app;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import app.ExcepcionesPersonalizadas.ImporteInvalidoException;
import app.ExcepcionesPersonalizadas.TelefonoInvalidoException;
import data.Fichero;
import logic.Bebida;
import logic.Cliente;
import logic.Comida;
import logic.Producto;

/**
 * @user Timur Bogach
 * @date 18 may 2024
 *
 *
 */

public class Menu {
	public static Cliente loginUsuario(Scanner scanner, Cliente gestorClientes) {
		Cliente clienteSeleccionado = null;
		while (clienteSeleccionado == null) {
			try {
				System.out.println("\n--- Login de Usuario ---");
				System.out.println(gestorClientes.mostrarClientesRegistrados());
				System.out.print("Ingrese el nombre del cliente: ");
				String nombreClienteSeleccionado = scanner.nextLine();
				clienteSeleccionado = gestorClientes.seleccionarClientePorNombre(nombreClienteSeleccionado);
				if (clienteSeleccionado == null) {
					System.out.println("Cliente no encontrado. Intente nuevamente.");
				}
			} catch (Exception e) {
				System.out.println("Error al buscar el cliente. Intente nuevamente.");
				scanner.nextLine(); // Consumir el salto de línea
			}
		}
		return clienteSeleccionado;
	}

	public static Cliente registrarUsuario(Scanner scanner, Cliente gestorClientes) {
		Cliente nuevoCliente = null;
		try {
			System.out.print("Ingrese el nombre del cliente: ");
			String nombreCliente = scanner.nextLine();
			System.out.print("Ingrese los apellidos del cliente: ");
			String apellidosCliente = scanner.nextLine();
			System.out.print("Ingrese el teléfono del cliente: ");
			String telefonoCliente = scanner.nextLine();
			Main.validarTelefono(telefonoCliente); // Validar el teléfono del cliente
			System.out.print("Ingrese la dirección del cliente: ");
			String direccionCliente = scanner.nextLine();
			System.out.print("Ingrese el historial del cliente: ");
			String historialCliente = scanner.nextLine();

			nuevoCliente = new Cliente(nombreCliente, apellidosCliente, new GregorianCalendar(), telefonoCliente,
					direccionCliente, historialCliente);
			gestorClientes.añadirCliente(nuevoCliente);
			System.out.println("Cliente registrado correctamente.");
		} catch (TelefonoInvalidoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error al registrar el cliente. Intente nuevamente.");
			scanner.nextLine(); // Consumir el salto de línea
		}
		return nuevoCliente;
	}

	public static void comprar(Scanner scanner, GestionPedido pedido) {
		boolean subMenuComprar = true;
		while (subMenuComprar) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Menú de Compra ---");
				System.out.println("1. Agregar producto a la cesta");
				System.out.println("2. Pagar");
				System.out.println("3. Volver");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionComprar = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionComprar) {
				case 1:
					pedido.listarProductosCatalogo();
					System.out.print("Ingrese el nombre del producto a agregar: ");
					String nombreAgregarCesta = scanner.nextLine();
					Producto productoAgregarCesta = pedido.buscarProductoPorNombreCatalogo(nombreAgregarCesta);
					if (productoAgregarCesta != null) {
						pedido.agregarProductoCesta(productoAgregarCesta);
						System.out.println("Producto agregado a la cesta.");
					} else {
						System.out.println("Producto no encontrado.");
					}
					break;

				case 2:
					double total = pedido.calcularTotalCesta();
					Main.validarImporte(total); // Validar el importe total
					System.out.println("El total del pedido es: " + total);

					// Realizar el pago con tarjeta
					PagarTarjeta.pagar(pedido.getCliente(), scanner);

					// Generar y mostrar el ticket
					String ticket = pedido.generarTicket();
					System.out.println(ticket);

					// Guardar el ticket en un archivo
					Fichero.guardarTicket(ticket);

					System.out.println("Gracias por su compra.");
					subMenuComprar = false;
					break;

				case 3:
					subMenuComprar = false;
					break;

				default:
					System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada no válida. Por favor, ingrese un número.");
				scanner.nextLine(); // Consumir el salto de línea
			} catch (ImporteInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void verCarrito(Scanner scanner, GestionPedido pedido) {
		boolean subMenuCarrito = true;
		while (subMenuCarrito) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Carrito de Compras ---");
				pedido.listarProductosCesta();
				System.out.println("1. Borrar producto");
				System.out.println("2. Volver");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionCarrito = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionCarrito) {
				case 1:
					System.out.print("Ingrese el nombre del producto a eliminar: ");
					String nombreEliminarCesta = scanner.nextLine();
					Producto productoEliminarCesta = pedido.buscarProductoPorNombreCatalogo(nombreEliminarCesta);
					if (productoEliminarCesta != null) {
						pedido.eliminarProductoCesta(productoEliminarCesta);
						System.out.println("Producto eliminado correctamente.");
					} else {
						System.out.println("Producto no encontrado.");
					}
					break;

				case 2:
					subMenuCarrito = false;
					break;

				default:
					System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada no válida. Por favor, ingrese un número.");
				scanner.nextLine(); // Consumir el salto de línea
			}
		}
	}

	public static void agregarProductos(Scanner scanner, GestionPedido pedido) {
		try {
			System.out.println("\n--- Agregar Producto ---");
			System.out.println("Seleccione el tipo de producto a agregar: ");
			System.out.println("1. Bebida");
			System.out.println("2. Comida");
			int tipoProducto = scanner.nextInt();
			scanner.nextLine(); // Consumir el salto de línea

			System.out.print("Ingrese el nombre del producto: ");
			String nombreProducto = scanner.nextLine();
			System.out.print("Ingrese el precio del producto: ");
			float precioProducto = scanner.nextFloat();
			scanner.nextLine(); // Consumir el salto de línea
			System.out.print("Ingrese el estado del producto: ");
			String estadoProducto = scanner.nextLine();
			Calendar fechaCaducidad = Calendar.getInstance();
			System.out.print("Ingrese la fecha de caducidad (dd/MM/yyyy): ");
			String fechaCaducidadStr = scanner.nextLine();
			String[] fechaParts = fechaCaducidadStr.split("/");
			fechaCaducidad.set(Integer.parseInt(fechaParts[2]), Integer.parseInt(fechaParts[1]) - 1,
					Integer.parseInt(fechaParts[0]));

			Producto producto = null;
			if (tipoProducto == 1) { // Si el producto es una bebida
				System.out.print("¿Es gaseoso? (true/false): ");
				boolean gaseoso = scanner.nextBoolean();
				System.out.print("¿Es lácteo? (true/false): ");
				boolean lacteo = scanner.nextBoolean();
				scanner.nextLine(); // Consumir el salto de línea
				System.out.print("Ingrese la medida de la bebida: ");
				String medida = scanner.nextLine();

				producto = new Bebida(nombreProducto, precioProducto, fechaCaducidad, estadoProducto, gaseoso, lacteo,
						medida);
			} else if (tipoProducto == 2) { // Si el producto es una comida
				System.out.print("¿Es perecedero? (true/false): ");
				boolean perecedero = scanner.nextBoolean();
				System.out.print("Ingrese la cantidad de calorías: ");
				float calorias = scanner.nextFloat();
				System.out.print("¿Es vegano? (true/false): ");
				boolean vegano = scanner.nextBoolean();
				scanner.nextLine(); // Consumir el salto de línea
				Calendar fechaEnvase = Calendar.getInstance();
				System.out.print("Ingrese la fecha de envase (dd/MM/yyyy): ");
				String fechaEnvaseStr = scanner.nextLine();
				String[] fechaEnvaseParts = fechaEnvaseStr.split("/");
				fechaEnvase.set(Integer.parseInt(fechaEnvaseParts[2]), Integer.parseInt(fechaEnvaseParts[1]) - 1,
						Integer.parseInt(fechaEnvaseParts[0]));

				producto = new Comida(nombreProducto, precioProducto, fechaCaducidad, estadoProducto, perecedero,
						calorias, vegano, fechaEnvase);
			}

			if (producto != null) {
				pedido.agregarProductoCatalogo(producto); // Agregar el producto al catálogo
				System.out.println("Producto agregado correctamente.");
			} else {
				System.out.println("Tipo de producto no válido.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrada no válida. Por favor, ingrese los datos correctamente.");
			scanner.nextLine(); // Consumir el salto de línea
		} catch (Exception e) {
			System.out.println("Error al agregar el producto. Intente nuevamente.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}

	public static void borrarProductos(Scanner scanner, GestionPedido pedido) {
		try {
			// Listar productos del catálogo
			System.out.println("\n--- Productos en el Catálogo ---");
			pedido.listarProductosCatalogo();

			// Pedir al usuario que ingrese el nombre del producto a eliminar
			System.out.println("Ingrese el nombre del producto a eliminar del catálogo: ");
			String nombreEliminarCatalogo = scanner.nextLine();

			// Buscar el producto por nombre en el catálogo
			Producto productoEliminarCatalogo = pedido.buscarProductoPorNombreCatalogo(nombreEliminarCatalogo);

			// Eliminar el producto si se encuentra en el catálogo
			if (productoEliminarCatalogo != null) {
				pedido.eliminarProductoCatalogo(productoEliminarCatalogo);
				System.out.println("Producto eliminado correctamente.");
			} else {
				System.out.println("Producto no encontrado.");
			}
		} catch (Exception e) {
			System.out.println("Error al borrar el producto. Intente nuevamente.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}

	public static void eliminarUsuarios(Scanner scanner, Cliente gestorClientes) {
		try {
			System.out.println("\n--- Eliminar Usuario ---");
			System.out.println(gestorClientes.mostrarClientesRegistrados());
			System.out.print("Ingrese el nombre del cliente a eliminar: ");
			String nombreClienteEliminar = scanner.nextLine();
			gestorClientes.eliminarClientePorNombre(nombreClienteEliminar);
			System.out.println("Cliente eliminado correctamente.");
		} catch (Exception e) {
			System.out.println("Error al eliminar el cliente. Intente nuevamente.");
			scanner.nextLine(); // Consumir el salto de línea
		}
	}
}