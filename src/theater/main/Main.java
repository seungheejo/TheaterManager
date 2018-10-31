package theater.main;

import theater.ui.JapaneseTheaterUI;
import theater.ui.KoreanTheaterUI;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			// プログラムの初期画面
			// 言語の選択
			System.out.println("==============");
			System.out.println("1. 한국어");
			System.out.println("2. 일본어");
			System.out.print("언어를 선택하세요: ");

			int select = sc.nextInt();
			sc.nextLine();

			while (true) {

				// UIが韓国語で提供
				if (select == 1) {
					new KoreanTheaterUI().startUI();
					sc.close();

					// UIが日本語で提供
				} else if (select == 2) {
					new JapaneseTheaterUI().startUI();
					sc.close();

					// 数字が１，２と違う場合
				} else {
					System.out.println("다시 선택해 주세요");
					break;
				}

			} // インナーwhileループエンド

		} // アウターwhileループエンド

	}

}
