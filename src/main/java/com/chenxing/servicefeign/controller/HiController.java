/**
 * 
 */
package com.chenxing.servicefeign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenxing.common.result.BaseResult;
import com.chenxing.servicefeign.entity.Test01;
import com.chenxing.servicefeign.outeriface.Servicehi;
import com.chenxing.servicefeign.service.Test01Service;

/**
 * @author liuxing
 */
@RestController
public class HiController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Servicehi servicehi;
	@Autowired
	Test01Service test01Service;

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public BaseResult<Test01> sayHi(@RequestParam String id, @RequestParam String name, @RequestParam int currentpage,
			@RequestParam int pagesize) {
		BaseResult<Test01> result = new BaseResult<>();
		log.info(name);
		long start = System.currentTimeMillis();
		String res = null;
		try {
			res = servicehi.sayHiFromClient(name);
			res += "feign版本2.5";
			int resaa = test01Service.updateName(id, name, currentpage, pagesize);
			res = res + "访问数据库结果：" + resaa;
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		log.info("消耗时长 " + (start - end) + "毫秒");
		Test01 a = new Test01();
		a.setName(res);
		result.setData(a);
		return result;

	}
}