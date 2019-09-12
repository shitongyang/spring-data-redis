package org.hackerandpainter.springdataredis.service.impl;

import org.hackerandpainter.springdataredis.service.RedisService;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;


public class RedisServiceImpl implements RedisService {

    public static JedisCluster getConnection(){

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(10);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
       //poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.3.70", 9001));
        nodes.add(new HostAndPort("192.168.3.70", 9002));
        nodes.add(new HostAndPort("192.168.3.70", 9003));
        nodes.add(new HostAndPort("192.168.3.70", 9004));
        nodes.add(new HostAndPort("192.168.3.70", 9005));
        nodes.add(new HostAndPort("192.168.3.70", 9006));
        JedisCluster cluster = new JedisCluster(nodes, 1000,poolConfig);
        return cluster;
    }

    public String getValue(String key){
        JedisCluster cluster=getConnection();


        String value = cluster.get(key);
        cluster.close();
        //System.out.println(value+"++++++");
        return value;
    }

    public void setValue(String key,String value){

        JedisCluster cluster=getConnection();

        cluster.set(key,value);

        System.out.println(cluster.get(key)+"***********");
        cluster.close();
    }



}
