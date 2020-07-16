package theater.service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 
 * DAO(data access object)클래스 -> Mapper인터페이스 ->
 * Mapper XML -> DB SqlSession을 통해 
 * JAVA에서 부터 데이터베이스로 쿼리를 전달 
 *
 * MyBatisConfig.java에서 데이터베이스 엑세스 정보 설정
 * 
 * */

public class MyBatisConfig {
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSqlSessionFactory() {

		if (sqlSessionFactory == null) {

			// Mybatis의 환경설정한 파일을 읽음
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
