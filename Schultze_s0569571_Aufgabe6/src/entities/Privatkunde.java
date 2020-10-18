package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import util.Settings;

/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung des Privatkunden.
 */
public class Privatkunde extends Kunde {

	private Bezahlmethode bezahlmethode[];

	/**
	 * @param anrede
	 * @param vorname
	 * @param nachname
	 */
	public Privatkunde(Anrede anrede, String vorname, String nachname, LocalDate gbDatum, Adresse adresse,
			String telefonNr, String email) {
		super(anrede, vorname, nachname, gbDatum, adresse, telefonNr, email);
		this.bezahlmethode = new Bezahlmethode[Settings.MAX_BEZAHLMETHODEN_PRO_KUNDE];
	}

	public String getName() {
		return super.getVorname() + " " + super.getNachname();
	}

	public Bezahlmethode[] getBezahlmethode() {
		return this.bezahlmethode;
	}

	public void setBezahlMethoden(Bezahlmethode[] bezahlMethoden) {
		this.bezahlmethode = bezahlMethoden;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString() + "\t\tPrivatkunde: Bezahlmethoden:");
		if (Arrays.toString(bezahlmethode) != null) {
			builder.append(Arrays.toString(bezahlmethode));
			builder.append(" ]");
		}
		return builder.toString();
	}
}
