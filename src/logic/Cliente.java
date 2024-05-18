package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @user Timur Bogach
 * @date 23 dic 2023
 * 
 *       Clase Cliente que representa a un cliente en el sistema de gestión de
 *       pedidos. Incluye información personal y métodos para manejar los
 *       pedidos.
 */

public class Cliente {

	// Atributos de la clase
	private ArrayList<Cliente> ListaClientes;
	private String nombre;
	private String apellidos;
	private Calendar fechaDeAlta;
	private String telefono;
	private String direccion;
	public String historial;

	public Cliente() {
		super();
		this.ListaClientes = new ArrayList<>();
	}

	public Cliente(String nombre, String apellidos, Calendar fechaDeAlta, String telefono, String direccion,
			String historial) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaDeAlta = fechaDeAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = historial;
	}

	// Método para convertir los datos del cliente a una cadena de texto
	public String toTexto() {
		return nombre + "," + apellidos + "," + ((GregorianCalendar) fechaDeAlta).get(Calendar.YEAR) + "-"
				+ ((GregorianCalendar) fechaDeAlta).get(Calendar.MONTH) + "-"
				+ ((GregorianCalendar) fechaDeAlta).get(Calendar.DAY_OF_MONTH) + "," + telefono + "," + direccion + ","
				+ historial;
	}

	// Método para construir un cliente a partir de una cadena de texto
	public static Cliente fromTexto(String texto) {
		String[] partes = texto.split(",");
		String nombre = partes[0];
		String apellidos = partes[1];
		String[] fechaParts = partes[2].split("-");
		Calendar fechaDeAlta = new GregorianCalendar(Integer.parseInt(fechaParts[0]), Integer.parseInt(fechaParts[1]),
				Integer.parseInt(fechaParts[2]));
		String telefono = partes[3];
		String direccion = partes[4];
		String historial = partes[5];
		return new Cliente(nombre, apellidos, fechaDeAlta, telefono, direccion, historial);
	}

	public String mostrarClientesRegistrados() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ListaClientes.size(); i++) {
			Cliente cliente = ListaClientes.get(i);
			sb.append((i + 1)).append(". ").append(cliente.getNombre()).append(" ").append(cliente.getApellidos())
					.append("\n");
		}
		return sb.toString();
	}

	public void añadirCliente(Cliente cliente) {
		ListaClientes.add(cliente);
	}

	public void eliminarClientePorNombre(String nombre) {
		for (int i = 0; i < ListaClientes.size(); i++) {
			Cliente cliente = ListaClientes.get(i);
			if (cliente.getNombre().equals(nombre)) {
				ListaClientes.remove(cliente);
			}
		}
	}

	public Cliente seleccionarClientePorNombre(String nombre) {
		for (int i = 0; i < ListaClientes.size(); i++) {
			Cliente cliente = ListaClientes.get(i);
			if (cliente.getNombre().equals(nombre)) {
				return cliente;
			}
		}
		return null;
	}

	public int cantidadClientes() {
		return ListaClientes.size();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Calendar getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Calendar fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	public ArrayList<Cliente> getListaClientes() {
		return ListaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		ListaClientes = listaClientes;
	}

}
