
package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @user Timur Bogach
 * @date 8 may 2024
 *
 *       Si el elemento es perecedero, la fecha de caducidad será diez días más
 *       de la fecha de envase.
 * 
 *       Si no lo es sólo devuelve la fecha de caducidad.
 *
 */
public class Comida extends Producto {
	private boolean perecedero;
	private float calorias;
	private boolean vegano;
	private Calendar fechaEnvase;

	@Override
	public String detalleProducto() {
		StringBuilder resultado = new StringBuilder();
		Calendar obtenerCaducidad = this.obtenerCaducidad(); // Utiliza la fecha de caducidad del producto actual
		Calendar fechaActual = Calendar.getInstance();
		long diferenciaDias = TimeUnit.MILLISECONDS
				.toDays(obtenerCaducidad.getTimeInMillis() - fechaActual.getTimeInMillis());

		if (diferenciaDias <= 0) {
			this.setEstado("CADUCADO");
		} else if (diferenciaDias < 5) {
			resultado.append(mostrarConDescuento());
		} else {
			resultado.append(mostrarSinDescuento());
		}
		return resultado.toString();
	}

	@Override
	public String mostrarSinDescuento() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n**Comida:** ").append(getNombre()).append(" (").append(getPrecio()).append("€)\n");
		// sb.append(" - Estado: ").append(getEstado()).append("\n");
		sb.append(" - Perecedero: ").append(isPerecedero() ? "Sí" : "No").append("\n");
		sb.append(" - Calorías: ").append(getCalorias()).append(" kcal\n");
		sb.append(" - Vegano: ").append(isVegano() ? "Sí" : "No").append("\n");
		sb.append(" - Fecha de envasado: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(getFechaEnvase().getTime())).append("\n");

		sb.append(" - Fecha de caducidad: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(obtenerCaducidad().getTime())).append("\n");
		return sb.toString();// Caducidad modificada
	}

	@Override
	public String mostrarConDescuento() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nOFERTA DEL 30%!\n");
		sb.append("**Comida:** ").append(getNombre()).append(" (")
				.append(Math.round((getPrecio() * 100) / 100f) * 0.70f).append("€)\n");
		// sb.append(" - Estado: ").append(getEstado()).append("\n");
		sb.append(" - Perecedero: ").append(isPerecedero() ? "Sí" : "No").append("\n");
		sb.append(" - Calorías: ").append(getCalorias()).append(" kcal\n");
		sb.append(" - Vegano: ").append(isVegano() ? "Sí" : "No").append("\n");
		sb.append(" - Fecha de envasado: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(getFechaEnvase().getTime())).append("\n");

		sb.append(" - Fecha de caducidad: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(obtenerCaducidad().getTime())).append("\n");
		return sb.toString();// Caducidad modificada
	}

	@Override
	public Calendar obtenerCaducidad() {
		Calendar fechaResultado = (Calendar) this.fechaCaducidad.clone();
		if (this.perecedero) {
			fechaResultado.add(Calendar.DAY_OF_MONTH, 10);
		}
		return fechaResultado;
	}

	public Comida(String nombre, Float precio, Calendar fechaCaducidad, String estado, boolean perecedero,
			float calorias, boolean vegano, Calendar fechaEnvase) {
		super(nombre, precio, fechaCaducidad);
		this.perecedero = perecedero;
		this.calorias = calorias;
		this.vegano = vegano;
		this.fechaEnvase = fechaEnvase;
	}

	public boolean isPerecedero() {
		return perecedero;
	}

	public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}

	public Calendar getPerecedero() {
		return fechaCaducidad;
	}

	public float getCalorias() {
		return calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public Calendar getFechaEnvase() {
		return fechaEnvase;
	}

	public void setFechaEnvase(Calendar fechaEnvase) {
		this.fechaEnvase = fechaEnvase;
	}

	@Override
	public String toString() {
		return "Comida [perecedero=" + perecedero + ", calorias=" + calorias + ", vegano=" + vegano + ", fechaEnvase="
				+ fechaEnvase + "]";
	}

}
