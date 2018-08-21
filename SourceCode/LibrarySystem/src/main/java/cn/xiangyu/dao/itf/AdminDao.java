package cn.xiangyu.dao.itf;


import org.apache.ibatis.annotations.Param;

import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.SettingPO;

public interface AdminDao {
	AccountPO login(@Param("username")String username,@Param("password")String password);
	SettingPO querySettingById(int settingId);
	int updateSetting(SettingPO po);
}
