
package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import logic.Cliente;

/**
 * @user Timur Bogach
 * @date 12 may 2024
 *
 *
 */
public class Fichero {

	private static final String ARCHIVO_CLIENTES = "C:/Users/Timur/eclipse-workspace/PRACTICA_FINAL/SAVE/clientes.txt";
	private static final String ARCHIVO_TIKET = "C:/Users/Timur/eclipse-workspace/PRACTICA_FINAL/SAVE/Tiket.txt";

	// Método para guardar los clientes en un archivo de texto
	public static void guardarClientes(List<Cliente> clientes) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CLIENTES))) {
			for (Cliente cliente : clientes) {
				pw.println(cliente.toTexto());
			}
		} catch (IOException e) {
			System.err.println("Error al guardar clientes: " + e.getMessage());
		}
	}

	// Método para cargar los clientes desde un archivo de texto
	public static List<Cliente> cargarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				clientes.add(Cliente.fromTexto(linea));
			}
		} catch (FileNotFoundException e) {
			System.out.println("El archivo de clientes no existe. Se creará uno nuevo al guardar.");
		} catch (IOException e) {
			System.err.println("Error al cargar clientes: " + e.getMessage());
		}
		return clientes;
	}

	// Método para guardar el ticket en un archivo de texto
	public static void guardarTicket(String contenido) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_TIKET, true))) {
			pw.println(contenido);
			System.out.println("La escritura en el fichero se ha completado con éxito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}