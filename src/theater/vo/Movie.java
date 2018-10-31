package theater.vo;

public class Movie {
	private String movieTitle;
	private String rating;
	private String theaterName;
	private String genre;
	private String company;
	private String releaseDate;
	
	public Movie() {
		
	}


	public String getMovieTitle() {
		return movieTitle;
	}



	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getRating() {
		return rating;
	}



	public void setRating(String rating) {
		this.rating = rating;
	}



	public String getTheaterName() {
		return theaterName;
	}



	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(String releaseDate) {
		
		this.releaseDate = releaseDate.substring(0, 10);
	}

	@Override
	public String toString() {
		return "영화명: " + movieTitle +" , 개봉일: "+releaseDate+" , 등급: "+rating
				+" , 배급사: "+company+" , 영화관: "+theaterName+" , 장르: "+genre;
	}
}
