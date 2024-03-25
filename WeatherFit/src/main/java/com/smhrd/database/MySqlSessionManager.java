package com.smhrd.database;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionManager {

	// mybatis-config.xml 파일을 읽어서 Connection 객체(=SqlSession)를 빌려줄 준비를 하는 객체
	// 파일을 별도로 분리한 이유는?
	// : 수정되면 안되기 때문.
	private static SqlSessionFactory sqlSessionFactory;
	
	// static >> 제일 먼저 읽혀지는 저장공간, 고정적인 저장공간
	// MySqlSessionManager 클래스 파일을 부르는 순간. 바로 실행되는 공간
	static {
		// 자동 import 단축키 >> ctrl shift o -> ibatis 선택
		String resource = "com/smhrd/database/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// sqlSessionFactory를 되돌려주는 메서드 생성
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
