package theater.ui;

import theater.service.TheaterDAO;
import theater.vo.Movie;
import theater.vo.Reservation;
import theater.vo.SeatInfo;
import theater.vo.TheaterMember;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class KoreanTheaterUI {

	private TheaterDAO dao = new TheaterDAO();
	private Scanner sc = new Scanner(System.in);
	private List<SeatInfo> sList = null;
	private List<Movie> mList = null;

	TheaterMember tm = new TheaterMember();

	public KoreanTheaterUI() {

		sList = new ArrayList<>();
		mList = new ArrayList<>();
	}

	public void startUI() {
		while (true) {

			printMainUI();

			int select = sc.nextInt();

			switch (select) {

			// 회원 예약일 경우
			case 1:
				System.out.println("=====================================");
				System.out.println("=================로그인================");
				System.out.print("id를 입력해 주세요: ");
				String id = sc.next();
				tm.setId(id);
				sc.nextLine();

				System.out.print("password를 입력해 주세요: ");
				String pw = sc.next();
				sc.nextLine();

				// 로그인 성공할 경우
				if (checkLogin(id, pw) == true) {

					System.out.println("로그인 되었습니다.");
					System.out.println("=====================================");
					System.out.println("=====================================");
					System.out.println("1. 영화 예매");
					System.out.println("2. 회원 탈퇴");
					System.out.println("9. 프로그램 종료");
					System.out.print("입력해 주세요: ");
					int menu = sc.nextInt();
					sc.nextLine();

					if (menu == 1) {

						// 영화 추천
						recommendMovie(id);
						// 영화 예약
						reservation(select);

					} else if (menu == 2) {

						// 탈퇴
						withdrawal(id);

					} else {

						// 프로그램 종료
						System.out.println("프로그램을 종료합니다.");
						return;

					}

					break;

				} else {

					// 로그인 실패할 경우
					System.out.println("로그인 실패하였습니다.");

					// 상위 메뉴로 돌아감
					break;

				}

				// 비회원 예약일 경우
			case 2:
				System.out.println("=====================================");
				System.out.println("===============비회원 예매==============");
				System.out.print("email을 입력해 주세요: ");
				String email = sc.next();
				sc.nextLine();
				tm.setEmail(email);

				System.out.print("핸드폰 번호를 입력해 주세요: ");
				String phonenum = sc.next();
				sc.nextLine();
				tm.setPhoneNum(phonenum);

				reservation(select);

				break;

			// 회원 등록일 경우
			case 3:
				register();

				break;

			// 예약 확인의 경우
			case 4:
				System.out.println("=======예매내역 확인=======");
				System.out.println("1. 회원");
				System.out.println("2. 비회원");
				System.out.print("입력해주세요: ");
				int choice = sc.nextInt();
				sc.nextLine();

				getReservationInfo(choice);

				break;

			// 프로그램 종료
			case 9:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);

			}// switch 끝

		} // while 끝

	}// startUI메소드 끝

	// 영화 예약 프로그램의 기본 유저 인터페이스
	public void printMainUI() {

		System.out.println("=====================================");
		System.out.println("==========SGV 영화 예매 프로그램==========");
		System.out.println("=====================================");
		System.out.println("1. 회원 예매");
		System.out.println("2. 비회원 예매");
		System.out.println("3. 회원 가입");
		System.out.println("4. 예매내역 확인");
		System.out.println("9. 프로그램 종료");
		System.out.println("=====================================");
		System.out.print("입력해주세요: ");

	}

	// 영화관 선택하는 유저 인터페이스
	public void printSelectDistrictUI() {

		System.out.println("=====================================");
		System.out.println("===============영화관 선택==============");
		System.out.println("1. SGV 부천");
		System.out.println("2. SGV 인천");
		System.out.println("3. SGV 용산");
		System.out.println("4. SGV 왕십리");
		System.out.println("=====================================");
		System.out.print("입력해주세요: ");

	}

	// 좌석 선택의 유저 인터페이스
	public String printSelectSeatUI(List<SeatInfo> sList, String theaterName) {

		System.out.println("=====================================");
		System.out.println("================좌석 선택==============");

		// 비어있는 좌석을"□"의 형태로 설정
		String[][] seats = { { "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" } };

		// 영화관마다의 좌석정보를 리스트에 넣음
		if (theaterName.contains("부천"))
			sList = dao.getBucheonSeatsInfo();
		else if (theaterName.contains("인천"))
			sList = dao.getIncheonSeatsInfo();
		else if (theaterName.contains("용산"))
			sList = dao.getYongsanSeatsInfo();
		else if (theaterName.contains("왕십리"))
			sList = dao.getWangsibriSeatsInfo();

		String seatRow = " ";
		String seatColumn = " ";
		String booking = " ";

		// 모든 좌석을 구분하기 위해
		String row[] = { "a", "b", "c", "d" };
		String column[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };

		System.out.println(seatRow + " " + seatColumn + " " + booking);

		for (int index = 0; index < sList.size(); index++) {

			seatRow = sList.get(index).getSeats().substring(0, 1);
			seatColumn = sList.get(index).getSeats().substring(1, 3);
			booking = sList.get(index).getBookingInfo();

			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < column.length; j++) {

					if (seatRow.equals(row[i]) && seatColumn.equals(column[j])) {

						if (booking.equals("예약가능")) {
							// 예약 가능한 좌석일 경우
							seats[i][j] = "□";
						} else {
							// 예약 불가능한 좌석일 경우
							seats[i][j] = "■";
						}
					}
				}

			} // inner for 끝

		} // outer for 끝

		// 영화의 현재 좌석정보를 보기 위해
		for (int seatNum = 1; seatNum <= 10; seatNum++) {

			if (seatNum == 10) {
				System.out.print("\t" + seatNum + "\n");
				break;
			}
			System.out.print("\t0" + seatNum);

		}
		for (int i = 0; i < 4; i++) {

			if (i == 0)
				System.out.print("A ");
			else if (i == 1)
				System.out.print("B ");
			else if (i == 2)
				System.out.print("C ");
			else
				System.out.print("D ");

			for (int j = 0; j < 10; j++) {
				System.out.print("\t" + seats[i][j]);
			}
			System.out.println();
		}

		// 좌석을 선택할 경우
		// 좌석의 행, 열의 이름을 붙여서 입력해야만 한다
		System.out.print("좌석을 선택해 주세요(ex a01): ");
		String seatSelect = sc.next();
		sc.nextLine();

		String inputRow = seatSelect.substring(0, 1); // a
		String inputColumn = seatSelect.substring(1, 3); // 01

		updateSeatsInfo(theaterName, booking, seats, row, column, inputRow, inputColumn);

		return seatSelect;

	}// printSelectSeatUI메소드 끝

	// 좌석정보 업데이트
	public void updateSeatsInfo(String theaterName, String booking, String[][] seats, String[] row, String[] column,
			String inputRow, String inputColumn) {
		int indexI = 0;
		int indexJ = 0;

		SeatInfo s = new SeatInfo();
		s.setSeats(inputRow + inputColumn);

		// 부천 영화관일 경우
		if (theaterName.contains("부천")) {

			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < column.length; j++) {

					if (inputRow.equals(row[i]) && inputColumn.equals(column[j])) {

						indexI = i;
						indexJ = j;

						break;
					}

				}
			}

			for (int index = 0; index < sList.size(); index++) {

				if (s.getSeats().equals(sList.get(index).getSeats())) {

					// 예약 가능할 경우
					if (sList.get(index).getBookingInfo().equals("예약가능")) {
						// 좌석을 예약하고 "■"으로 변경
						seats[indexI][indexJ] = "■";
						dao.updateBucheonBookingInfo(inputRow.concat(inputColumn));

					} else {
						System.out.println("예약불가");

					}
				}

			} // for 끝
		} // outer if 끝

		// 인천 영화관일 경우
		if (theaterName.contains("인천")) {

			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < column.length; j++) {

					if (inputRow.equals(row[i]) && inputColumn.equals(column[j])) {

						indexI = i;
						indexJ = j;

						break;
					}
				}
			}

			for (int index = 0; index < sList.size(); index++) {

				if (s.getSeats().equals(sList.get(index).getSeats())) {

					if (sList.get(index).getBookingInfo().equals("예약가능")) {

						seats[indexI][indexJ] = "■";
						dao.updateIncheonBookingInfo(inputRow.concat(inputColumn));

					} else {
						System.out.println("예약불가");
					}
				}

			} // for 끝
		} // outer if 끝

		// 용산 영화관일 경우
		if (theaterName.contains("용산")) {

			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < column.length; j++) {

					if (inputRow.equals(row[i]) && inputColumn.equals(column[j])) {

						indexI = i;
						indexJ = j;

						break;
					}
				}
			}

			for (int index = 0; index < sList.size(); index++) {

				if (s.getSeats().equals(sList.get(index).getSeats())) {

					if (sList.get(index).getBookingInfo().equals("예약가능")) {

						seats[indexI][indexJ] = "■";
						dao.updateYongsanBookingInfo(inputRow.concat(inputColumn));

					} else {
						System.out.println("예약불가");
					}
				}

			} // for 끝
		} // outer if 끝

		// 왕십리 영화관일 경우
		if (theaterName.contains("왕십리")) {

			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < column.length; j++) {

					if (inputRow.equals(row[i]) && inputColumn.equals(column[j])) {

						indexI = i;
						indexJ = j;

						break;

					}
				}
			}

			for (int index = 0; index < sList.size(); index++) {

				if (s.getSeats().equals(sList.get(index).getSeats())) {

					if (sList.get(index).getBookingInfo().equals("예약가능")) {

						seats[indexI][indexJ] = "■";
						dao.updateWangsibriBookingInfo(inputRow.concat(inputColumn));

					} else {
						System.out.println("예약불가");
					}
				}

			} // for 끝
		} // outer if 끝

	}// updateSeatsInfo메소드 끝

	// 회원별 영화 추천
	public void recommendMovie(String id) {

		System.out.println("========= " + id + " 고객님을 위한 영화추천===========");

		// 회원별 예약한 정보를 리스트에 넣음
		List<Reservation> rList = dao.idReservationInfo(id);

		String genre = " ";
		int check[] = new int[7];
		String genres[] = { "SF", "공포", "드라마", "스릴러", "액션", "코미디", "판타지" };

		for (int index = 0; index < rList.size(); index++) {
			for (int g = 0; g < genres.length; g++) {
				/*
				 * genres배열중에 rList와 일치하는게 있다면
				 */
				if (genres[g].equals(rList.get(index).getGenre())) {
					/*
					 * 이 때의 genres배열의 index와 같은 index에 있는 check배열의 값을 증가시킴
					 */
					check[g]++;
				}

			}
		}

		int index = 0;
		int max = check[0];

		for (int i = 0; i < check.length; i++) {
			if (check[i] >= max) {
				max = check[i];
				index = i;
			}
		}

		// 가장 많이 카운트 된 장르를 저장함
		genre = genres[index];

		// 장르가 같은 영화정보를 리스트에 넣음
		mList = dao.getMovieRecommend(genre);

		for (Movie m : mList) {
			System.out.println(m);
		}

	}// recommendMovie메소드 끝

	// 예약 정보
	public void getReservationInfo(int choice) {

		List<Reservation> rList = dao.reservationInfo();

		// 회원일 경우
		if (choice == 1) {

			while (true) {

				System.out.println("=========로그인=========");
				System.out.print("id: ");
				String id = sc.next();
				sc.nextLine();

				System.out.print("pw: ");
				String pw = sc.next();
				sc.nextLine();

				// 로그인 성공할 경우
				if (checkLogin(id, pw) == true) {

					System.out.println("로그인 되었습니다");
					System.out.println("\n===============================");
					System.out.println("1. 예매내역확인 및 예매취소하기");
					System.out.println("2. 상위메뉴로 돌아가기");
					System.out.print("입력해 주세요: ");
					int menu = sc.nextInt();
					sc.nextLine();

					// 예약 확인 or 취소
					if (menu == 1) {

						int index = 0;

						for (index = 0; index < rList.size(); index++) {

							if (id.equals(rList.get(index).getId())) {
								System.out.println("\n\nreserveNum: " + rList.get(index).getReserveNum() + " \n"
										+ rList.get(index));

							}

						}

						System.out.print("취소할 예매번호를 입력해 주세요: ");
						int c = sc.nextInt();

						dao.deleteReservedTicket(c);
						System.out.println("예매를 취소하였습니다");

					} else {
						// 로그인 실패할 경우
						System.out.println("로그인 실패");
						return;

					}

				} else
					break;

			} // while 끝

		} else {
			// 비회원일 경우
			while (true) {

				System.out.print("email: ");
				String email = sc.next();
				sc.nextLine();

				for (int index = 0; index < rList.size(); index++) {

					if (email.equals(rList.get(index).getEmail())) {
						System.out.println(
								"\n\nreserveNum: " + rList.get(index).getReserveNum() + " \n" + rList.get(index));
					}
				}

				System.out.print("취소할 예매번호를 입력해 주세요: ");
				int c = sc.nextInt();

				dao.deleteReservedTicket(c);
				System.out.println("예매를 취소하였습니다");

			} // while 끝

		} // else 끝

	}// getReservationInfo메소드 끝

	// 탈퇴
	public void withdrawal(String id) {

		int count = 0;

		while (true) {

			System.out.print("비밀번호를 다시 입력해 주세요: ");
			String pw = sc.next();
			sc.nextLine();

			if (checkLogin(id, pw) == true) {

				dao.deleteMember(id);
				System.out.println("탈퇴하였습니다");
				break;

			} else {

				count++;

				if (count == 3) {
					// 비밀번호가 3회 다르면 위로 돌아감
					System.out.println("비밀번호 3회 불일치, 상위메뉴로 돌아갑니다");
					break;

				}

				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해 주세요");
			}

		} // while 끝

	}// withdrawal메소드 끝

	// 회원 등록
	public void register() {

		TheaterMember tm = new TheaterMember();

		String id = " ";
		System.out.println("=====================================");
		System.out.println("================회원가입===============");

		while (true) {

			System.out.print("id: ");
			id = sc.next();
			sc.nextLine();

			// id가 이미 있을 경우
			if (checkId(id) == true) {

				System.out.println("이미 회원가입이 되어 있는 아이디입니다.");
				continue;

			} else {

				tm.setId(id);
				break;

			}
		} // while 끝

		System.out.print("pw: ");
		String pw = sc.next();
		sc.nextLine();
		tm.setPw(pw);

		System.out.print("name: ");
		String name = sc.next();
		sc.nextLine();
		tm.setName(name);

		System.out.print("phone number: ");
		String phoneNum = sc.next();
		sc.nextLine();
		tm.setPhoneNum(phoneNum);

		System.out.print("email: ");
		String email = sc.next();
		sc.nextLine();
		tm.setEmail(email);

		tm = new TheaterMember(name, "bronze", email, phoneNum, id, pw, 0);

		dao.insertMember(tm);

		System.out.println(tm);

	}// register메소드 끝

	// 예약
	public void reservation(int select) {

		while (true) {

			// 영화관 선택
			printSelectDistrictUI();

			int selectDistrict = sc.nextInt();
			sc.nextLine();

			switch (selectDistrict) {

			case 1:
				if (select == 1)
					memberSelectMovie(selectDistrict); // 회원 예약
				else
					nonMemberSelectMovie(selectDistrict); // 비회원 예약

				break;

			case 2:
				if (select == 1)
					memberSelectMovie(selectDistrict);
				else
					nonMemberSelectMovie(selectDistrict);

				break;

			case 3:
				if (select == 1)
					memberSelectMovie(selectDistrict);
				else
					nonMemberSelectMovie(selectDistrict);

				break;

			case 4:
				if (select == 1)
					memberSelectMovie(selectDistrict);
				else
					nonMemberSelectMovie(selectDistrict);

				break;

			case 9:
				return;

			}// switch 끝

		} // while 끝

	}// reservation메소드 끝

	// 회원일 경우 영화 선택
	public void memberSelectMovie(int selectDistrict) {

		List<TheaterMember> tList = dao.getTheaterMemberInfo();
		Reservation r = null;

		// 영화관별 영화 선택
		switch (selectDistrict) {

		case 1:
			mList = dao.getShowingBucheonInfo();
			sList = dao.getBucheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 부천 현재상영영화==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 2:
			mList = dao.getShowingIncheonInfo();
			sList = dao.getIncheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 인천 현재상영영화==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 3:
			mList = dao.getShowingYongsanInfo();
			sList = dao.getYongsanSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 용산 현재상영영화==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 4:
			mList = dao.getShowingWangsibriInfo();
			sList = dao.getWangsibriSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 왕십리 현재상영영화========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		}// switch 끝

	}// memberSelectMovie메소드 끝

	// 비회원일 경우 영화 선택
	public void nonMemberSelectMovie(int selectDistrict) {

		List<Movie> mList = null;
		List<SeatInfo> sList = null;
		Reservation r = null;

		// 영화관별 영화 
		switch (selectDistrict) {

		case 1:
			mList = dao.getShowingBucheonInfo();
			sList = dao.getBucheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 부천 현재상영영화==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 2:
			mList = dao.getShowingIncheonInfo();
			sList = dao.getIncheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 인천 현재상영영화==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 3:
			mList = dao.getShowingYongsanInfo();
			sList = dao.getYongsanSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 용산 현재상영영화==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 4:
			mList = dao.getShowingWangsibriInfo();
			sList = dao.getWangsibriSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGV 왕십리 현재상영영화========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		}// switch 끝

	}// nonMemberSelectMovie메소드 끝

	// 회원일 경우 영화 예약
	public void memberReservationTheater(List<SeatInfo> sList, List<Movie> mList, List<TheaterMember> tmList,
			Reservation r) {

		for (int index = 0; index < mList.size(); index++) {
			System.out.println((index + 1) + ". " + mList.get(index));
		}

		System.out.print("영화를 선택해 주세요: ");
		int selectMovie = sc.nextInt() - 1;
		System.out.println(mList.get(selectMovie));

		String movieTitle = mList.get(selectMovie).getMovieTitle();
		String theaterName = mList.get(selectMovie).getTheaterName();
		String genre = mList.get(selectMovie).getGenre();

		int ticketPrice = 15000;
		String id = tm.getId();
		String email = " ";

		for (TheaterMember tm : tmList) {
			if (tm.getId().equals(tm.getId()))
				email = tm.getEmail();

		}

		String seat = printSelectSeatUI(sList, theaterName);

		// 영화 예약 정보를 데이터베이스에 저장
		r = new Reservation(movieTitle, theaterName, seat, ticketPrice, id, email, genre);

		dao.memberReserveTicket(r);

		System.out.println(r);

	}// memberReservationTheater메소드 끝

	// 비회원일 경우 영화 예약
	public void nonmemberReservationTheater(List<SeatInfo> sList, List<Movie> mList, Reservation r) {
		for (int index = 0; index < mList.size(); index++) {
			System.out.println((index + 1) + ". " + mList.get(index));
		}

		System.out.print("영화를 선택해 주세요: ");
		int selectMovie = sc.nextInt() - 1;
		System.out.println(mList.get(selectMovie));

		String movieTitle = mList.get(selectMovie).getMovieTitle();
		String theaterName = mList.get(selectMovie).getTheaterName();
		String seat = printSelectSeatUI(sList, theaterName);
		String genre = mList.get(selectMovie).getGenre();
		int ticketPrice = 15000;
		String email = tm.getEmail();

		r = new Reservation(movieTitle, theaterName, seat, ticketPrice, email, genre);

		dao.nonmemberReserveTicket(r);

		System.out.println(r + "\n이메일: " + email);

	}// nonmemberReservationTheater메소드 끝

	// 로그인 체크
	public boolean checkLogin(String id, String pw) {

		List<TheaterMember> tList = dao.getTheaterMemberInfo();
		tList = dao.getTheaterMemberInfo();
		boolean result = false;

		for (TheaterMember tm : tList) {
			if (tm.getId().equals(id) && tm.getPw().equals(pw)) {
				result = true;
			}
		}

		return result;
	}// checkLogin메소드 끝

	// 이미 등록된 id인지 체크
	public boolean checkId(String id) {

		List<TheaterMember> tList = dao.getTheaterMemberInfo();
		tList = dao.getTheaterMemberInfo();
		boolean result = false;

		for (TheaterMember tm : tList) {
			if (tm.getId().equals(id)) {
				result = true;
			}
		}

		return result;
	}// checkId메소드 끝

}
