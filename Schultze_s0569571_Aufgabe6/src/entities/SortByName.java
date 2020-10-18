package entities;

import java.io.Serializable;
/**
 * @author Nico Schultze
 *version 1.2
 */
import java.util.Comparator;

public class SortByName implements Comparator<Kunde>, Serializable{

	@Override
	public int compare(Kunde a, Kunde b) {
		int compareVorname = a.getVorname().compareToIgnoreCase(b.getVorname());
		int compareNachname = a.getNachname().compareToIgnoreCase(b.getNachname());

		if (compareVorname == 0)
			return compareNachname;
		else
			return compareVorname;

	}

}
