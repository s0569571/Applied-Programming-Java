package observerpattern;
/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung der Buchhaltung der Reiseagentur. Diese Klasse speichert die Zahlungen und Bezahlstrategien.
 */
import java.util.List;


import strategy.BezahlStrategie;

import java.util.ArrayList;

public class Buchhaltung implements observerpattern.Observer {
// SPEICHERT ALLE ZAHLUNGEN

	// Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Buchhaltung buchhaltung = null;
	private ArrayList<BezahlStrategie> bzStrategie = new ArrayList<BezahlStrategie>();
	private ArrayList<Double> betraege = new ArrayList<Double>();

	public ArrayList<BezahlStrategie> getBzStrategie() {
		return bzStrategie;
	}

	public ArrayList<Double> getBetraege() {
		return betraege;
	}

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private Buchhaltung() {
		System.out.println("Konstruktor Aufruf");
	}

	// Eine Zugriffsmethode auf Klassenebene, welches '''einmal''' ein konkretes
	// Objekt erzeugt und dieses zurückliefert.
	public static Buchhaltung getInstance() {
		if (buchhaltung == null)
			buchhaltung = new Buchhaltung(); // Aufruf Konstruktor
		else
			System.out.println("Buchhaltung existiert bereits und wird verwendet.");

		return buchhaltung;
	}

	public void update(double betrag, BezahlStrategie bzStrategie) {
		System.out.println("Der Betrag "+ betrag + " wurde mit der Bezahlstrategie "+bzStrategie +" bezahlt und an die Buchhaltung weitergeleitet.");
		this.betraege.add(betrag);
		this.bzStrategie.add(bzStrategie);

	}

}
