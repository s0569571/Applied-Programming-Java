package starter;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.EventListener;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Adresse;
import entities.Anrede;
import entities.Bezahlmethode;
import entities.Flugreservierung;
import entities.Geschaeftskunde;
import entities.Hotelreservierung;
import entities.Kasse;
import entities.Kunde;
import entities.Privatkunde;
import entities.Reiseagentur;
import entities.Reservierung;
import entities.SortByName;

import observerpattern.Buchhaltung;
import observerpattern.Zahlung;
import strategy.BezahlStrategie;
import strategy.ECKarte;
import strategy.Kreditkarte;
import strategy.RechnungZahlen;
import util.Init;
import util.Validator;

/**
 * @author Nico Schultze
 *version 1.2
 *Starter datei mit menue.
 */

public class Starter extends JFrame implements Serializable, ActionListener,ListSelectionListener {

	private static Scanner scan = new Scanner(System.in);
	private static Reiseagentur magicHolidays;
	public static void main(String[] args) throws Exception {

		Adresse adresseHotel = new Adresse("Hauptstraße", "5a", "10559", "Berlin");
		magicHolidays = new Reiseagentur("Magic Holidays Reiseagentur", "DE812524001", adresseHotel);

		Init.initReiseagentur(magicHolidays);
		System.out.println(magicHolidays);
		boolean auswahl = true;
		
		while (auswahl == true) {
			System.out.println("Wie möchten Sie das Programm starten? Wählen Sie eine der folgenden Optionen.");
			System.out.println("1. CLI - Console.");
			System.out.println("2. GUI - Graphische Oberfläche.");
			String eingabe = scan.nextLine();
			
		if (eingabe.equalsIgnoreCase("1")||eingabe.equalsIgnoreCase("eins")||eingabe.equalsIgnoreCase("CLI")||eingabe.equalsIgnoreCase("Console")) {
			showMenue();
			int choice = readUserInput();
			processUserInput(choice);
		} else if (eingabe.equalsIgnoreCase("2")||eingabe.equalsIgnoreCase("zwei")||eingabe.equalsIgnoreCase("GUI")) {
			new Starter();
			auswahl = false;
		}else {
		System.out.println("Bitte nur folgende Eingaben tätigen.");
		}
			
		}
	}

	private static void showMenue() {

		String menuEntries[] = { "Privatkunde anlegen.", "Geschäftskunde anlegen.",
				"Reservierung anlegen und Kundennummer zuordnen.",
				"Kunde mit Reservierungen anzeigen (Auswahl durch Kundennummer).",
				"Kunde mit Reservierungen anzeigen (Auswahl durch Name).",
				"Reservierung anzeigen (Auswahl durch Reservierungsnummer).",
				"Alle Kunden sortiert nach aufsteigendem Namen anzeigen.",
				"Bezahlmethoden sortiert nach absteigender Häufigkeit zeigen.",
				"Reservierungen eines Datums sortiert nach Nachnamen.", "Daten exportieren.",
				"Daten importieren.","Kunden nach Namen sortiert als CSV-Datei exportieren.", 
				"Reservierung Checkout.","Buchhaltungsliste anzeigen.", "Auditingliste anzeigen.",
				"Beenden."};
				

		System.out.println("Menu");
		System.out.println("=====");

		for (int i = 0; i < menuEntries.length; i++) {
			System.out.println((i + 1) + ".\t" + menuEntries[i]);
		}

	}

	private static int readUserInput() {
		
		int choice = -1;

		System.out.print("\nAuswahl:" + "\t");

		if (scan.hasNextLine()) {
			String stringChoice = scan.nextLine();
			try {

				choice = Integer.parseInt(stringChoice);
			} catch (NumberFormatException nfe) {
				System.err.println(nfe.getMessage());
			}
		}
		System.out.println();

		return choice;
	}

	private static void processUserInput(int choice) throws Exception {

		switch (choice) {
		case 1:
			privatkundeAnlegen();
			break;
		case 2:
			geschaeftskundeAnlegen();
			break;
		case 3:
			rsvAnlegen();
			break;
		case 4:
			long kndNr = readCustomerNr();
			kundeMitRsvAnzeigen1(kndNr);
			break;
		
		case 5:
			String nachname = readLastName();
			kundeMitRsvAnzeigen2(nachname);
			break;
		case 6:
			reservierungSuchen();
			break;
		case 7:
			kundenAnzeigen();
			break;
		case 8:
			bzmAnzeigen();
			
			break;
		case 9:
			RsvEinesDatums();
			break;
			
		case 10:
			exportiereDaten(magicHolidays);
			
			break;
		case 11:
			String path=readPath();
			importiereDaten(path);
			break;
		case 12:
			exportiereAlsCSV();
			break;
		case 13:			
			rsvCheckout();
			break;
		case 14:
			showBhList();
			break;
		case 15:
			showAuditingList();
			break;
		case 16:
			quitApp();
			break;
			
		default: {
			System.out.println("Invalider Input!");
			scan.reset();
		}

		}
	}
	
	//Punkt 1
	
	/**
	 * 
	 */
	private static void geschaeftskundeAnlegen() {
		Anrede anrede = null;
		boolean check = true;
		do {
		
		System.out.println("Herr/Frau");

		String gender = scan.next();

		if (gender.equalsIgnoreCase("Mann") || gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("H")||gender.equalsIgnoreCase("Herr")) {
			anrede = Anrede.Herr;
			check =false;
		} else if(gender.equalsIgnoreCase("Frau") || gender.equalsIgnoreCase("F")) {
			anrede = Anrede.Frau;
			check =false;

		}else {System.out.println("Bitte nur Herr/Frau eingeben.");}
		
		} while (check==true);

		String vorname = readFirstName();
		String nachname = readLastName();
		LocalDate geburtsdatum = readLocalDate();
		String eMail = readEmail();
		String telNr = readPhone();
		String straße = readStreet();
		String hausnummer = readHomeNr();
		String ort = readCity();
		String plz = readPostCode();
		String firmenname = readCompanyName();


		Adresse adresse = new Adresse(straße, hausnummer, plz, ort);

		Kunde gKunde = new Geschaeftskunde(anrede, vorname, nachname, geburtsdatum, adresse , eMail, telNr, firmenname);
		
		magicHolidays.addKunde(gKunde);
		System.out.println("Der Geschäftskunde wurde erfolgreich angelegt und dem System hinzugefügt.");
		scan.nextLine();
	}
	
	//Punkt 2
   /**
	 * 
	 */
	private static void privatkundeAnlegen() {
		Anrede anrede = null;
		boolean check = true;
		do {
		
		System.out.println("Herr/Frau");

		String gender = scan.next();

		if (gender.equalsIgnoreCase("Mann") || gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("H")||gender.equalsIgnoreCase("Herr")) {
			anrede = Anrede.Herr;
			check =false;
		} else if(gender.equalsIgnoreCase("Frau") || gender.equalsIgnoreCase("F")) {
			anrede = Anrede.Frau;
			check =false;

		}else {System.out.println("Bitte nur Herr/Frau eingeben.");}
		
		} while (check==true);
	


		String vorname = readFirstName();
		String nachname = readLastName();
		LocalDate geburtsdatum = readLocalDate();
		String eMail = readEmail();
		String telNr = readPhone();
		String straße = readStreet();
		String hausnummer = readHomeNr();
		String ort = readCity();
		String plz = readPostCode();
	

		Adresse adresse = new Adresse(straße, hausnummer, plz, ort);

		Kunde pKunde = new Privatkunde(anrede, vorname, nachname, geburtsdatum, adresse, eMail, telNr);
		magicHolidays.addKunde(pKunde);
		System.out.println("Der Privatkunde wurde erfolgreich angelegt und dem System hinzugefügt.");
		scan.nextLine();
	}
	
// Punkt 3
	/**
	 * Die Methode legt eine Reservierung an.
	 * @throws Exception 
	 * 
	 */
	public static void rsvAnlegen() throws Exception {
		System.out.println("Wenn Sie eine Flugreservierung anlegen wollen drücken Sie die [1].");
		System.out.println("Wenn Sie eine Hotelreservierung anlegen wollen drücken Sie die [2].");
		byte eingabe = scan.nextByte();

		do {

			if (eingabe == 1) {
				flugreservierungAnlegen();

			} else if (eingabe == 2) {
				hotelreservierungAnlegen();
			} else {
				System.out.println("Bitte wählen Sie eine korrekte Eingabe.");
			}
		} while (eingabe != 1 && eingabe != 2);
	}

	
	/**
	 * Die Methode legt eine Flugreservierung an.
	 * @throws Exception 
	 * 
	 */
	public static void flugreservierungAnlegen() throws Exception {
	    Iterator<Kunde> iterator = magicHolidays.getKundenSet().iterator();
		LocalDate fromDate = readLocalDate();
		LocalDate toDate = readLocalDate();
		double summe = readSum();
		String abflughafen = readStartAirP();
		String zielhafen = readEndAirP();
		String flugNr = readFlightNr();

		Flugreservierung fRsv = new Flugreservierung(abflughafen, zielhafen, flugNr, fromDate, toDate);
	    fRsv.setSumme(summe);

		System.out.println("Die Flugreservierung wurde angelegt. Ordnen Sie nun mit Ihrer naechsten Eingabe eine Kundennummer hinzu.");
		long kundenNr = readCustomerNr();

		for (Kunde kunde : magicHolidays.getKundenSet()) {
			if (kunde.getKundennummer()==kundenNr) {
				System.out.println(kunde);
				kunde.getReservierungsListe().add(fRsv);
				System.out.println(kunde);
			}
			
			
		}
		scan.nextLine();
	}

	/**
	 * Die Methode legt eine Hotelreservierung an.
	 * @throws Exception 
	 * 
	 */
	public static void hotelreservierungAnlegen() throws Exception {
		LocalDate fromDate = readLocalDate();
		LocalDate toDate = readLocalDate();
		double summe = readSum();
		String hotelname = readHotelName();
		Hotelreservierung hRsv = new Hotelreservierung(hotelname, fromDate, toDate);
	    hRsv.setSumme(summe);
	
		System.out.println("Die Hotelreservierung wurde angelegt. Ordnen Sie nun mit Ihrer naechsten Eingabe eine Kundennummer hinzu.");
		long kundenNr = readCustomerNr();


			for (Kunde kunde : magicHolidays.getKundenSet()) {
				if (kunde.getKundennummer()==kundenNr) {
					System.out.println(kunde);
					kunde.getReservierungsListe().add(hRsv);
					System.out.println(kunde);
				}
				
				
			}
			scan.nextLine();

	}

	// Punkt 4
	/**
	 * Die Methode zeigt den Kunden und die Reservierungen dieses an.
	 * 
	 * @param kndNr:dabei wird die Kundennummer zur Auswahl eingegeben.
	 */
	public static void kundeMitRsvAnzeigen1(long kndNr) {
		

		for (Kunde kunde : magicHolidays.getKundenSet()) {
			if (kndNr==kunde.getKundennummer()) {
				System.out.println(kunde);
			}
		}
		scan.nextLine();
		

	}
	
	// Punkt 5

	/**
	 * Die Methode zeigt den Kunden und die Reservierungen dieses an.
	 * 
	 * @param nachname:dabei wird der Nachname zur Auswahl eingegeben.
	 */
	public static void kundeMitRsvAnzeigen2(String nachname) {


		for (Kunde kunde : magicHolidays.getKundenSet()) {
			if (kunde.getNachname().equalsIgnoreCase(nachname)) {
				System.out.println(kunde);
			}
		}
		scan.nextLine();
		
	
	}


	
	//Punkt 6
	
	/**
	 * @throws Exception 
	 * 
	 */
	private static void reservierungSuchen() throws Exception {
		System.out.println("Reservierung anzeigen (Auswahl durch Reservierungsnummer)");
		System.out.print("Reservierungsnummer:\t");
		long reservierungsnummer = -1;
		String reservierungsnummerAlsString = scan.nextLine();
		try {
			reservierungsnummer = Long.parseLong(reservierungsnummerAlsString);
		} catch (NumberFormatException exc) {
			System.err.println("Invalider Input " + reservierungsnummerAlsString);
			processUserInput(6);
			return;
		}

		boolean isValidInput = Validator.isValidReservierungsnummer(reservierungsnummer);
		if (!isValidInput) {
			System.err.println("Reservierungsnummer " + reservierungsnummer + " ist ungueltig");
			processUserInput(6);
			return;
		}

		Reservierung reservierung = magicHolidays.reservierungSuchen(reservierungsnummer);
		if (reservierung == null)
			System.out.println("Reservierung mit der Reservierungsnummer " + reservierungsnummerAlsString
					+ " koennte nicht gefunden werden");
		else
			System.out.println(reservierung);
          
	}
	
//Punkt 7
	/**
	 * Die Methode zeigt alle Kunden sortiert nach aufsteigendem Vornamen,
	 * aufsteigendem Nachnamen an.
	 * 
	 * @param 
	 */
	public static void kundenAnzeigen() {
			
		for (Kunde kunde : magicHolidays.getKundenSet()) {
			System.out.println(kunde);
		}
		

	}
	
	//Punkt 8
	/**
	 * Die Methode zeigt alle Bezahlmethoden nach absteigendert Häufigkeit an.
	 * 
	 * 
	 */
	public static void bzmAnzeigen() {
		int countKC=0;
		int countEC=0;
		int countPP=0;
		int countRechnung=0;
		System.out.println("Hauefigkeit der Bezahlmethoden: ");
		for (Kunde kunde : magicHolidays.getKundenSet()) {
			
				for (Bezahlmethode methode : kunde.getBezahlmethode()) {
					
		
					if (methode.getBezeichnung().equalsIgnoreCase("Rechnung")){
							countRechnung++;
							
						}else if(methode.getBezeichnung().equalsIgnoreCase("PayPal")){
							countEC++;
							
						}else if(methode.getBezeichnung().equalsIgnoreCase("EC-Karte")){
							countPP++;
							
						}else if(methode.getBezeichnung().equalsIgnoreCase("Kreditkarte")){
							countKC++;
						
						}else {
							System.out.println("Irgendwas stimmt nicht.");
						}
		
			
		
		}
				
	}
		System.out.println(countRechnung + "- Rechnung");
		System.out.println(countEC + "- EC-Karte " );
		System.out.println(countPP + "- PayPal " );
     	System.out.println(countKC + "- Kreditkarte " );
	}
	
	
	
	//punkt9
	/**
	 * Die Methode zeigt Reservioerungen eines Datums an.
	 * 
	 * 
	 * 
	 */
	public static void RsvEinesDatums() {
		System.out.println("Bitte einmal enter drücken.");
		LocalDate date = readLocalDate();
		for (Kunde kunde : magicHolidays.getKundenSet()) {
			for (Reservierung reservierung : kunde.getReservierungsListe()) {
				if (date.equals(reservierung.getFromDate())) {
					System.out.println(kunde.getName()+ " - " + reservierung);
			}
			
		}
		
		
		}
			
	}

	//Punkt 10
	/**
	 * This method saves and exports the data within the hospital to an external
	 * directory.
	 * 
	 * @throws IOException
	 */
	public static void exportiereDaten(Reiseagentur magicHolidays) throws IOException {

		String pfad = readPath();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(pfad));
		System.out.println("Die Daten wurden erfolgreich exportiert");
		objectOutputStream.writeObject(magicHolidays);
		objectOutputStream.close();
		scan.nextLine();
	}

	//Punkt 11
	/**
	 * This method imports data from an external directory to the hospital.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public static void importiereDaten(String pfad) throws IOException, ClassNotFoundException {

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pfad));
		magicHolidays = (Reiseagentur) objectInputStream.readObject();
		System.out.println(magicHolidays);
		objectInputStream.close();
		scan.nextLine();
	}

	//Punkt 12
	/**
	 * This method saves and exports the data from all the customers within the agency
	 * to an external directory, sorted by the name.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void exportiereAlsCSV() throws FileNotFoundException, IOException {

		String pfad = readPath();
		String fullPath = pfad + ".csv";

		FileOutputStream fos = new FileOutputStream(fullPath, true);
		PrintWriter pw = new PrintWriter(fos);

		pw.println("Geschlecht; Name; Straße; Hausnummer; Wohnort; HandyNr; E-mail; Kundennummer; ");
		for (Kunde kunden : magicHolidays.getKundenSet()) {
			pw.println(kunden);
		}
		pw.close();
		System.out.println("Die Datensätze wurden in die Datei " + fullPath + " exportiert");
		scan.nextLine();
	}


	//Punkt 13
		/**
		 * Die Methode fragt nach der Bezahlungsmethode und beendet den Zahlungsprozess.
		 * 
		 */
	public static void rsvCheckout() {
		long rsvNr = sucheRsvNr();
		double betrag = ermittleBetrag(rsvNr);
		Kasse kasse = magicHolidays.getKasse();
	    BezahlStrategie strategie =null;
	    

		System.out.println("Wie soll die Summe "+ betrag +" Euro, der Reservierung NR:" + rsvNr +" bezahlt werden?" );
		System.out.println("1. Kreditkarte");
		System.out.println("2. ECKarte");
		System.out.println("3. Rechnung");
		int eingabe = scan.nextInt();
		
		switch (eingabe) {
		case 1: 
			strategie = new Kreditkarte();
			kasse.setBezahlStrategie(strategie);
			kasse.bezahlen(betrag);
			break;
        case 2: 
        	strategie = new ECKarte();
        	kasse.setBezahlStrategie(strategie);
			kasse.bezahlen(betrag);
			break;
        case 3: 
        	strategie = new RechnungZahlen();
            kasse.setBezahlStrategie(strategie);
			kasse.bezahlen(betrag);
	break;
		default:
			System.out.println("Bitte Zahlen 1-3 eingeben.");
			break;
		}
		

		
		Zahlung zahlung = new Zahlung();
		//betrag aus reservierung hinzufügen, und bzmethode aus scanner übergeben
		zahlung.erfasseZahlung(betrag, strategie);
		
	}
	
	//Punkt 14
		/**
	      * Die Methode zeigt alle Betraege und Bezahlmethoden der Buchhaltung
		 */
	public static void showBhList() {
		
		System.out.println("Die Beträge der Buchhaltungsliste:");
		for (  Double betrag : magicHolidays.getBuchhaltung().getBetraege()) {
			System.out.println(betrag);
		}
		
		System.out.println("Die Bezahlmethoden der Buchhaltungsliste:");
		for (  BezahlStrategie bzStrategie : magicHolidays.getBuchhaltung().getBzStrategie()) {
			System.out.println(bzStrategie);
		}
	}
	
	//Punkt 15
		/**
		 * Die Methode zeigt alle Betraege und Bezahlmethoden des Auditing.
		 * 
		 */
	public static void showAuditingList() {
		System.out.println("Die Beträge des Auditing:");
		for (  Double betrag : magicHolidays.getAuditing().getBetraege()) {
			System.out.println(betrag);
		}
		
		System.out.println("Die Bezahlmethoden des Auditing:");
		for (  BezahlStrategie bzStrategie : magicHolidays.getAuditing().getBzStrategie()) {
			System.out.println(bzStrategie);
		}
	}
	
	private static void quitApp() {
		scan.close();
		System.exit(0);
	}

	

		/**
		 * Die Methode geht alle Reservierungen durch und gib die Reservierung einer nummer wieder.
		 * 
		 * @return: die reservierungsnummer
		 */
	public static long sucheRsvNr() {
		boolean again= false;
		long rsvNr = readRsvNr();
		
			
		for (Kunde kunde : magicHolidays.getKundenSet()) {
			for (Reservierung reservierung : kunde.getReservierungsListe()) {
				if(reservierung.getReservierunsnummer() == rsvNr) {
					System.out.println(reservierung);
					return rsvNr;
				}
			}
		}
		
		return rsvNr;
	}
	
	
		/**
		 * Die Methode ermittelt den Betrag der Reservierung und  gibt ihn wieder
		 * 
		 * @return: 
		 */
	public static double ermittleBetrag(long rsvNr) {
		boolean again = false;
		double betrag =0;
	
			
			for (Kunde kunde : magicHolidays.getKundenSet()) {
				for (Reservierung reservierung : kunde.getReservierungsListe()) {
					if(reservierung.getReservierunsnummer() == rsvNr) {
					betrag =reservierung.getSumme();
					
					}
				}
			}
		
		
			return betrag;
	}
	

//-------------------------------------------------------------------------------------------------
	
	//GUI Teil des Programms 
	//Die Attribute wurden zur Übersichtlichkeit direkt hier aufgelistet.
	

	
		private JMenuBar menuBar;
		private JMenu menuInfo;
		private JLabel titel, customerLabel, customerFoundLabel,customerFoundInput, customerNrLabel, birthdateLabel, adressLabel, customerNrInput,
				birthdateInput, adressInput;
		private JTextField customerText;
		private JList<Kunde>customersJList;
		private DefaultListModel <Kunde>modelCustomers = new DefaultListModel();
		private JList<Reservierung>reservationsJList;
		private DefaultListModel<Reservierung> modelReservations = new DefaultListModel();
		private JButton searchButton;

		/**
		 * Der Konstruktor, welcher das GUI erzeugt und die Panel visualisiert.
		 * @param:
		 * @return: 
		 */
		 public Starter() {
		

			// Create the frame, position it and handle closing it

			this.setTitle("Magic Holiday Reiseagentur");
			this.setSize(750, 600);
			this.setLocation(200, 150);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);

			

			JPanel topPanel = initTopPanel();
			JPanel leftPanel = initLeftPanel();
			JPanel rightPanel = initRightPanel();

			this.getContentPane().add(topPanel, BorderLayout.NORTH);
			this.getContentPane().add(leftPanel, BorderLayout.WEST);
			this.getContentPane().add(rightPanel, BorderLayout.EAST);

			// Adjusts the size of the frame to best work for the components
			this.setVisible(true);

		}

		 
		 /**
			 * Der obere Teil des Panels wird initialisiert.
			 * @param:
			 * @return: the top Panel.
			 */
		private JPanel initTopPanel() {

			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			
// Erstellen einer Menüleiste
			menuBar = new JMenuBar();

			// Erzeugung eines Objektes der Klasse JMenu
			menuInfo = new JMenu("Info");
			
			JMenuItem about = new JMenuItem("About");
   		    about.addActionListener(this);
			about.setName("about");
			menuInfo.add(about);
			
			// Menü wird der Menüleiste hinzugefügt
			menuBar.add(menuInfo);
			
			this.setJMenuBar(menuBar);
			
			Font font = new Font("Helvetica", Font.BOLD, 25);
			titel = new JLabel("Magic Holidays Reiseagentur");
			titel.setFont(font);
			addComp(panel, titel, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

			customerLabel = new JLabel("  Kundenname: ");
			addComp(panel, customerLabel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

			customerText = new JTextField(30);
			addComp(panel, customerText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

			searchButton = new JButton("Suchen");
			searchButton.setName("search");
			addComp(panel, searchButton, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			searchButton.addActionListener(this);

			JLabel makeSpace = new JLabel("");
			addComp(panel, makeSpace, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			
			// Zur Veranschaulichung erstellen wir hier eine Border
			Border bo = new LineBorder(Color.black);
			panel.setBorder(bo);
			
			return panel;
		}

		
		
		/**
		 * Die linke Hälfte des GUI wird initialisiert.
		 * @param:
		 * @return: the left Panel.
		 */
		private JPanel initLeftPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			customersJList = new JList(modelCustomers);
			customersJList.setVisibleRowCount(4);
			JScrollPane kundeScroll = new JScrollPane(this.customersJList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			addComp(panel, kundeScroll, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			customersJList.setFixedCellHeight(93);
			customersJList.setFixedCellWidth(342);
			
			for (Kunde customer : magicHolidays.getKundenSet()) {
		 	this.modelCustomers.addElement(customer);
				}
				
				
			customerFoundLabel = new JLabel("Kunden gefunden:");
			addComp(panel, customerFoundLabel, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			
			customerFoundInput = new JLabel(""+modelCustomers.size());
			addComp(panel, customerFoundInput, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			
			Border bo = new LineBorder(Color.black);
			panel.setBorder(bo);
			
			this.customersJList.addListSelectionListener(this);
			return panel;
		}

		
		/**
		 * Die rechte Hälfte des GUI wird initialisiert.
		 * @param:
		 * @return: the right Panel.
		 */
		private JPanel initRightPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			

			customerNrLabel = new JLabel(" Kundennummer:");
			addComp(panel, customerNrLabel, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

			customerNrInput = new JLabel("");
			customerNrInput.setName("Kundennummer");
			addComp(panel, customerNrInput, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

			birthdateLabel = new JLabel(" Geburtsdatum:");
			addComp(panel, birthdateLabel, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

			birthdateInput = new JLabel("");
			birthdateInput.setName("Geburtsdatum");
			addComp(panel, birthdateInput, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

			adressLabel = new JLabel(" Adresse:");
			addComp(panel, adressLabel, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

			adressInput = new JLabel("");
			adressInput.setName("Adresse");
			addComp(panel, adressInput, 1, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);


			reservationsJList = new JList(modelReservations);
			reservationsJList.setVisibleRowCount(4);
			JScrollPane reservationScroller = new JScrollPane(reservationsJList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			addComp(panel, reservationScroller, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			reservationsJList.setFixedCellHeight(80);
			reservationsJList.setFixedCellWidth(342);
			
			Border bo = new LineBorder(Color.black);
			panel.setBorder(bo);
			return panel;
		}

		
		/**Methode welche die Komponente dem Panel hinzufügt und über das GridBagLayout positioniert.
		 * 
		 * @param: das Panel, die hinzugefügte KOmponente, x und y Position, die Weite und Höhe, sowie stretch und fill.
		 * @return: 
		 */
		private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
				int stretch) {

			GridBagConstraints gridConstraints = new GridBagConstraints();

			gridConstraints.gridx = xPos;
			gridConstraints.gridy = yPos;
			gridConstraints.gridwidth = compWidth;
			gridConstraints.gridheight = compHeight;
			gridConstraints.weightx = 0;
			gridConstraints.weighty = 0;
			gridConstraints.insets = new Insets(5, 5, 5, 5);
			gridConstraints.anchor = place;
			gridConstraints.fill = stretch;

			thePanel.add(comp, gridConstraints);

		}

		
		/**Methode, welche beim auswählen eines Kunden die Reservierungen dieses Kunden anzeigt, sowie dessen Kundennummer, Adresse und Geburtsdatum.
		 * eingegebenen Person gefiltert und eine Kundenliste angezeigt.
		 * Außerdem wird bei dem Klick auf "about" die Matrikelnummer und Name angezeigt.
		 * 
		 * @param:das Event.
		 * @return: 
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
				Kunde kundenListe = (Kunde) this.customersJList.getSelectedValue();
				for(Kunde kunde : magicHolidays.getKundenSet()) {
					if(kunde.equals(kundenListe)) {
						this.modelReservations = addToModelReservation(kunde);
						this.reservationsJList.setModel(this.modelReservations);
						this.customerNrInput.setText(kunde.getKundennummerString());
						this.birthdateInput.setText(kunde.getGeburtsdatum().toString());
						this.adressInput.setText(kunde.getAdresse().toString());
						
						
					}//end of inner if
				}//end of for each
			}//end of outer if			
			
		}

		
		/**Methode, welche bei einem Event eine bestimmte Aktion ausführt. Wenn der Button "suchen" ausgewählt wird, wird nach der 
		 * eingegebenen Person gefiltert und eine Kundenliste angezeigt.
		 * Außerdem wird bei dem Klick auf "about" die Matrikelnummer und Name angezeigt.
		 * 
		 * @param: das action Event.
		 * @return: 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton actionButton = (JButton)e.getSource(); 
			if(actionButton.getName().equalsIgnoreCase("search")||actionButton.getName().equalsIgnoreCase("suchen")) {

				this.modelCustomers = compareStringOfList();
					this.customersJList.setModel(modelCustomers);
					this.customerFoundInput.setText(""+modelCustomers.size());

			}
			}else if(e.getSource() instanceof JMenuItem) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				if(menuItem.getName().equalsIgnoreCase("about")) {
					JOptionPane.showMessageDialog(this, "Nico Schultze\ns0569571");
				}
			}
		}
	
	
		/**Methode welche die Reservierungsliste der ausgewählten Person füllt.
		 * 
		 * 
		 * @return: die gefüllte Liste mit den Reservierungen der ausgewählten Person.
		 */
		public DefaultListModel<Reservierung> addToModelReservation(Kunde kunde) {
			this.modelReservations.clear();
			this.reservationsJList.clearSelection();
			for (Kunde customer : magicHolidays.getKundenSet()) {
				
				for (Reservierung reservation : customer.getReservierungsListe()) {
					if (kunde.equals(customer)) {
						modelReservations.addElement(reservation);
				}	
			}
				}
			this.reservationsJList = new JList(modelReservations);
			return modelReservations;
		}
		
		
		/**Methode welche die Eingabe in das Textfeld mit den Namen in der KundenListe vergleicht und die passenden der Liste hinzufügt und anzeigt.
		 * Wird nach jedem Durchlauf wieder geleert um das aktualisieren der Liste zu ermöglichen.
		 * 
		 * @param:
		 * @return: die gefüllte DefaultList.
		 */
		public DefaultListModel<Kunde> compareStringOfList() {
			String input = customerText.getText();
			this.modelCustomers.clear();
			this.customersJList.clearSelection();
			for (Kunde customer : magicHolidays.getKundenSet()) {
				if (customer.getVorname().contains(input)||customer.getNachname().contains(input)) {
					this.modelCustomers.addElement(customer);
				}
				
				
			}
			return modelCustomers;
		}
		
		
		
		
	//EINGABE METHODEN-----------------------------------------
	
	
	public static String readFirstName() {
		scan.nextLine();
		System.out.println("Wie lautet der vorname des Kunden?");
		return scan.nextLine();
	}

	public static String readLastName() {
		System.out.println("Wie lautet der Nachname des Kunden?");
		return scan.next();
	}

	public static String readEmail() {
		System.out.println("Geben Sie die E-mail des Kunden ein.");
		return scan.next();
	}

	public static String readPhone() {
		System.out.println("Wie lautet die Handynummer?");
		return scan.next();

	}

	public static String readStreet() {
		System.out.println("Geben Sie die Straße ein.");
		return scan.next();
	}

	public static String readHomeNr() {
		System.out.println("Geben Sie die Hausnummer ein.");
		return scan.next();
	}

	public static String readCity() {
		System.out.println("Bitte geben Sie die Stadt ein.");
		return scan.next();
	}

	public static String readPostCode() {
		System.out.println("Geben Sie die PLZ ein.");
		return scan.next();
	}

	public static long readCustomerNr() {
		System.out.println("Geben Sie die Kundennummer ein.");
	return scan.nextLong();
		}

	public static String readCompanyName() {
		scan.nextLine();
		System.out.println("Nennen Sie die Firma des Geschaeftskunden.");
		return scan.nextLine();
	}

	public static long readRsvNr() {
		System.out.println("Nennen Sie die Reservierungsnummer.");
		return scan.nextLong();
	}
	
	private static LocalDate readLocalDate() {
		scan.nextLine();
		LocalDate day = null;
		while (day == null) {

			System.out.print("Datum (tt.mm.jjjj)" + ":\t");

			if (scan.hasNextLine()) {
				String stringDay = scan.nextLine();
				try {
					day = LocalDate.parse(stringDay,
							DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN));
				} catch (DateTimeParseException exc) {
					System.err.println(exc.getMessage());
				}
			}

		}
		return day;
	}

	public static double readSum() {
		System.out.println("Geben Sie die Summe der Reservierung an.");
		return scan.nextDouble();
	}

	public static String readStartAirP() {
		System.out.println("Nennen Sie den Abflughafen.");
		return scan.next();
	}

	public static String readEndAirP() {
		System.out.println("Nennen Sie den Endflughafen.");
		return scan.next();
	}

	public static String readFlightNr() {
		System.out.println("Nennen Sie die Flugnummer.");
		return scan.next();
	}

	public static String readHotelName() {
		System.out.println("Nennen Sie den Hotelnamen.");
		return scan.next();
	}

	public static String readPath() {
		System.out.println("Bitte geben Sie den Dateipfad an.");
		return scan.next();
	}

	

	
}
//