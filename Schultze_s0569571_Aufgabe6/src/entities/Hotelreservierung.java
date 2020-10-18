package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nico Schultze
 *version 1.2
 *Hotelreservierung des Kunden.
 */
public class Hotelreservierung extends Reservierung {

	private String hotelname;

	/**
	 * @param datum
	 * @throws Exception 
	 */
	public Hotelreservierung(String hotelname, LocalDate fromDate, LocalDate toDate) throws Exception {
		super(fromDate, toDate);
		this.hotelname = hotelname;
		
	}

	/**
	 * @return the hotelname
	 */
	public String getHotelname() {
		return this.hotelname;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString() + " ");

		if (this.hotelname != null) {
			builder.append("beim Hotel ");
			builder.append(this.hotelname);
		}
		return builder.toString();
	}
}
