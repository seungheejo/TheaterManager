package theater.service;

import java.util.List;

import theater.vo.Movie;
import theater.vo.Reservation;
import theater.vo.SeatInfo;
import theater.vo.TheaterMember;

public interface TheaterMapper {

	// 회원 등록 insert
	public int insertMember(TheaterMember tm);

	// 회원 예약 insert
	public int memberReserveTicket(Reservation r);

	// 예약 취소 delete
	public int deleteReservedTicket(int reservenum);

	// 비회원 예약 insert
	public int nonmemberReserveTicket(Reservation r);

	// 예약 확인 select
	public List<Reservation> reservationInfo();

	// ID별 예약한 영화 정보 select
	public List<Reservation> idReservationInfo(String id);

	// 모든 영화의 상영정보 select
	public List<Movie> getShowingInfo();

	// 부천 영화관 영화 상영정보 select
	public List<Movie> getShowingBucheonInfo();

	// 인천 영화관 영화 상영정보 select
	public List<Movie> getShowingIncheonInfo();

	// 용산 영화관 영화 상영정보 select
	public List<Movie> getShowingYongsanInfo();

	// 왕십리 영화관 영화 상영정보 select
	public List<Movie> getShowingWangsibriInfo();

	// 부천 영화관 좌석정보 select
	public List<SeatInfo> getBucheonSeatsInfo();

	// 인천 영화관 좌석정보 select
	public List<SeatInfo> getIncheonSeatsInfo();

	// 용산 영화관 좌석정보 select
	public List<SeatInfo> getYongsanSeatsInfo();

	// 왕십리 영화관 좌석정보 select
	public List<SeatInfo> getWangsibriSeatsInfo();

	// 부천 영화관 좌석정보 업데이트 update
	public int updateBucheonBookingInfo(String bookingInfo);

	// 인천 영화관 좌석정보 업데이트 update
	public int updateIncheonBookingInfo(String bookingInfo);

	// 용산 영화관 좌석정보 업데이트 update
	public int updateYongsanBookingInfo(String bookingInfo);

	// 왕십리 영화관 좌석정보 업데이트 update
	public int updateWangsibriBookingInfo(String bookingInfo);

	// 장르별 영화정보 select
	public List<Movie> getMovieRecommend(String genre);

	// 모든 회원의 정보 select
	public List<TheaterMember> getTheaterMemberInfo();

	// 탈퇴 delete
	public int deleteMember(String id);

}
