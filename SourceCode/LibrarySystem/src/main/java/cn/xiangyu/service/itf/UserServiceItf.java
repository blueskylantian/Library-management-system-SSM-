package cn.xiangyu.service.itf;

import cn.xiangyu.entity.User;

public interface UserServiceItf {
	User findByUsername(String username);
}
