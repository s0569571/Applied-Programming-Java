package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Random;

import entities.*;

/**
 * @author Nico Schultze
 *version 1.2
 *Init des Programms, mit Beispieldaten.
 */
public class Init {

	private static String companies[] = { "Essence", "Daydream Systems", "Icecap Foods", "Wooductions", "Elitelligence",
			"Vinedustries", "Blizzart", "Mountainway", "Crowcoms", "Cloudmaster" };

	private static String hotels[] = { "Snowy Lake Hotel", "King's Flower Hotel", "Winter Cove Hotel",
			"Ebony Landscape Hotel & Spa", "Illustrious Vertex Hotel", "Southern Veil Hotel", "Antique Hotel",
			"Recreation Hotel", "Mountain Hotel & Spa", "Majestic Hotel" };

	private static String flughaefen[] = { "Altenbach", "Bischofstein", "Antden", "Grimselt", "Saumur", "Maitoise",
			"Neunsloh", "Oderhofen", "Tullaroom", "Athnard", "Groveen", "Culemstein", "Spreitendrisio", "Reistadt" };

	public static void initReiseagentur(Reiseagentur agentur) throws Exception {
		Kunde kunden[] = loadKunden();
		for (Kunde kunde : kunden) {
			agentur.addKunde(kunde);
			makeSomeReservations(kunde);
		}

	}

	private static void makeSomeReservations(Kunde kunde) throws Exception {

		
		LocalDate startDate = LocalDate.of(2019, 1, 1);
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(10); i++) {
			LocalDate fromDate = startDate.plusDays(rand.nextInt(30));
			LocalDate toDate = fromDate.plusDays(rand.nextInt(10) + 1);
			fromDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale((Locale.GERMAN)));
			toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale((Locale.GERMAN)));
			Reservierung reservierung;

			if (rand.nextBoolean())
				reservierung = new Hotelreservierung(hotels[i], fromDate, toDate);
			else
				reservierung = new Flugreservierung(flughaefen[i], flughaefen[flughaefen.length - i - 1],
						"FL-" + (1000 + rand.nextInt(8999)), fromDate, toDate);
			reservierung.setSumme(rand.nextInt(2000));
			kunde.addReservierung(reservierung);
			
		}
	}
	


	private static Kunde[] loadKunden() {

		Random rand = new Random();

		Adresse zacAdresse = new Adresse("Zacstraße", "1a", "45345", "Bali");
		Adresse bradAdresse = new Adresse("Bradstraße", "12", "04236", "Berlin");
		Adresse ashleyAdresse = new Adresse("Tisdalestraße", "87", "95645", "Bangkok");
		Adresse leonardoAdresse = new Adresse("DiCapriostaße", "8", "56678", "Kyoto");
		Adresse vanessaAdresse = new Adresse("Hudgeonsstr", "4", "94585", "NY");
		Adresse siaAdresse = new Adresse("Siastraße", "99b", "34634", "Paris");
		Adresse cristianoAdresse = new Adresse("Cristianostraße", "1", "78933", "Turin");
		Adresse messiAdresse = new Adresse("Messistraße", "1", "22342", "Barcelona");
		Adresse alabaAdresse = new Adresse("Alabastraße", "11", "93842", "Wien");
		Adresse kroosAdresse = new Adresse("Kroosstraße", "7a", "11232", "Frankfurt");

		LocalDate birth1 = LocalDate.of(1990, Month.MARCH, 12);
		LocalDate birth2 = LocalDate.of(1965, Month.JUNE, 6);
		LocalDate birth3 = LocalDate.of(1989, Month.AUGUST, 8);
		LocalDate birth4 = LocalDate.of(1975, Month.FEBRUARY, 7);
		LocalDate birth5 = LocalDate.of(1990, Month.JANUARY, 19);
		LocalDate birth6 = LocalDate.of(1992, Month.JANUARY, 29);
		LocalDate birth7 = LocalDate.of(1985, Month.SEPTEMBER, 4);
		LocalDate birth8 = LocalDate.of(1987, Month.DECEMBER, 10);
		LocalDate birth9 = LocalDate.of(1992, Month.NOVEMBER, 15);
		LocalDate birth10 = LocalDate.of(1992, Month.MAY, 21);

		Privatkunde zac = new Privatkunde(Anrede.Herr, "Zac", "Efron", birth1, zacAdresse, "z.e@web.de", "0345234532");
		Privatkunde brad = new Privatkunde(Anrede.Herr, "Brad", "Pitt", birth2, bradAdresse, "b.p@web.de", "9345637443");
		Privatkunde ashley = new Privatkunde(Anrede.Frau, "Ashley", "Tisdale", birth3, ashleyAdresse, "a.t@web.de",
				"69595403");
		Privatkunde ronaldo = new Privatkunde(Anrede.Herr, "Cristiano", "Ronaldo", birth7, cristianoAdresse, "034562345",
				"c.r@web.de");
		Privatkunde messi = new Privatkunde(Anrede.Herr, "Lionel", "Messi", birth8, messiAdresse, "9324582", "l.m@web.de");

		Geschaeftskunde leonardo = new Geschaeftskunde(Anrede.Herr, "Leonardo", "DiCaprio", birth4, leonardoAdresse, "l.c@web.de",
				"98677893", companies[rand.nextInt(companies.length)]);
		Geschaeftskunde vanessa = new Geschaeftskunde(Anrede.Frau, "Vanessa", "Hudgeons", birth5, vanessaAdresse, "v.h@web.de",
				"56703943", companies[rand.nextInt(companies.length)]);
		Geschaeftskunde sia = new Geschaeftskunde(Anrede.Frau, "Sia", "Chandelier", birth6, siaAdresse, "s.h@web.de", "777348209",
				companies[rand.nextInt(companies.length)]);
		Geschaeftskunde alaba = new Geschaeftskunde(Anrede.Herr, "David", "Alaba", birth9, alabaAdresse, "9432475", "d.a@web.de",
				companies[rand.nextInt(companies.length)]);
		Geschaeftskunde kroos = new Geschaeftskunde(Anrede.Herr, "Toni", "Kroos", birth10, kroosAdresse, "909324578",
			"t.k@web.de", companies[rand.nextInt(companies.length)]);
		
		
	   
		
	    
		//privatkunden
		Bezahlmethode[] zacBz = new Bezahlmethode[3];
		Bezahlmethode b1 = new Bezahlmethode("Kreditkarte", " -NR:456");
		Bezahlmethode b2 = new Bezahlmethode("PayPal", " -z.e@web.de");
		Bezahlmethode b3 = new Bezahlmethode("EC-Karte", " -IBAN: 3456346364545");
		zacBz[0]=b1;
		zacBz[1]=b2;
		zacBz[2]=b3;
	    zac.setBezahlMethoden(zacBz);
		
	    Bezahlmethode[] bradBz = new Bezahlmethode[1];
		Bezahlmethode b4 = new Bezahlmethode("PayPal", " -b.p@web.de");
		bradBz[0]=b4;
		brad.setBezahlMethoden(bradBz);
		
		Bezahlmethode[] ashleyBz = new Bezahlmethode[2];
		Bezahlmethode b5 = new Bezahlmethode("EC-Karte", " -IBAN: 477432454564");
		Bezahlmethode b6 = new Bezahlmethode("PayPal", " -a.t@web.de");
		ashleyBz[0]=b5;
		ashleyBz[1]=b6;
		ashley.setBezahlMethoden(ashleyBz);
		
		
		Bezahlmethode[] ronaldoBz = new Bezahlmethode[3];
		Bezahlmethode b7 = new Bezahlmethode("Kreditkarte", " -NR:971");
		Bezahlmethode b8 = new Bezahlmethode("EC-Karte", " -IBAN: 0996423457767");
		Bezahlmethode b9 = new Bezahlmethode("PayPal", " -c.r@web.de");
		ronaldoBz[0]=b7;
		ronaldoBz[1]=b8;
		ronaldoBz[2]=b9;
		ronaldo.setBezahlMethoden(ronaldoBz);
		
		Bezahlmethode[] messiBz = new Bezahlmethode[1];
		Bezahlmethode b10= new Bezahlmethode("EC-Karte", " -IBAN: 567785893245");
		messiBz[0]=b10;
		messi.setBezahlMethoden(messiBz);
	
		
//		//geschaeftskunden
//		Bezahlmethode gBz1 = new Bezahlmethode("Rechnung", "Post");
//		leonardo.setBezahlmethode(gBz1);
//		
//		Bezahlmethode gBz2 = new Bezahlmethode("Rechnung", "Email");
//		vanessa.setBezahlmethode(gBz2);
//		
//		Bezahlmethode gBz3 = new Bezahlmethode("Rechnung", "Post");
//		sia.setBezahlmethode(gBz3);
//		
//		Bezahlmethode gBz4 = new Bezahlmethode("Rechnung", "Post");
//		alaba.setBezahlmethode(gBz4);
//		
//		Bezahlmethode gBz5 = new Bezahlmethode("Rechnung", "Email");
//	    kroos.setBezahlmethode(gBz5);

		return new Kunde[] { zac, brad, ashley, ronaldo, messi, leonardo, vanessa, sia, alaba, kroos };

	}

}
