/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung der Kasse der Reiseagentur. Diese ist als Singleton implementiert und besitzt die Methoden zum Bezahlen der Reservierung.
 */
package entities;

import strategy.BezahlStrategie;


public class Kasse {
// Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Kasse kasse = null;
	public BezahlStrategie bezahlStrategie;

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private Kasse() {
		System.out.println("Konstruktor Aufruf");
	}

// Eine Zugriffsmethode auf Klassenebene, welches '''einmal''' ein konkretes
// Objekt erzeugt und dieses zurückliefert.
	public static Kasse getInstance() {
		if (kasse == null)
			kasse = new Kasse(); // Aufruf Konstruktor
		else
			System.out.println("kasse existiert schon");

		return kasse;
	}

	public String bezahlen(double betrag) {
		return bezahlStrategie.bezahlen(betrag);
	}

	public void setBezahlStrategie(BezahlStrategie newBezahlStrategie) {
		this.bezahlStrategie = newBezahlStrategie;
	}

	
	
}