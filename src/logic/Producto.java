
package logic;

import java.util.Calendar;

/**
 * @user Timur Bogach
 * @date 8 may 2024
 *
 *
 */
public abstract class Producto {
	private String nombre;
	private Float precio;
	protected Calendar fechaCaducidad;
	private String estado;

	public abstract Calendar obtenerCaducidad();

	public abstract String detalleProducto();

	public abstract String mostrarSinDescuento();

	public abstract String mostrarConDescuento();

	public Producto(String nombre, Float precio, Calendar fechaCaducidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.fechaCaducidad = fechaCaducidad;
		this.estado = "Disponible";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
