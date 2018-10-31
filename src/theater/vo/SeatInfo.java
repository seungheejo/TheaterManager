package theater.vo;

public class SeatInfo {
	private String theaterName;
	private String seats;
	private String bookinginfo;
	
	public SeatInfo() {
		
	}

	public SeatInfo(String theaterName, String seats, String bookinginfo) {
		super();
		this.theaterName = theaterName;
		this.seats = seats;
		this.bookinginfo = bookinginfo;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getBookingInfo() {
		return bookinginfo;
	}

	public void setBookingInfo(String bookinginfo) {
		this.bookinginfo = bookinginfo;
	}
	
	@Override
	public String toString() {
		return "상영관명: " + this.theaterName + ", 좌석: " + this.seats + ",좌석예약정보: " + this.bookinginfo;
	}
}
