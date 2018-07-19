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

import com.alibaba.fastjson.JSON;
import com.chenxing.common.result.BaseResult;
import com.chenxing.common3.test.Test1;
import com.chenxing.servicefeign.entity.Test01;
import com.chenxing.servicefeign.outeriface.Servicehi;
import com.chenxing.servicefeign.service.Test01Service;
import com.chenxing.servicefeign.serviceI.RedisService;

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
	@Autowired
	private RedisService redisService;

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

	@RequestMapping("/saveToRedis")
	public String saveToRedis() { // 这里用于测试，key值可以自定义实现
		Test01 t = new Test01();
		t.setId("safsd");
		t.setName("安吉lie");
		redisService.set("123456", t);
		return "SUCCESS";
	}

	@RequestMapping("/getFromRedis")
	public String getFromRedis() { // 这里用于测试，key值可以自定义实现
		// JSONObject jsonObject = (JSONObject) redisService.get("123456");
		Test1 t = new Test1();
		return JSON.toJSONString(redisService.get("123456")) + t.get0();
	}

}