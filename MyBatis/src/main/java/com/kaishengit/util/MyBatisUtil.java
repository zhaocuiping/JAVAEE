package com.kaishengit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	// 1.SqlSessionFactory静态，整个系统运行时只有一份（单例）
	private static SqlSessionFactory sqlSessionFactory = builderSqlSessionFactory();

	private static SqlSessionFactory builderSqlSessionFactory() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			return sessionFactory;
		} catch (IOException e) {
			throw new RuntimeException("读取配置文件时异常", e);
		}
	}

	// 2.获取SqlSessionFactory
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	//3.获取SqlSession
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();

	}
}
