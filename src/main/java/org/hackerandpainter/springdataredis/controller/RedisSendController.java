package org.hackerandpainter.springdataredis.controller;

import org.hackerandpainter.springdataredis.service.RedisService;
import org.hackerandpainter.springdataredis.service.impl.RedisServiceImpl;
import org.hackerandpainter.springdataredis.utils.StateParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/redis")

public class RedisSendController {

    RedisService redisCluster = new RedisServiceImpl();

    @RequestMapping(value = "getRedis",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getRedis(@RequestParam String time){

        System.out.println("---------------");

        return getModelMap(StateParameter.SUCCESS, redisCluster.getValue(time), "执行成功");
    }

    @RequestMapping(value = "setRedis",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap setRedis(@RequestParam String key,@RequestParam String value){

        System.out.println("key="+key+"+++++++++++++value="+value);

        redisCluster.setValue(key,value);
        return getModelMap(StateParameter.SUCCESS, "成功存入","执行成功");
    }

    public ModelMap getModelMap(String status,Object data,String msg){
        ModelMap modelMap=new ModelMap();
        modelMap.put("status", status);
        modelMap.put("data", data);
        modelMap.put("msg", msg);
        return modelMap;

    }
}
