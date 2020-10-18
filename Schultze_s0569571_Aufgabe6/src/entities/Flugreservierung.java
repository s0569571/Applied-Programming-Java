package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nico Schultze
 *version 1.2
 *Flugreservierung der Kunden.
 */
public class Flugreservierung extends Reservierung {

	private String abflughafen;
	private String zielflughafen;
	private String flugnummer;

	/**
	 * @param datum
	 * @throws Exception
	 */
	public Flugreservierung(String abflughafen, String zielflughafen, String flugnummer, LocalDate fromDate,
			LocalDate toDate) throws Exception {
		super(fromDate, toDate);
		this.abflughafen = abflughafen;
		this.zielflughafen = zielflughafen;
		this.flugnummer = flugnummer;

	}

	/**
	 * @return the abflughafen
	 */
	public String getAbflughafen() {
		return this.abflughafen;
	}

	/**
	 * @return the zielflughafen
	 */
	public String getZielflughafen() {
		return this.zielflughafen;
	}

	/**
	 * @return the flugnummer
	 */
	public String getFlugnummer() {
		return this.flugnummer;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString() + " ");
		if (this.abflughafen != null) {
			builder.append("ab ");
			builder.append(this.abflughafen);
			builder.append(" ");
		}
		if (this.zielflughafen != null) {
			builder.append("nach ");
			builder.append(this.zielflughafen);
			builder.append(", ");
		}
		if (this.flugnummer != null) {
			builder.append("flugnummer ");
			builder.append(this.flugnummer);
		}
		return builder.toString();
	}

}
