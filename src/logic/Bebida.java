
package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @user Timur Bogach
 * @date 8 may 2024
 *
 *       Si el producto es lácteo, la fecha de caducidad se saca sumando diez
 *       días a la que tiene el alimento.
 * 
 *       En otro caso fecha de caducidad + 20 días.
 *       ----------------------------------------------------------------- Si el
 *       producto es lácteo, la fecha de caducidad se saca sumando diez días a
 *       la que tiene el alimento.
 * 
 *       En otro caso fecha de caducidad + 20 días.
 */
public class Bebida extends Producto {
	private boolean gaseoso;
	private boolean lacteo;
	private String medida; // medida en CC de la bebida.

	@Override
	public String detalleProducto() {
		StringBuilder resultado = new StringBuilder();

		Calendar obtenerCaducidad = this.obtenerCaducidad();
		Calendar frechaActual = Calendar.getInstance();
		long diferenciaDias = TimeUnit.MILLISECONDS
				.toDays(obtenerCaducidad.getTimeInMillis() - frechaActual.getTimeInMillis());

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
		sb.append("\n**Bebida:** ").append(getNombre()).append(" (").append(getPrecio()).append("€)\n");
		// sb.append(" - Estado: ").append(getEstado()).append("\n");
		sb.append(" - Gaseoso: ").append(isGaseoso() ? "Sí" : "No").append("\n");
		sb.append(" - Lacteo: ").append(isLacteo() ? "Sí" : "No").append("\n");
		sb.append(" - Medida: ").append(getMedida()).append("\n");
		sb.append(" - Fecha de caducidad: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(obtenerCaducidad().getTime())).append("\n");
		return sb.toString();// Caducidad modificada
	}

	@Override
	public String mostrarConDescuento() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nOFERTA DEL 30%!\n");
		sb.append("**Bebida:** ").append(getNombre()).append(" (")
				.append(Math.round((getPrecio() * 100) / 100f) * 0.70f).append("€)\n");
		// sb.append(" - Estado: ").append(getEstado()).append("\n");
		sb.append(" - Gaseoso: ").append(isGaseoso() ? "Sí" : "No").append("\n");
		sb.append(" - Lacteo: ").append(isLacteo() ? "Sí" : "No").append("\n");
		sb.append(" - Medida: ").append(getMedida()).append("\n");
		sb.append(" - Fecha de caducidad: ")
				.append(new SimpleDateFormat("dd/MM/yyyy").format(obtenerCaducidad().getTime())).append("\n");
		return sb.toString();// Caducidad modificada
	}

	@Override
	public Calendar obtenerCaducidad() {
		Calendar fechaAjustada = (Calendar) this.fechaCaducidad.clone();
		if (this.lacteo) {
			fechaAjustada.add(Calendar.DAY_OF_MONTH, 10);
		} else {
			fechaAjustada.add(Calendar.DAY_OF_MONTH, 20);
		}
		return fechaAjustada;
	}

	public Bebida(String nombre, Float precio, Calendar fechaCaducidad, String estado, boolean gaseoso, boolean lacteo,
			String medida) {
		super(nombre, precio, fechaCaducidad);
		this.gaseoso = gaseoso;
		this.lacteo = lacteo;
		this.medida = medida;
	}


	public boolean isGaseoso() {
		return gaseoso;
	}

	public void setGaseoso(boolean gaseoso) {
		this.gaseoso = gaseoso;
	}

	public boolean isLacteo() {
		return lacteo;
	}

	public void setLacteo(boolean lacteo) {
		this.lacteo = lacteo;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	@Override
	public String toString() {
		return "Bebida [gaseoso=" + gaseoso + ", lacteo=" + lacteo + ", medida=" + medida + "]";
	}

}
