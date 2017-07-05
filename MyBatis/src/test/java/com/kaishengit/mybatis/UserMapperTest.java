package com.kaishengit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MyBatisUtil;

public class UserMapperTest {
	private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

	@Test
	public void findById() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findById(8);
		logger.debug("user: {}",user);
		sqlSession.close();
	}
	
	@Test
	public void save(){
		SqlSession sqlSession=MyBatisUtil.getSqlSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		
		User user=new User();
		user.setUserName("’‘¥‰∆Ω");
		user.setAddress("Ã®ÕÂ");
		user.setPassword("123456");
		
		userMapper.save(user);
		sqlSession.commit();
		
	}
}
