package com.chenxing.servicefeign.serviceI;

public interface RedisService {
	void set(String key, Object value);

	Object get(String key);
}
