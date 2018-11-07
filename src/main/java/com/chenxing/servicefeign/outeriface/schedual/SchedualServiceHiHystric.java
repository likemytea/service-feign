/**
 * 
 */
package com.chenxing.servicefeign.outeriface.schedual;

import org.springframework.stereotype.Component;

import com.chenxing.servicefeign.entity.Test01;
import com.chenxing.servicefeign.outeriface.Servicehi;

/**
 * @author HXDF
 *
 */
@Component
public class SchedualServiceHiHystric implements Servicehi {

	@Override
	public String sayHiFromClient(Test01 name) {
		return "服务配置错误或网络异常： " + name;
	}

}
