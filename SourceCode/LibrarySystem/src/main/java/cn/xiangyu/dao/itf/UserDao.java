package cn.xiangyu.dao.itf;

import cn.xiangyu.entity.User;

public interface UserDao {
	
	   public abstract User findByUsername(String username);
	
}
