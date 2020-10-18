package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Nico Schultze
 *version 1.2
 *Geschaeftskunde.
 */
public class Geschaeftskunde extends Kunde {

	private String firmenname;
	private Bezahlmethode bezahlmethode;

	/**
	 * Geschaeftskunde bekommt die Rechnung per Email standardmaessig.
	 * 
	 * @param anrede
	 * @param vorname
	 * @param nachname
	 */
	public Geschaeftskunde(Anrede anrede, String vorname, String nachname, LocalDate gbDatum, Adresse adresse,
			String telefonNr, String email, String firmenname) {
		super(anrede, vorname, nachname, gbDatum, adresse, telefonNr, email);
		this.firmenname = firmenname;
		this.bezahlmethode = new Bezahlmethode("Rechnung", "E-Mail");
	}

	/**
	 * @return the firmenname
	 */
	public String getFirmenname() {
		return this.firmenname;
	}

	public void rechnungMitDerPostSchicken() {
		this.bezahlmethode.setBeschreibung("Post");
	}

	public void rechnungPerEmailSchicken() {
		this.bezahlmethode.setBeschreibung("E-Mail");
	}

	public String getName() {
		return super.getVorname() + " " + super.getNachname() + " - " + this.firmenname;
	}


	

	public Bezahlmethode[] getBezahlmethode() {
		return new Bezahlmethode[] { this.bezahlmethode };
	}
	

	public void setBezahlmethoden(Bezahlmethode bezahlmethode) {
	this.bezahlmethode = bezahlmethode;
}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString() + " ");
		if (this.firmenname != null) {
			builder.append("\t\tGeschaeftskunde: [firmenname= ");
			builder.append(this.firmenname);
			builder.append(", ");
		}
		if (this.bezahlmethode != null) {
			builder.append("Bezahlmethode: ");
			builder.append(this.bezahlmethode);
			builder.append(" ]");
		}

		return builder.toString();
	}

	

	

}
