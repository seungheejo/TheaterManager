package theater.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import theater.service.TheaterMapper;
import theater.vo.Movie;
import theater.vo.Reservation;
import theater.vo.SeatInfo;
import theater.vo.TheaterMember;
import theater.service.MyBatisConfig;


public class TheaterDAO implements TheaterMapper {

	private SqlSessionFactory factory = MyBatisConfig.getSqlSessionFactory();

	@Override
	public int insertMember(TheaterMember tm) {
		
		SqlSession session = factory.openSession();
		TheaterMapper theater = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = theater.insertMember(tm);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int memberReserveTicket(Reservation r) {
	
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.memberReserveTicket(r);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteReservedTicket(int reservenum) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.deleteReservedTicket(reservenum);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int nonmemberReserveTicket(Reservation r) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.nonmemberReserveTicket(r);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Reservation> reservationInfo() {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		List<Reservation> result = null;
		
		try {
			result = tm.reservationInfo();
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Reservation> idReservationInfo(String id) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		List<Reservation> result = null;
		
		try {
			result = tm.idReservationInfo(id);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Movie> getShowingInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getShowingInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Movie> getShowingBucheonInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getShowingBucheonInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Movie> getShowingIncheonInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getShowingIncheonInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Movie> getShowingYongsanInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getShowingYongsanInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Movie> getShowingWangsibriInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getShowingWangsibriInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<TheaterMember> getTheaterMemberInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<TheaterMember> result = null;

		try {
			result = tm.getTheaterMemberInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public int updateBucheonBookingInfo(String bookingInfo) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.updateBucheonBookingInfo(bookingInfo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateIncheonBookingInfo(String bookingInfo) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.updateIncheonBookingInfo(bookingInfo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateYongsanBookingInfo(String bookingInfo) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.updateYongsanBookingInfo(bookingInfo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateWangsibriBookingInfo(String bookingInfo) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.updateWangsibriBookingInfo(bookingInfo);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteMember(String id) {
		
		SqlSession session = factory.openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);
		
		int result = 0;
		
		try {
			result = tm.deleteMember(id);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<SeatInfo> getBucheonSeatsInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<SeatInfo> result = null;

		try {
			result = tm.getBucheonSeatsInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<SeatInfo> getIncheonSeatsInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<SeatInfo> result = null;

		try {
			result = tm.getIncheonSeatsInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<SeatInfo> getYongsanSeatsInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<SeatInfo> result = null;

		try {
			result = tm.getYongsanSeatsInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<SeatInfo> getWangsibriSeatsInfo() {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<SeatInfo> result = null;

		try {
			result = tm.getWangsibriSeatsInfo();
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Movie> getMovieRecommend(String genre) {
		
		SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
		TheaterMapper tm = session.getMapper(TheaterMapper.class);

		List<Movie> result = null;

		try {
			result = tm.getMovieRecommend(genre);
			session.commit();
		} finally {
			session.close();
		}

		return result;
	}

}
