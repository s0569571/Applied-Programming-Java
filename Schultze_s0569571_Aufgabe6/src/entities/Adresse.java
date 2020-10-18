package entities;

import java.io.Serializable;

/**
 * @author Nico Schultze
 *version 1.2
 *ADresse für Reiseagentur und Kunden.
 */
public class Adresse implements Serializable {
	private String strasse;
	private String hausNummer;
	private String postleitzahl;
	private String ort;

	/**
	 * @param strasse
	 * @param hausNummer
	 * @param postleitzahl
	 * @param ort
	 */
	public Adresse(String strasse, String hausNummer, String postleitzahl, String ort) {
		this.strasse = strasse;
		this.hausNummer = hausNummer;
		this.postleitzahl = postleitzahl;
		this.ort = ort;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return this.strasse;
	}

	/**
	 * @return the hausNummer
	 */
	public String getHausNummer() {
		return this.hausNummer;
	}

	/**
	 * @return the postleitzahl
	 */
	public String getPostleitzahl() {
		return this.postleitzahl;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return this.ort;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (this.strasse != null) {
			builder.append(this.strasse);
			builder.append(" ");
		}
		if (this.hausNummer != null) {
			builder.append(this.hausNummer);
			builder.append(", ");
		}
		if (this.postleitzahl != null) {
			builder.append(this.postleitzahl);
			builder.append(" ");
		}
		if (this.ort != null) {
			builder.append(this.ort);
		}
		return builder.toString();
	}

}
