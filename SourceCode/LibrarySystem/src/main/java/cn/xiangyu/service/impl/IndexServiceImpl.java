package cn.xiangyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiangyu.dao.itf.IndexDao;
import cn.xiangyu.entity.SettingPO;
import cn.xiangyu.service.itf.IndexServiceItf;

@Service
public class IndexServiceImpl implements IndexServiceItf{
	@Autowired
	private IndexDao mapper;

	@Override
	public List<SettingPO> getSetting() {
		List<SettingPO> list = mapper.getSetting();
		return list;
	}
}
