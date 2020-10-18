package observerpattern;
/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung der Auditing Klasse der Reiseagentur. Diese Klasse speichert die Zahlungen und Bezahlstrategien.
 */
import java.util.ArrayList;

import java.util.List;


import strategy.BezahlStrategie;


public class Auditing implements observerpattern.Observer {
// SPEICHERT ZAHLUNGEN UBER 1000 EURO <- wie implementieren?

	// Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Auditing auditing = null;
	private ArrayList<BezahlStrategie> bzStrategie = new ArrayList<BezahlStrategie>();
	private ArrayList<Double> betraege = new ArrayList<Double>();

	public ArrayList<BezahlStrategie> getBzStrategie() {
		return bzStrategie;
	}

	
	public ArrayList<Double> getBetraege() {
		return betraege;
	}

	

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private Auditing() {
		System.out.println("Konstruktor Aufruf");
	}

	// Eine Zugriffsmethode auf Klassenebene, welches '''einmal''' ein konkretes
	// Objekt erzeugt und dieses zurückliefert.
	public static Auditing getInstance() {
		if (auditing == null)
			auditing = new Auditing(); // Aufruf Konstruktor
		else
			System.out.println("Auditing existiert bereits und wird verwendet.");

		return auditing;
	}


	public void update(double betrag, BezahlStrategie bzStrategie) {
		if (betrag > 1000) {
			System.out.println("Der Betrag "+ betrag +" wurde mit der Bezahlstrategie "+ bzStrategie +" bezahlt und liegt über 1000 Euro, daher wird dieser an das Auditing weitergereicht.");
		
		this.betraege.add(betrag);
		this.bzStrategie.add(bzStrategie);
		} else {
			System.out.println("Wird nicht im Auditing gespeichert, da der Betrag unter 1000 Euro ist.");
		}
	

	}




}
