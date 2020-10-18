package strategy;
/**
 * @author Nico Schultze
 *version 1.2
 *Bezahlstrategie Kreditkarte.
 */
public class Kreditkarte implements BezahlStrategie {

	@Override
	public String bezahlen(double betrag) {

		return "Der Betrag" + betrag + " Euro wird mit der Bezahlstrategie Kreditkarte bezahlt";
	}

	@Override
	public String toString() {
		return " Kreditkarte ";
	}
	

}
