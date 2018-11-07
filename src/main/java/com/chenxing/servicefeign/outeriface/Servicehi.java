/**
 * 
 */
package com.chenxing.servicefeign.outeriface;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chenxing.servicefeign.entity.Test01;
import com.chenxing.servicefeign.outeriface.schedual.SchedualServiceHiHystric;

/**
 * @author HXDF
 *
 */
@FeignClient(value = "eurekaclient111", fallback = SchedualServiceHiHystric.class)
public interface Servicehi {
	@RequestMapping(value = "/hi", method = RequestMethod.POST)
	String sayHiFromClient(@RequestBody Test01 xxx);
}
