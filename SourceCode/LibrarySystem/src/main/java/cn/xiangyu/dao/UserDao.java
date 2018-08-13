package cn.xiangyu.dao;

import cn.xiangyu.domain.User;

public interface UserDao {
	
	   public abstract User findByUsername(String username);
	
}
