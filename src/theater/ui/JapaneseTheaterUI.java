package theater.ui;

import theater.service.TheaterDAO;
import theater.vo.Movie;
import theater.vo.Reservation;
import theater.vo.SeatInfo;
import theater.vo.TheaterMember;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class JapaneseTheaterUI {

	private TheaterDAO dao = new TheaterDAO();
	private Scanner sc = new Scanner(System.in);
	private List<SeatInfo> sList = null;
	private List<Movie> mList = null;

	TheaterMember tm = new TheaterMember();

	public JapaneseTheaterUI() {

		sList = new ArrayList<>();
		mList = new ArrayList<>();
	}

	public void startUI() {
		while (true) {

			printMainUI();

			int select = sc.nextInt();

			switch (select) {

			// メンバーの予約の場合
			case 1:
				System.out.println("=====================================");
				System.out.println("=================ログイン================");
				System.out.print("idを入力してください: ");
				String id = sc.next();
				tm.setId(id);
				sc.nextLine();

				System.out.print("passwordを入力してください: ");
				String pw = sc.next();
				sc.nextLine();

				// ログインの成功の場合
				if (checkLogin(id, pw) == true) {

					System.out.println("ログインできました");
					System.out.println("=====================================");
					System.out.println("=====================================");
					System.out.println("1. 映画の予約");
					System.out.println("2. 脱退");
					System.out.println("9. プログラム終了");
					System.out.print("入力してください: ");
					int menu = sc.nextInt();
					sc.nextLine();

					if (menu == 1) {

						// 映画のおすすめ
						recommendMovie(id);
						// 映画予約
						reservation(select);

					} else if (menu == 2) {

						// 脱退
						withdrawal(id);

					} else {

						// プログラム終了
						System.out.println("プログラム終了");
						return;

					}

					break;

				} else {

					// ログインの失敗の場合
					System.out.println("ログイン失敗");

					// 上のメニューに戻る
					break;

				}

				// ノンメンバーの予約の場合
			case 2:
				System.out.println("=====================================");
				System.out.println("===============ノンメンバー の予約 ==============");
				System.out.print("メールアドレスを入力してください: ");
				String email = sc.next();
				sc.nextLine();
				tm.setEmail(email);

				System.out.print("ケータイ番号を入力してください: ");
				String phonenum = sc.next();
				sc.nextLine();
				tm.setPhoneNum(phonenum);

				reservation(select);

				break;

			// メンバー登録の場合
			case 3:
				register();

				break;

			// 予約の確認の場合
			case 4:
				System.out.println("=======予約の確認=======");
				System.out.println("1. メンバー");
				System.out.println("2. ノンメンバー");
				System.out.print("入力してください: ");
				int choice = sc.nextInt();
				sc.nextLine();

				getReservationInfo(choice);

				break;

			// プログラム終了
			case 9:
				System.out.println("プログラムを終了します。");
				System.exit(0);

			}// スイッチエンド

		} // whileループエンド

	}// startUIメソッドエンド

	// 映画予約プログラムの基本ユーザーインターフェース
	public void printMainUI() {

		System.out.println("=====================================");
		System.out.println("==========映画予約システム==========");
		System.out.println("=====================================");
		System.out.println("1. メンバー");
		System.out.println("2. ノンメンバー");
		System.out.println("3. 登録");
		System.out.println("4. 予約の確認");
		System.out.println("9. プログラム 終了");
		System.out.println("=====================================");
		System.out.print("入力してください: ");

	}

	// 映画館の選択のユーザーインターフェース
	public void printSelectDistrictUI() {

		System.out.println("=====================================");
		System.out.println("===============映画館を選択==============");
		System.out.println("1. SGV プチョン");
		System.out.println("2. SGV インチョン");
		System.out.println("3. SGV ヨンサン");
		System.out.println("4. SGV ワンシムニ");
		System.out.println("=====================================");
		System.out.print("入力してください: ");

	}

	// 座席を選択するユーザーインターフェース
	public String printSelectSeatUI(List<SeatInfo> sList, String theaterName) {

		System.out.println("=====================================");
		System.out.println("================席を選択==============");

		// 空いている席を"□"の形に設定
		String[][] seats = { { "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" },
				{ "□", "□", "□", "□", "□", "□", "□", "□", "□", "□" } };

		// 映画館によって座席情報をリストに入れる
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

		// 全ての座席を区別するために
		String row[] = { "a", "b", "c", "d" }; // a b c d
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
							// 座席を予約できる場合
							seats[i][j] = "□";
						} else {
							// 座席を予約できない場合
							seats[i][j] = "■";
						}
					}
				}

			} // インナーforループエンド

		} // アウターforループエンド

		// 映画の現在の座席情報を見るために
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

		// 座席を選択の場合
		// 座席のローの名とカラムの名をつけて入力しなければならない
		System.out.print("席を選択してください(ex a01): ");
		String seatSelect = sc.next();
		sc.nextLine();

		String inputRow = seatSelect.substring(0, 1);
		String inputColumn = seatSelect.substring(1, 3);

		updateSeatsInfo(theaterName, booking, seats, row, column, inputRow, inputColumn);

		return seatSelect;

	}// printSelectSeatUIメソッドエンド

	// 座席情報のアップデート
	public void updateSeatsInfo(String theaterName, String booking, String[][] seats, String[] row, String[] column,
			String inputRow, String inputColumn) {
		int indexI = 0;
		int indexJ = 0;

		SeatInfo s = new SeatInfo();
		s.setSeats(inputRow + inputColumn);

		// プチョン映画館の場合
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

					// 予約できる場合
					if (sList.get(index).getBookingInfo().equals("예약가능")) {
						// 座席を予約して"■"で変更する
						seats[indexI][indexJ] = "■";
						dao.updateBucheonBookingInfo(inputRow.concat(inputColumn));

					} else {
						System.out.println("不可能");
					}
				}

			} // forループエンド
		} // アウターifエンド

		// インチョン映画館の場合
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
						System.out.println("不可能");
					}
				}

			} // forループエンド
		} // アウターifエンド

		// ヨンサン映画館の場合
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
						System.out.println("不可能");
					}
				}

			} // forループエンド
		} // アウターifエンド

		// ワンシムニ映画館の場合
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
						System.out.println("不可能");
					}
				}

			} // forループエンド
		} // アウターifエンド

	}// updateSeatsInfoメソッドエンド

	// メンバー別の映画おすすめ
	public void recommendMovie(String id) {

		System.out.println("========= " + id + "のお客さまにおすすめ映画===========");

		// メンバー別の予約した情報をリストに入れる
		List<Reservation> rList = dao.idReservationInfo(id);

		String genre = " ";
		int check[] = new int[7];
		String genres[] = { "SF", "공포", "드라마", "스릴러", "액션", "코미디", "판타지" };

		for (int index = 0; index < rList.size(); index++) {
			for (int g = 0; g < genres.length; g++) {
				/*
				 * genresアレイの中のことと リストのジャンルがマッチングすれば
				 */
				if (genres[g].equals(rList.get(index).getGenre())) {
					/*
					 * その時のgenres配列のインデックスと同じインデックスにある check配列の値を増加させる
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

		// 一番多くカウントされたジャンルを保存する
		genre = genres[index];

		// ジャンルが同じ映画情報をリストに入れる
		mList = dao.getMovieRecommend(genre);

		for (Movie m : mList) {
			System.out.println(m);
		}

	}// recommendMovieメソッドエンド

	// 予約の情報
	public void getReservationInfo(int choice) {

		List<Reservation> rList = dao.reservationInfo();

		// メンバーの場合
		if (choice == 1) {

			while (true) {
				System.out.println("=========ログイン=========");
				System.out.print("id: ");
				String id = sc.next();
				sc.nextLine();

				System.out.print("pw: ");
				String pw = sc.next();
				sc.nextLine();

				// ログイン成功の場合
				if (checkLogin(id, pw) == true) {

					System.out.println("ログイン成功");
					System.out.println("\n===============================");
					System.out.println("1. 予約の確認  or キャンセル");
					System.out.println("2. 上に戻る");
					System.out.print("選ぶ: ");
					int menu = sc.nextInt();
					sc.nextLine();

					// 予約の確認 or キャンセル
					if (menu == 1) {
						int index = 0;
						for (index = 0; index < rList.size(); index++) {
							if (id.equals(rList.get(index).getId())) {
								System.out.println("\n\nreserveNum: " + rList.get(index).getReserveNum() + " \n"
										+ rList.get(index));

							}
						}

						System.out.print("予約番号をインプット: ");
						int c = sc.nextInt();

						dao.deleteReservedTicket(c);
						System.out.println("キャンセルできました。");

					} else {
						// ログイン失敗の場合
						System.out.println("ログイン 失敗");
						return;
					}

				} else
					break;

			} // whileループエンド

		} else {
			// ノンメンバーの場合
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

				System.out.print("予約番号をインプット: ");
				int c = sc.nextInt();

				dao.deleteReservedTicket(c);
				System.out.println("キャンセルできました。");

			} // whileループエンド

		} // elseエンド

	}// getReservationInfoメソッドエンド

	// 脱退
	public void withdrawal(String id) {

		int count = 0;

		while (true) {

			System.out.print("また、パスワードを 入力してください : ");
			String pw = sc.next();
			sc.nextLine();

			if (checkLogin(id, pw) == true) {

				dao.deleteMember(id);
				System.out.println("脱退できました。");
				break;

			} else {

				count++;

				if (count == 3) {
					// パスワードが3回違うと上に戻る
					System.out.println("パスワード 3回間違いました, 上に戻る");
					break;
				}

				System.out.println("パスワード 3回間違いました。 また、パスワードを 入力してください。");
			}

		} // whileループエンド

	}// withdrawalメソッドエンド

	// メンバー登録
	public void register() {

		TheaterMember tm = new TheaterMember();

		String id = " ";
		System.out.println("=====================================");
		System.out.println("================メンバー登録===============");

		while (true) {

			System.out.print("id: ");
			id = sc.next();
			sc.nextLine();

			// idがもうある場合
			if (checkId(id) == true) {

				System.out.println("もう存在しているidです。");
				continue;

			} else {

				tm.setId(id);
				break;

			}
		} // whileループエンド

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

	}// registerメソッドエンド

	// 予約
	public void reservation(int select) {

		while (true) {

			// 映画館を選択
			printSelectDistrictUI();

			int selectDistrict = sc.nextInt();
			sc.nextLine();

			switch (selectDistrict) {

			case 1:
				if (select == 1)
					memberSelectMovie(selectDistrict); // メンバーの予約
				else
					nonMemberSelectMovie(selectDistrict); // ノンメンバーの予約

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

			}// スイッチエンド

		} // whileループエンド

	}// reservationメソッドエンド

	// メンバーの場合の映画選択
	public void memberSelectMovie(int selectDistrict) {

		List<TheaterMember> tList = dao.getTheaterMemberInfo();
		Reservation r = null;

		// 映画館別の映画選択
		switch (selectDistrict) {

		case 1:
			mList = dao.getShowingBucheonInfo();
			sList = dao.getBucheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVプチョンの映画情報==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 2:
			mList = dao.getShowingIncheonInfo();
			sList = dao.getIncheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVインチョンの映画情報==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 3:
			mList = dao.getShowingYongsanInfo();
			sList = dao.getYongsanSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVヨンサンの映画情報==========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		case 4:
			mList = dao.getShowingWangsibriInfo();
			sList = dao.getWangsibriSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVワンシムニの映画情報========");
			memberReservationTheater(sList, mList, tList, r);

			break;

		}// スイッチエンド

	}// memberSelectMovieメソッドエンド

	// ノンメンバーの場合の映画選択
	public void nonMemberSelectMovie(int selectDistrict) {

		List<Movie> mList = null;
		List<SeatInfo> sList = null;
		Reservation r = null;

		// 映画館別の映画選択
		switch (selectDistrict) {

		case 1:
			mList = dao.getShowingBucheonInfo();
			sList = dao.getBucheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVプチョンの映画情報==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 2:
			mList = dao.getShowingIncheonInfo();
			sList = dao.getIncheonSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVインチョンの映画情報==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 3:
			mList = dao.getShowingYongsanInfo();
			sList = dao.getYongsanSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVヨンサンの映画情報==========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		case 4:
			mList = dao.getShowingWangsibriInfo();
			sList = dao.getWangsibriSeatsInfo();
			System.out.println("=====================================");
			System.out.println("===========SGVワンシムニの映画情報========");
			nonmemberReservationTheater(sList, mList, r);

			break;

		}// スイッチエンド

	}// nonMemberSelectMovieメソッドエンド

	// メンバーの場合の映画予約
	public void memberReservationTheater(List<SeatInfo> sList, List<Movie> mList, List<TheaterMember> tmList,
			Reservation r) {

		for (int index = 0; index < mList.size(); index++) {
			System.out.println((index + 1) + ". " + mList.get(index));
		}

		System.out.print("映画を選ぶ: ");
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

		// 映画の予約情報をデータベースに保存
		r = new Reservation(movieTitle, theaterName, seat, ticketPrice, id, email, genre);

		dao.memberReserveTicket(r);

		System.out.println(r);
	}// memberReservationTheaterメソッドエンド

	// ノンメンバーの場合の映画予約
	public void nonmemberReservationTheater(List<SeatInfo> sList, List<Movie> mList, Reservation r) {
		for (int index = 0; index < mList.size(); index++) {
			System.out.println((index + 1) + ". " + mList.get(index));
		}

		System.out.print("映画を選ぶ: ");
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

		System.out.println(r + "\nメールアドレス: " + email);

	}// nonmemberReservationTheaterメソッドエンド

	// ログインチェック
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
	}// checkLoginメソッドエンド

	// idがもうあるかチェック
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
	}// checkIdメソッドエンド
}
