package util;

/**
 * @author Nico Schultze
 *version 1.2
 *Validator des Programms.
 */
public class Validator {

	public static boolean isValidReservierungsnummer(long reservierungsnummer) {

		return (reservierungsnummer > 0);

	}
}
