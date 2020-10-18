package strategy;
/**
 * @author Nico Schultze
 *version 1.2
 *Bezahlstrategie Rechnung.
 */
public class RechnungZahlen implements BezahlStrategie {

	@Override
	public String bezahlen(double betrag) {

		return "Der Betrag" + betrag + " Euro wird mit der Bezahlstrategie Rechnung bezahlt";
	}

	@Override
	public String toString() {
		return " Rechnung ";
	}
}
