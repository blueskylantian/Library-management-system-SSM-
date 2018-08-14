package cn.xiangyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.UserDao;
import cn.xiangyu.entity.User;
import cn.xiangyu.service.itf.UserServiceItf;
@Service
public class UserServiceImpl implements UserServiceItf{
	@Autowired
	private UserDao mapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = mapper.findByUsername(username);
		return user;
	}
	
	
}
