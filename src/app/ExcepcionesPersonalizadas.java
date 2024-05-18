
package app;

/**
 * @user Timur Bogach
 * @date 18 may 2024
 *
 *
 */
public class ExcepcionesPersonalizadas {
	// Excepción para validación de teléfono
	public static class TelefonoInvalidoException extends Exception {
		public TelefonoInvalidoException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepción para controlar el importe que se paga
	public static class ImporteInvalidoException extends Exception {
		public ImporteInvalidoException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepción para validación de tarjeta
	public static class TarjetaInvalidaException extends Exception {
		public TarjetaInvalidaException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepción para validación de número de cuenta
	public static class NumeroCuentaInvalidoException extends Exception {
		public NumeroCuentaInvalidoException(String mensaje) {
			super(mensaje);
		}
	}
}
