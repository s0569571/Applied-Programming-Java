package observerpattern;
/**
 * @author Nico Schultze
 *version 1.2
 *Eigener implementierter Observer.
 */

import strategy.BezahlStrategie;

public interface Observer {

    /**
     * Diese abstrakte Methode gibt vor, dass Observer Instanzen diese update Methode individuell implementieren, um eine relevantes Update erhalten zu koennen.
     * @param betrag: der weiterzureichende Betrag
     * @param bzMethode: der weiterzugebende Betrag.
     */
    public void update(double betrag, BezahlStrategie bzStrategie);

}//end of interface