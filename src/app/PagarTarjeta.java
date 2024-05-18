
package app;

import java.util.NoSuchElementException;
import java.util.Scanner;

import app.ExcepcionesPersonalizadas.TarjetaInvalidaException;
import logic.Cliente;

/**
 * @user Timur Bogach
 * @date 18 may 2024
 *
 *
 */
public class PagarTarjeta {
	public static void pagar(Cliente cliente, Scanner sc) throws NoSuchElementException, NumberFormatException {

		boolean tarjetaValida = false;

		do {
			try {
				System.out.println("Introduzca su tarjeta con los espacios correspondientes: \nEj 3444 666666 55555");
				String tipo = sc.nextLine(); // Ejemplo: 3444 666666 55555
				tipo = tipo.replace(" ", "");
				int longitud = tipo.length();

				if (longitud == 15 || longitud == 16) {
					if (tipo.startsWith("3") && longitud == 15) {
						System.out.println("Gracias por pagar con American Express\n");
						tarjetaValida = true;
					} else if (tipo.startsWith("4") && longitud == 16) {
						System.out.println("Gracias por pagar con Visa\n");
						tarjetaValida = true;
					} else if (tipo.startsWith("5") && longitud == 16) {
						System.out.println("Gracias por pagar con Master Card\n");
						tarjetaValida = true;
					} else {
						throw new TarjetaInvalidaException("Número de tarjeta no válido. Inténtelo de nuevo.\n");
					}
				} else {
					throw new TarjetaInvalidaException("Número de tarjeta no válido. Inténtelo de nuevo.\n");
				}
			} catch (TarjetaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error con la entrada de la tarjeta. Por favor, inténtelo de nuevo.");
				sc.nextLine(); // Limpiar el buffer del scanner después de una excepción
			}
		} while (!tarjetaValida);
	}
}
