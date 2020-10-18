/**
 * @author Nico Schultze
 *version 1.2
 *Bezahlstrategie ECKarte
 */
package strategy;

public class ECKarte implements BezahlStrategie {

	public String bezahlen(double betrag) {
		
		return "Der Betrag" + betrag + " Euro wird mit der Bezahlstrategie ECKarte bezahlt";
	}

	@Override
	public String toString() {
		return " ECKarte ";
	}



}
