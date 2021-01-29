package com.maxfan.springbootuxdb.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxfan.springbootuxdb.entity.Neo4jConfig;
import com.maxfan.springbootuxdb.mgmt.Neo4jConfigMgmtService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 图数据库管理
 * @author yangfan
 *
 */
@RestController
@RequestMapping(value = "neo4j")
public class Neo4jConfigController {
   @Autowired
   Neo4jConfigMgmtService neo4jConfigMgmtService;
   @ResponseBody
   @RequestMapping(value = "findById",method= RequestMethod.GET)
  	public Object findByPage(@Param("id")long id) {
  	   long creator=174;
	   Neo4jConfig neo4jConfig=new Neo4jConfig();
	   neo4jConfig.setId(id);
	   neo4jConfig.setCreator(creator);
  		JSONObject json =neo4jConfigMgmtService.findById(neo4jConfig);
  		return json;
  	}
   @ResponseBody
   @RequestMapping(value = "findVoById",method= RequestMethod.GET)
  	public Object findVoByPage(@Param("id")long id) {
  	   long creator=174;
	   Neo4jConfig neo4jConfig=new Neo4jConfig();
	   neo4jConfig.setId(id);
  		JSONObject json =neo4jConfigMgmtService.findVoById(neo4jConfig);
  		return json;
  	}
    @ResponseBody
    @RequestMapping(value = "findByPage",method= RequestMethod.GET)
   	public Object findByPage(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,
   			String name,String type,Integer desc) {
    	
   	   long creator=174;
   		JSONObject json =neo4jConfigMgmtService.findByPage(pageNum, pageSize, name,type,desc,creator);
   		return json;
   	}
    @ResponseBody
    @RequestMapping(value = "findAll",method= RequestMethod.GET)
   	public Object findAll() {
   	   long creator=174;
    	Neo4jConfig neo=new Neo4jConfig();
    	neo.setCreator(creator);
   		JSONObject json =neo4jConfigMgmtService.findByCreator(neo);
   		return json;
   	}
    
    @ResponseBody
    @RequestMapping(value = "create",method= RequestMethod.POST)
   	public Object add() {
   	   long creator=174;
		Neo4jConfig neo4jConfig=new Neo4jConfig();
		neo4jConfig.setName("dsadas");
		neo4jConfig.setPassword("dsadas");
		neo4jConfig.setCreator(creator);
		neo4jConfig.setIsuseful(true);
   		JSONObject json =neo4jConfigMgmtService.add(neo4jConfig);
   		return json;
   	}
    @ResponseBody
    @RequestMapping(value = "update",method= RequestMethod.POST)
   	public Object update(Neo4jConfig neo4jConfig) {
   	   long creator=174;
    	neo4jConfig.setCreator(creator);
   		JSONObject json =neo4jConfigMgmtService.update(neo4jConfig);
   		return json;
   	}
    @ResponseBody
    @RequestMapping(value = "delete",method= RequestMethod.POST)
   	public Object delete(Neo4jConfig neo4jConfig) {
   	   long creator=174;
  	   neo4jConfig.setCreator(creator);
   		JSONObject json = neo4jConfigMgmtService.delete(neo4jConfig);
   		return json;
   	}
   
}
