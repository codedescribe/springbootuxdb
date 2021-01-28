package com.maxfan.springbootuxdb.mgmt;

import com.alibaba.fastjson.JSONObject;
import com.maxfan.springbootuxdb.entity.Neo4jConfig;

public interface Neo4jConfigMgmtService {
	
	
	public JSONObject findByPage(int pageNum,int pageSize,String name,String type,Integer desc,long creator);
	public JSONObject add(Neo4jConfig neo4jConfig);
	/**
	 * 删除
	 * 被占用时不能执行
	 * @param neo4jConfig
	 * @return
	 */
	public JSONObject delete(Neo4jConfig neo4jConfig);
	/**
	 * 修改
	 * 运行时不能执行
	 * @param neo4jConfig
	 * @return
	 */
	public JSONObject update(Neo4jConfig neo4jConfig);
	/**
	 * 查询对象
	 * 携带用户米密码明文
	 * @param neo4jConfig
	 * @return
	 */
	public JSONObject findById(Neo4jConfig neo4jConfig);
	public Neo4jConfig findById(long id);
	public JSONObject findVoById(Neo4jConfig neo4jConfig);
	public JSONObject findByCreator(Neo4jConfig neo4jConfig);
	public boolean checkName(Neo4jConfig neo4jConfig);
}
