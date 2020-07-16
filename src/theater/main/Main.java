package theater.main;

import theater.ui.JapaneseTheaterUI;
import theater.ui.KoreanTheaterUI;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			// 프로그램 초기화면
			// 언어 선택
			System.out.println("==============");
			System.out.println("1. 한국어");
			System.out.println("2. 일본어");
			System.out.print("언어를 선택하세요: ");

			int select = sc.nextInt();
			sc.nextLine();

			while (true) {

				// UI가 한국어로 제공됨
				if (select == 1) {
					new KoreanTheaterUI().startUI();
					sc.close();

					// UI가 일본어로 제공됨
				} else if (select == 2) {
					new JapaneseTheaterUI().startUI();
					sc.close();

					// 입력한 숫자가１，２가 아닐 경우
				} else {
					System.out.println("다시 선택해 주세요");
					break;
				}

			} // inner while 끝

		} // outer while 끝

	}

}
