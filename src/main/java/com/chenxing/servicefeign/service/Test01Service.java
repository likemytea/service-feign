package com.chenxing.servicefeign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxing.servicefeign.dao.Test01Dao;

@Service
public class Test01Service {
	@Autowired
	private Test01Dao test01Dao;

	public int updateName(String id, String name, int currentpage, int pagesize) {
		int count = 0;
		try {
			count = test01Dao.updateName(id, name);
			test01Dao.findMessage(id, name, currentpage, pagesize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
}
