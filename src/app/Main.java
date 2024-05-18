
package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.ExcepcionesPersonalizadas.ImporteInvalidoException;
import app.ExcepcionesPersonalizadas.NumeroCuentaInvalidoException;
import app.ExcepcionesPersonalizadas.TarjetaInvalidaException;
import app.ExcepcionesPersonalizadas.TelefonoInvalidoException;
import data.Fichero;
import logic.Cliente;

/**
 * @user Timur Bogach
 * @date 8 may 2024
 *
 *
 */
public class Main {

	public static void main(String[] args) {

		System.out.println("                                   ");
		System.out.println("          ( )                     ");
		System.out.println("           ) (                     ");
		System.out.println("         ........                  ");
		System.out.println("         |      |]                 ");
		System.out.println("         \\      /                  ");
		System.out.println("          `----'                   ");
		System.out.println("                                    ");
		System.out.println("         .-'''''-.                 ");
		System.out.println("      .'  * * * *  `.              ");
		System.out.println("     :   *       *   :              ");
		System.out.println("    :  ~  MASTICA  ~  :             ");
		System.out.println("    :  ~     y     ~  :             ");
		System.out.println("     :   * TRAGA *   :              ");
		System.out.println("      '. * * * * * .'             ");
		System.out.println("         '-.....-'                 ");
		System.out.println("\n***************************************");

		// Cargar los clientes desde el archivo al iniciar la aplicación
		List<Cliente> clientes = Fichero.cargarClientes();
		Cliente gestorClientes = new Cliente();
		if (clientes != null) {
			for (Cliente cliente : clientes) {
				gestorClientes.añadirCliente(cliente);
			}
		} else {
			System.out.println("Hay un error al cargar los clientes desde el fichero");
		}

		Scanner scanner = new Scanner(System.in);

		// Guardar clientes al cerrar la aplicación
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			Fichero.guardarClientes(gestorClientes.getListaClientes());
		}));

		System.out.print("\nBienvenido a la tienda del COPÔN: \n");

		boolean salir = false;
		while (!salir) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Menú Principal ---");
				System.out.println("1. Usuario");
				System.out.println("2. Administrador");
				System.out.println("3. Salir");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionPrincipal = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionPrincipal) {
				case 1:
					subMenuUsuario(scanner, gestorClientes);
					break;

				case 2:
					subMenuAdministrador(scanner, gestorClientes);
					break;

				case 3:
					salir = true;
					System.out.println("Saliendo del programa...");
					break;

				default:
					System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada no válida. Por favor, ingrese un número.");
				scanner.nextLine(); // Consumir el salto de línea
			}
		}

		scanner.close();
	}

	public static void subMenuUsuario(Scanner scanner, Cliente gestorClientes) {
		boolean salirUsuario = false;
		while (!salirUsuario) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Menú de Usuario ---");
				System.out.println("1. Login");
				System.out.println("2. Registrar");
				System.out.println("3. Volver al menú principal");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionUsuario = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionUsuario) {
				case 1:
					Cliente clienteLogin = Menu.loginUsuario(scanner, gestorClientes);
					subMenuLogin(scanner, clienteLogin);
					break;

				case 2:
					Cliente clienteRegistrado = Menu.registrarUsuario(scanner, gestorClientes);
					subMenuLogin(scanner, clienteRegistrado);
					break;

				case 3:
					salirUsuario = true;
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

	public static void subMenuLogin(Scanner scanner, Cliente cliente) {
		GestionPedido pedido = new GestionPedido(cliente);
		pedido.cargarProductosEnMemoria();

		boolean salirLogin = false;
		while (!salirLogin) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Menú de Login de Usuario ---");
				System.out.println("1. Comprar");
				System.out.println("2. Ver carrito");
				System.out.println("3. Volver");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionLogin = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionLogin) {
				case 1:
					Menu.comprar(scanner, pedido);
					break;

				case 2:
					Menu.verCarrito(scanner, pedido);
					break;

				case 3:
					salirLogin = true;
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

	public static void subMenuAdministrador(Scanner scanner, Cliente gestorClientes) {
		boolean salirAdmin = false;
		GestionPedido pedidoAdmin = new GestionPedido(gestorClientes);
		pedidoAdmin.cargarProductosEnMemoria();

		while (!salirAdmin) {
			try {
				System.out.println("_____________________________________");
				System.out.println("\n--- Menú de Administrador ---");
				System.out.println("1. Agregar productos");
				System.out.println("2. Borrar productos");
				System.out.println("3. Eliminar usuarios");
				System.out.println("4. Volver al menú principal");
				System.out.print("Seleccione una opción: \n");
				System.out.println("_____________________________________");

				int opcionAdmin = scanner.nextInt();
				scanner.nextLine(); // Consumir el salto de línea

				switch (opcionAdmin) {
				case 1:
					Menu.agregarProductos(scanner, pedidoAdmin);
					break;

				case 2:
					Menu.borrarProductos(scanner, pedidoAdmin);
					break;

				case 3:
					Menu.eliminarUsuarios(scanner, gestorClientes);
					break;

				case 4:
					salirAdmin = true;
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

	// Métodos de validación
	public static void validarTelefono(String telefono) throws TelefonoInvalidoException {
		if (!telefono.matches("\\d{9}")) {
			throw new TelefonoInvalidoException("El número de teléfono debe tener 9 dígitos.");
		}
	}

	public static void validarImporte(double importe) throws ImporteInvalidoException {
		if (importe <= 0) {
			throw new ImporteInvalidoException("El importe debe ser mayor que cero.");
		}
	}

	public static void validarTarjeta(String tarjeta) throws TarjetaInvalidaException {
		if (!tarjeta.matches("\\d{16}")) {
			throw new TarjetaInvalidaException("El número de tarjeta debe tener 16 dígitos.");
		}
	}

	public static void validarNumeroCuenta(String cuenta) throws NumeroCuentaInvalidoException {
		if (!cuenta.matches("\\d{20}")) {
			throw new NumeroCuentaInvalidoException("El número de cuenta debe tener 20 dígitos.");
		}
	}

}
