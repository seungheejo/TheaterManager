package theater.vo;

public class Reservation {
	private int reserveNum;
	private String reserveDate;
	private String movieTitle;
	private String theaterName;
	private String chosenSeat;
	private int ticketPrice;
	private String id;
	private String email;
	private String genre;
	
	public Reservation() {
		
	}
	
	//メンバーの予約
	public Reservation(String movieTitle, String theaterName, String chosenSeat, int ticketPrice, String id,
			String email, String genre) {
		super();
		this.movieTitle = movieTitle;
		this.theaterName = theaterName;
		this.chosenSeat = chosenSeat;
		this.ticketPrice = ticketPrice;
		this.id = id;
		this.email = email;
		this.genre = genre;
	}
	
	//ノンメンバーの予約
	public Reservation(String movieTitle, String theaterName, String chosenSeat, int ticketPrice,
			String email, String genre) {
		super();
		this.movieTitle = movieTitle;
		this.theaterName = theaterName;
		this.chosenSeat = chosenSeat;
		this.ticketPrice = ticketPrice;
		this.email = email;
		this.genre = genre;
	}


	public int getReserveNum() {
		return reserveNum;
	}


	public void setReserveNum(int reserveNum) {
		this.reserveNum = reserveNum;
	}

	public String getReserveDate() {
		return reserveDate;
	}


	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getTheaterName() {
		return theaterName;
	}


	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}


	public String getChosenSeat() {
		return chosenSeat;
	}


	public void setChosenSeat(String chosenSeat) {
		this.chosenSeat = chosenSeat;
	}


	public int getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}
	 
	@Override
	public String toString() {
		return "====예매 내역====\n" +"\n영화제목: " + movieTitle + "\n영화관: " + theaterName
				+ "\n좌석번호: " + chosenSeat + "\n티켓가격: " + ticketPrice+ "\n장르: " + genre;
	}
	
}
