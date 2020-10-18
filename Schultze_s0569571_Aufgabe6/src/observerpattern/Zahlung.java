/**
 * @author Nico Schultze
 *version 1.2
 *Beschreibung der Zahlung der Reiseagentur. Diese KLasse ist Teil des Observer Patterns.
 */
package observerpattern;

import java.util.ArrayList;

import entities.Bezahlmethode;
import strategy.BezahlStrategie;



public class Zahlung implements observerpattern.Observable {

	private ArrayList<observerpattern.Observer> observers = new ArrayList<>();
	private ArrayList<BezahlStrategie> bzStrategie = new ArrayList<BezahlStrategie>();
	private ArrayList<Double> betraege = new ArrayList<Double>();

	
	
	public Zahlung() {
		super();
	addObserver(Auditing.getInstance());
	addObserver(Buchhaltung.getInstance());
	}



	public void addObserver(observerpattern.Observer o) {
		this.observers.add(o);
	}

	

	public void erfasseZahlung(double betrag, BezahlStrategie bzStrategie) {
		this.betraege.add(betrag);
		this.bzStrategie.add(bzStrategie);
		notifyObservers(betrag, bzStrategie);
	}

	@Override
	public void notifyObservers(double betrag, BezahlStrategie bzStrategie) {
		for (observerpattern.Observer observer : observers) {
			observer.update(betrag, bzStrategie);
		}
		
	}
}
