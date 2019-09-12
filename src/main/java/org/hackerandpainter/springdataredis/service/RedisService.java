package org.hackerandpainter.springdataredis.service;

public interface RedisService {
     String getValue(String key);
     void setValue(String key,String value);

}
