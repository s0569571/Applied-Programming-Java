package entities;

import util.Settings;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import observerpattern.Auditing;
import observerpattern.Buchhaltung;

/**
 * @author Nico Schultze version 1.2 Reiseagentur und Inhalte.
 */
public class Reiseagentur implements Serializable {

	private Adresse adresse;
	private String ustIdnr;
	private String name;
	private Kasse kasse;
	private Auditing auditing;
	private Buchhaltung buchhaltung;

	private Map<LocalDate, Set<Reservierung>> reservierungMap;
	private Set<Kunde> kundenSet;

	/**
	 * @param ustIdnr
	 * @param name
	 */
	public Reiseagentur(String name, String ustIdnr, Adresse adresse) {
		this.ustIdnr = ustIdnr;
		this.name = name;
		this.adresse = adresse;
		this.kasse = Kasse.getInstance();
		this.auditing = Auditing.getInstance();
		this.buchhaltung = Buchhaltung.getInstance();

		this.kundenSet = new TreeSet<Kunde>(new SortByName());
		this.reservierungMap = new TreeMap<>();
	}

	public Auditing getAuditing() {
		return auditing;
	}

	public Buchhaltung getBuchhaltung() {
		return buchhaltung;
	}

	public Kasse getKasse() {
		return kasse;
	}

	public Set<Reservierung> getReservationsMap(LocalDate day) {

		return this.reservierungMap.get(day);
	}

	public void addReservierungsMap(Reservierung reservierung) {
		LocalDate key = reservierung.getFromDate();
		Set<Reservierung> currentReservations = this.reservierungMap.get(key);

		if (currentReservations == null)
			currentReservations = new TreeSet<Reservierung>();

		currentReservations.add(reservierung);

		this.reservierungMap.put(key, currentReservations);

	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return this.adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the ustIdnr
	 */
	public String getUstIdnr() {
		return this.ustIdnr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	public Set<Kunde> getKundenSet() {
		return kundenSet;
	}

	public void setKundenSet(Set<Kunde> kundenSet) {
		this.kundenSet = kundenSet;
	}

	/**
	 * @param kunde the kunde to add
	 */
	public boolean addKunde(Kunde kunde) {
		return kundenSet.add(kunde);
	}

	/**
	 * @param reservierungsnummer
	 * @return
	 */
	public Reservierung reservierungSuchen(long reservierungsnummer) {

		for (Kunde kunde : this.kundenSet)
			if (kunde != null)
				for (Reservierung reservierung : kunde.getReservierungsListe())
					if (reservierung != null
							&& Long.compare(reservierung.getReservierunsnummer(), reservierungsnummer) == 0)
						return reservierung;
		return null;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reiseagentur: ");
		if (this.name != null) {
			builder.append(this.name);
			builder.append(" ");
		}
		if (this.ustIdnr != null) {
			builder.append(this.ustIdnr);
			builder.append("\n");
		}
		if (this.adresse != null) {
			builder.append(this.adresse);
			builder.append("\n\n");
		}

		if (this.kundenSet != null) {
			builder.append("Alle kunden:\n\n");
			for (Kunde kunde : this.kundenSet)
				if (kunde != null)
					builder.append(kunde + "\n");
		}
		builder.append("---------------");
		return builder.toString();
	}

}
