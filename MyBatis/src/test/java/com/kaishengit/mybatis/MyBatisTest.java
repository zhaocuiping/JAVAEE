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
		// 1.加载配置文件
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.创建sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession();
		// 4.操作数据库
		User user = sqlSessoin.selectOne("com.kaishengit.mapper.usermapper.findById",2);
		System.out.println(user.getUserName());
		System.out.println(user.getAddress());
		// 5.关闭sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void findAll() throws Exception {
		
		// 3.创建sqlSessoin
		SqlSession sqlSessoin = MyBatisUtil.getSqlSession();
		// 4.操作数据库
		List<User> userList = sqlSessoin.selectList("com.kaishengit.mapper.usermapper.findAll");
		for (User user : userList) {
			logger.debug("{}->{}->{}",user.getId(),user.getUserName(),user.getAddress());
			
		}
		
		// 5.关闭sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void save() throws Exception {
		// 1.加载配置文件
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.创建sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession();
		// 4.操作数据库
		User user = new User();
		user.setUserName("赵一凡");
		user.setPassword("111");
		user.setAddress("中国");
		
		sqlSessoin.insert("com.kaishengit.mapper.usermapper.save",user);
		//手动提交事务
		sqlSessoin.commit();
		// 5.关闭sqlSessoin
		sqlSessoin.close();
	}
	
	
	@Test
	public void update() throws Exception {
		// 1.加载配置文件
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.创建sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession(true);//自动提交事务
		// 4.操作数据库
		
		User user=sqlSessoin.selectOne("com.kaishengit.mapper.usermapper.findById",4);
		user.setUserName("Ann");
		user.setPassword("222");
		user.setAddress("米兰");
		sqlSessoin.update("com.kaishengit.mapper.usermapper.update", user);
		// 5.关闭sqlSessoin
		sqlSessoin.close();
	}
	
	@Test
	public void delete() throws Exception {
		// 1.加载配置文件
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 2.创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 3.创建sqlSessoin
		SqlSession sqlSessoin = sqlSessionFactory.openSession(true);//自动提交事务
		// 4.操作数据库
		
		sqlSessoin.delete("com.kaishengit.mapper.usermapper.delById",7);

		// 5.关闭sqlSessoin
		sqlSessoin.close();
	}
}
