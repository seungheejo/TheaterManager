package theater.service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 
 * DAO(data access object)クラス -> Mapperインターフェース->
 * Mapper XML -> DB SqlSessionを通じて 
 * JAVAからデータベースにクエリーを伝える 
 *
 * MyBatisConfig.javaでデータベースのアクセス情報設定
 * 
 * */

public class MyBatisConfig {
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSqlSessionFactory() {

		if (sqlSessionFactory == null) {

			// Mybatisの環境設定したファイルリード
			String resource = "mybatis_config.xml";

			try {

				Reader reader = Resources.getResourceAsReader(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

			} catch (IOException e) {

				e.printStackTrace();

			}
		}

		return sqlSessionFactory;
	}
}
