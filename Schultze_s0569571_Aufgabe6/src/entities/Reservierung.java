package entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author Nico Schultze
 *version 1.2
 *Reservierung und deren Beschreibung.
 */
public abstract class Reservierung implements Serializable {

	private long reservierungsnummer;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double summe;
	private static long nextReservierungsnummer = 1;

	/**
	 * @param datum
	 */
	public Reservierung(LocalDate from, LocalDate to) throws Exception {
		this.fromDate = from;
		this.toDate = to;
		if (from.isAfter(to))
			throw new Exception("Invalid Input - " + from + " must be before " + to);
		this.reservierungsnummer = nextReservierungsnummer++;

	}

	/**
	 * @return the summe
	 */
	public double getSumme() {
		return this.summe;
	}

	/**
	 * @param summe the summe to set
	 */
	public void setSumme(double summe) {
		this.summe = summe;
	}

	/**
	 * @return the reservierunsnummer
	 */
	public long getReservierunsnummer() {
		return this.reservierungsnummer;
	}

	public void setFromDate(LocalDate to) {
		this.toDate = to;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setToDate(LocalDate to) {
		this.toDate = to;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservierungsnummer: ");
		builder.append(this.reservierungsnummer);
		builder.append(". ");
		if (fromDate != null) {
			builder.append("[");
			builder.append(fromDate
					.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.GERMAN)));
			builder.append(" - ");
		}
		if (toDate != null) {
			builder.append(
					toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.GERMAN)));
			builder.append("] ");
		}
		builder.append(this.summe);
		builder.append(" EURO");
		return builder.toString();
	}

}
