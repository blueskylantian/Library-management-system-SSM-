package cn.xiangyu.service.itf;

import cn.xiangyu.entity.SettingPO;

public interface AdminServiceItf {
	String login(String userrname,String password);
	SettingPO showSetting();
	void updateSetting(SettingPO po);
}
