package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import util.Settings;

/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung des Kunden
 */
public abstract class Kunde implements Serializable{
	private long kundennummer;
	private Anrede anrede;
	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;
	private Adresse adresse;
	private String telefonnummer;
	private String email;
	private List<Reservierung> reservierungsListe = new ArrayList<Reservierung>();
	private JList<Reservierung> reservierungenJList;
	private DefaultListModel<Reservierung> modelReservierung;

	private static long nextKundennummer = 1;

	/**
	 * @param anrede
	 * @param vorname
	 * @param nachname
	 */
	public Kunde(Anrede anrede, String vorname, String nachname, LocalDate gbDatum, Adresse adresse, String telefonNr,
			String email) {
		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = gbDatum;
		this.adresse = adresse;
		this.telefonnummer = telefonNr;
		this.email = email;
        this.reservierungenJList = new JList<>();
		this.modelReservierung =  new DefaultListModel<>();
		this.kundennummer = nextKundennummer++;
	}

	public abstract String getName();

	public abstract Bezahlmethode[] getBezahlmethode(); // <- soll nicht mehr existieren laut aufgabe 5

	/**
	 * @return the anrede
	 */
	public Anrede getAnrede() {
		return this.anrede;
	}

	/**
	 * @param anrede the anrede to set
	 */
	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return this.vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return this.nachname;
	}

	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the geburtsdatum
	 */
	public LocalDate getGeburtsdatum() {
		return this.geburtsdatum;
	}

	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
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
	 * @return the telefonnummer
	 */
	public String getTelefonnummer() {
		return this.telefonnummer;
	}

	/**
	 * @param telefonnummer the telefonnummer to set
	 */
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the kundennummer
	 */
	public long getKundennummer() {
		return this.kundennummer;
	}

	public List<Reservierung> getReservierungsListe() {
		return reservierungsListe;
	}
	
	
	
	public JList<Reservierung> getReservierungenJList() {
		return reservierungenJList;
	}


	

	public void setReservierungsListe(List<Reservierung> reservierungsListe) {
		this.reservierungsListe = reservierungsListe;
	}
	
	

	public boolean addReservierung(Reservierung reservierung) {
		return reservierungsListe.add(reservierung);
	}
	
	public String getKundennummerString() {
	return Objects.toString(kundennummer);
	}
	
	
//	public DefaultListModel<Reservierung> getDefaultListModel(){
//		for (Kunde customer : magicHolidays.getKundenSet()) {
//			for (Reservierung reservation : customer.getReservierungsListe()) {
//				modelReservations.addElement(reservation);
//			}
//		}
//		this.reservationsJList = new JList(modelReservations);
//		return modelReservations;
//		
//	}
	

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.kundennummer);
		builder.append(": ");
		if (this.anrede != null) {

			builder.append(this.anrede == Anrede.Frau ? "Frau" : "Herr");
			builder.append(" ");
		}
		if (this.vorname != null) {
			builder.append(this.vorname);
			builder.append(" ");
		}
		if (this.nachname != null) {
			builder.append(this.nachname);
			builder.append(" ");

		}
		if (this.geburtsdatum != null) {
			builder.append("geb. am ");
			builder.append(this.geburtsdatum);
			builder.append(", ");
		}
		if (this.adresse != null) {
			builder.append("adresse: ");
			builder.append(this.adresse);
			builder.append(", ");
		}
		if (this.telefonnummer != null) {
			builder.append("Tel.: ");
			builder.append(this.telefonnummer);
			builder.append(", ");
		}
		if (this.email != null) {
			builder.append("email: ");
			builder.append(this.email);
			builder.append(", ");
		}
		if (this.reservierungsListe != null) {
			builder.append("reservierungen:\n");
			for (Reservierung reservierung : this.reservierungsListe)
				if (reservierung != null)
					builder.append("\t" + reservierung + "\n");
		}
		return builder.toString();
	}

	// FÜR DAS SORTIEREN DES SETS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((anrede == null) ? 0 : anrede.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((geburtsdatum == null) ? 0 : geburtsdatum.hashCode());
		result = prime * result + (int) (kundennummer ^ (kundennummer >>> 32));
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((reservierungsListe == null) ? 0 : reservierungsListe.hashCode());
		result = prime * result + ((telefonnummer == null) ? 0 : telefonnummer.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kunde other = (Kunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (anrede != other.anrede)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (geburtsdatum == null) {
			if (other.geburtsdatum != null)
				return false;
		} else if (!geburtsdatum.equals(other.geburtsdatum))
			return false;
		if (kundennummer != other.kundennummer)
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (reservierungsListe == null) {
			if (other.reservierungsListe != null)
				return false;
		} else if (!reservierungsListe.equals(other.reservierungsListe))
			return false;
		if (telefonnummer == null) {
			if (other.telefonnummer != null)
				return false;
		} else if (!telefonnummer.equals(other.telefonnummer))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

}
