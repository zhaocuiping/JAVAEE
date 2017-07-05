package com.kaishengit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	// 1.SqlSessionFactory��̬������ϵͳ����ʱֻ��һ�ݣ�������
	private static SqlSessionFactory sqlSessionFactory = builderSqlSessionFactory();

	private static SqlSessionFactory builderSqlSessionFactory() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			return sessionFactory;
		} catch (IOException e) {
			throw new RuntimeException("��ȡ�����ļ�ʱ�쳣", e);
		}
	}

	// 2.��ȡSqlSessionFactory
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	//3.��ȡSqlSession
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();

	}
}
