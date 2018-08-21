package cn.xiangyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.AdminDao;
import cn.xiangyu.dao.itf.ReaderDao;
import cn.xiangyu.entity.AccountPO;
import cn.xiangyu.entity.ReaderPO;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.AdminServiceItf;
@Service
public class AdminServiceImpl implements AdminServiceItf {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ReaderDao readerDao;

	@Override
	public String login(String username, String password) {
		AccountPO po = adminDao.login(username, password);
		if(po == null) {
			return "账号或密码错误";
		}else{
			int readerid= po.getReader_id();
			ReaderPO readerPO = readerDao.queryReaderById(readerid);
			String adminname = readerPO.getReader_name();
			if(readerPO.getReader_type() != -1) {
				return "权限不足，无法登陆";
			}else {
				return adminname+":成功";
			}
		}
	}

	@Override
	public SettingPO showSetting() {
		SettingPO po = adminDao.querySettingById(3);
		return po;
	}

	@Override
	public void updateSetting(SettingPO po) {
		adminDao.updateSetting(po);
	}

}
