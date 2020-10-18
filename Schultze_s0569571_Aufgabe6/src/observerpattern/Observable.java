package observerpattern;

/**
 * @author Nico Schultze
 *version 1.2
 *Eigenes Observable interface.
 */
import strategy.BezahlStrategie;

public interface Observable {
	  public void notifyObservers(double betrag, BezahlStrategie bzMethode);
	  public void addObserver(Observer o);
}
