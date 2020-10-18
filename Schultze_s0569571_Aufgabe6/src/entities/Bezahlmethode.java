package entities;

import java.io.Serializable;

/**
 * @author Nico Schultze
 *version 1.2
 *Bezahlmethode der Kunden
 */
public class Bezahlmethode implements Serializable {

	private String bezeichnung;
	private String beschreibung;

	/**
	 * @param bezeichnung
	 * @param beschreibung
	 */
	public Bezahlmethode(String bezeichnung, String beschreibung) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the bezeichnung
	 */
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return this.beschreibung;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	

	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (this.bezeichnung != null) {
			builder.append("Bezeichnung= ");
			builder.append(this.bezeichnung);
			builder.append(", ");
		}
		if (this.beschreibung != null) {
			builder.append("Beschreibung= ");
			builder.append(this.beschreibung);
			builder.append(";");

		}

		return builder.toString();
	}

}
