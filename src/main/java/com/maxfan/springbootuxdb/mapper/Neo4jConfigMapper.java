package com.maxfan.springbootuxdb.mapper;

import com.maxfan.springbootuxdb.entity.Neo4jConfig;
import com.maxfan.springbootuxdb.entity.Neo4jConfigVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Neo4jConfigMapper {

	public Neo4jConfig findById(Neo4jConfig neo4jConfig);
	/**
	 * 查询明文密码数据
	 * @param id
	 * @return
	 */
	public Neo4jConfig findByIdAdmin(long id);
	public Neo4jConfigVo findVoById(Neo4jConfig neo4jConfig);
	public Neo4jConfig findByName(Neo4jConfig neo4jConfig);
	public List<Neo4jConfigVo> findAll(Map<String,Object> map);
	public List<Neo4jConfigVo> findByCreator(Neo4jConfig neo4jConfig);
	public List<Neo4jConfigVo> findByPage(Map<String,Object> map);
	public int totalRecord(Map<String,Object> map);
	public int insert(Neo4jConfig neo4jConfig);
	public int update(Neo4jConfig neo4jConfig);
	public int updateUserful(Neo4jConfig neo4jConfig);
	public int delete(Neo4jConfig neo4jConfig);
	public Neo4jConfig findUserById(Neo4jConfig neo4jConfig);
}
