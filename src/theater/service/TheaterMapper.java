package theater.service;

import java.util.List;

import theater.vo.Movie;
import theater.vo.Reservation;
import theater.vo.SeatInfo;
import theater.vo.TheaterMember;

public interface TheaterMapper {

	// メンバー登録 insert
	public int insertMember(TheaterMember tm);

	// メンバーの予約 insert
	public int memberReserveTicket(Reservation r);

	// 予約のキャンセル delete
	public int deleteReservedTicket(int reservenum);

	// ノンメンバーの予約 insert
	public int nonmemberReserveTicket(Reservation r);

	// 予約の確認 select
	public List<Reservation> reservationInfo();

	// ID別に予約した映画の情報 select
	public List<Reservation> idReservationInfo(String id);

	// 全ての映画の上映情報 select
	public List<Movie> getShowingInfo();

	// プチョン映画館で上映中の映画情報 select
	public List<Movie> getShowingBucheonInfo();

	// インチョン映画館で上映中の映画情報 select
	public List<Movie> getShowingIncheonInfo();

	// ヨンサン映画館で上映中の映画情報 select
	public List<Movie> getShowingYongsanInfo();

	// ワンシムニ映画館で上映中の映画情報 select
	public List<Movie> getShowingWangsibriInfo();

	// プチョン映画館の座席情報 select
	public List<SeatInfo> getBucheonSeatsInfo();

	// インチョン映画館の座席情報 select
	public List<SeatInfo> getIncheonSeatsInfo();

	// ヨンサン映画館の座席情報 select
	public List<SeatInfo> getYongsanSeatsInfo();

	// ワンシムニ映画館の座席情報 select
	public List<SeatInfo> getWangsibriSeatsInfo();

	// プチョン映画館の座席情報のアップデート update
	public int updateBucheonBookingInfo(String bookingInfo);

	// インチョン映画館の座席情報のアップデート update
	public int updateIncheonBookingInfo(String bookingInfo);

	// ヨンサン映画館の座席情報のアップデート update
	public int updateYongsanBookingInfo(String bookingInfo);

	// ワンシムニ映画館の座席情報のアップデート update
	public int updateWangsibriBookingInfo(String bookingInfo);

	// ジャンル別の映画情報 select
	public List<Movie> getMovieRecommend(String genre);

	// 全てのメンバーの情報 select
	public List<TheaterMember> getTheaterMemberInfo();

	// 脱退 delete
	public int deleteMember(String id);

}
