package data;

import logic.Producto;
import java.io.*;
import java.util.Scanner;

public interface Imprimible {

	// Método para agregar un producto al ticket
	void agregarProducto(Producto producto);

	// Método para calcular el total del ticket
	float calcularTotal();

	// Método para generar el formato del ticket
	String generarTicket();

	// Metodo : leer ficher, escribiir fichero
	String ruta = "C:/Users/Timur/eclipse-workspace/PRACTICA_FINAL/TIKET/Tiket.txt";

	default String leerRuta() throws IOException {
		
		String rutaAux = "";
		// Creamos un FileReader especificando la ruta
		// en la que se encuentra nuestro archivo de configuracion
		FileReader file = new FileReader(ruta);

		// Creamos el Objeto Scanner a partir del FileReader creado
		Scanner scanner = new Scanner(file);

		// Este bucle nos va a permitir recorrer nuestro fichero
		// hasta el final
		while (scanner.hasNextLine()) {
			// Obtenemos la siguiente linea del fichero
			Scanner line = new Scanner(scanner.nextLine());

			// Especificamos el separador introducido entre variable y
			// valor en nuestro archivo de configuracion.

			rutaAux = line.next();
			line.close();
		}

		// Cerramos scanner y fichero
		scanner.close();
		file.close();
		return rutaAux;
	}

	default void escribirFichero(String texto) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(ruta, false);
			pw = new PrintWriter(fichero);
			pw.println(texto);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fichero != null)
					fichero.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	default String leerFichero() {
		StringBuilder contenido = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contenido.toString();
	}

}
