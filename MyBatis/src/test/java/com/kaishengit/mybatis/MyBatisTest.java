package com.kaishengit.mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;
import com.kaishengit.util.MyBatisUtil;

public class MyBatisTest {
	private Logger logger=LoggerFactory.getLogger(MyBatisTest.class);
	@Test
	public void findById() throws Exception {
		// 1.���������ļ�
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.����sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession();
		// 4.�������ݿ�
		User user = sqlSessoin.selectOne("com.kaishengit.mapper.usermapper.findById",2);
		System.out.println(user.getUserName());
		System.out.println(user.getAddress());
		// 5.�ر�sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void findAll() throws Exception {
		
		// 3.����sqlSessoin
		SqlSession sqlSessoin = MyBatisUtil.getSqlSession();
		// 4.�������ݿ�
		List<User> userList = sqlSessoin.selectList("com.kaishengit.mapper.usermapper.findAll");
		for (User user : userList) {
			logger.debug("{}->{}->{}",user.getId(),user.getUserName(),user.getAddress());
			
		}
		
		// 5.�ر�sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void save() throws Exception {
		// 1.���������ļ�
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.����sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession();
		// 4.�������ݿ�
		User user = new User();
		user.setUserName("��һ��");
		user.setPassword("111");
		user.setAddress("�й�");
		
		sqlSessoin.insert("com.kaishengit.mapper.usermapper.save",user);
		//�ֶ��ύ����
		sqlSessoin.commit();
		// 5.�ر�sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void update() throws Exception {
		// 1.���������ļ�
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.����sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession(true);//�Զ��ύ����
		// 4.�������ݿ�
		
		User user=sqlSessoin.selectOne("com.kaishengit.mapper.usermapper.findById",4);
		user.setUserName("Ann");
		user.setPassword("222");
		user.setAddress("����");
		sqlSessoin.update("com.kaishengit.mapper.usermapper.update", user);
		// 5.�ر�sqlSessoin
		sqlSessoin.close();
	}
	
	@Test
	public void delete() throws Exception {
		// 1.���������ļ�
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.����sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession(true);//�Զ��ύ����
		// 4.�������ݿ�
		
		sqlSessoin.delete("com.kaishengit.mapper.usermapper.delById",7);

		// 5.�ر�sqlSessoin
		sqlSessoin.close();
	}
}
