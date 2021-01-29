package com.maxfan.springbootuxdb.mgmt.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxfan.springbootuxdb.entity.Neo4jConfig;
import com.maxfan.springbootuxdb.entity.Neo4jConfigVo;
import com.maxfan.springbootuxdb.entity.ResultEntity;
import com.maxfan.springbootuxdb.mapper.Neo4jConfigMapper;
import com.maxfan.springbootuxdb.mgmt.Neo4jConfigMgmtService;
import com.maxfan.springbootuxdb.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "neo4jConfigMgmtService")
public class Neo4jConfigMgmtServiceImpl implements Neo4jConfigMgmtService {
     @Autowired
    Neo4jConfigMapper neo4jConfigMapper;

    @Override
    public JSONObject findByPage(int pageNum, int pageSize, String name, String type, Integer desc, long creator) {
        JSONObject json = new JSONObject();
        try {
            Page<Neo4jConfigVo> page = new Page<>(pageNum, pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("ignoreSize", page.getIgnoreNum());
            map.put("pageSize", page.getPageSize());
            if (null != name && !"".equals(name)) {
                map.put("name", name);
            }
            if (null != type && !"".equals(type)) {
                map.put("type", type);
            }
            if (null != desc) {
                map.put("desc", desc);
            }
            map.put("creator", creator);
            List<Neo4jConfigVo> list = neo4jConfigMapper.findByPage(map);
            int totalRecord = neo4jConfigMapper.totalRecord(map);
            page.setList(list);
            page.setTotalRecord(totalRecord);
            json.put("code", ResultEntity.SUCCESS);
            json.put("data", page);
            json.put("msg", "查询成功!");
        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    @Override
    public JSONObject delete(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        try {
            //检查是否被本体占用
            Map<String, Object> param = new HashMap<>();
            param.put("nid", neo4jConfig.getId());
            param.put("creator", neo4jConfig.getCreator());

            int re = neo4jConfigMapper.delete(neo4jConfig);
            if (re > 0) {
                json.put("code", ResultEntity.SUCCESS);
                json.put("msg", "删除成功!");
            } else {
                json.put("code", ResultEntity.FAILURE);
                json.put("msg", "没有记录被删除!");
            }

        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    @Override
    public JSONObject update(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        if (null == neo4jConfig.getName() || "".equals(neo4jConfig.getName()) || null == neo4jConfig.getUsername()
                || "".equals(neo4jConfig.getUsername()) || neo4jConfig.getName().contains(" ")
                || neo4jConfig.getPassword().contains(" ")) {
            json.put("code", ResultEntity.FAILURE);
            json.put("msg", "检查用户名、连接地址、名称不要包含空格!");
            return json;
        }

        try {
            boolean check = checkName(neo4jConfig);
            if (!check) {
                json.put("code", ResultEntity.FAILURE);
                json.put("msg", "名称重复,请修改名称!");
                return json;
            }
            //如果密码没有填就不修改密码,密码不能为空
//			neo4jConfig.setUsername(new String(Base64.decodeBase64(neo4jConfig.getUsername()),"UTF-8"));
//			neo4jConfig.setPassword(new String(Base64.decodeBase64(neo4jConfig.getPassword()),"UTF-8"));
//			neo4jConfig.setUrl(new String(Base64.decodeBase64(neo4jConfig.getUrl()),"UTF-8"));

            neo4jConfig.setIsuseful(false);
            int i = neo4jConfigMapper.update(neo4jConfig);
            if (i > 0) {
                    neo4jConfig.setIsuseful(true);
                    int re = neo4jConfigMapper.updateUserful(neo4jConfig);
                    json.put("msg", "修改成功!数据连接可用!");
                    json.put("code", ResultEntity.SUCCESS);

            } else {
                json.put("code", ResultEntity.FAILURE);
                json.put("msg", "修改失败!");
            }
        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    @Override
    public JSONObject findById(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        try {
            //不返回具体账号密码
            Neo4jConfig nc = neo4jConfigMapper.findById(neo4jConfig);
            json.put("code", ResultEntity.SUCCESS);
            json.put("msg", "查询成功!");
            json.put("data", nc);
        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    @Override
    public Neo4jConfig findById(long id) {
        Neo4jConfig neo4jConfig = new Neo4jConfig();
        neo4jConfig.setId(id);
        return neo4jConfigMapper.findById(neo4jConfig);
    }

    @Override
    public JSONObject add(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        try {

            int i = neo4jConfigMapper.insert(neo4jConfig);
            if (i > 0) {
                //检测连接可用性
                json.put("code", ResultEntity.SUCCESS);
                json.put("msg", "添加成功!");
            } else {
                json.put("code", ResultEntity.FAILURE);
                json.put("msg", "添加失败!");
            }
        } catch (Exception e) {
//			e.printStackTrace();
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    /**
     * 检查
     *
     * @param in
     * @return
     */
    @Override
    public boolean checkName(Neo4jConfig in) {
        Neo4jConfig nc = neo4jConfigMapper.findByName(in);
        if (null != nc && nc.getId() != in.getId()) {
            return false;
        }
        return true;
    }


    private Neo4jConfig findUserById(Neo4jConfig neo4jConfig) {
        Neo4jConfig nc = neo4jConfigMapper.findUserById(neo4jConfig);
        return nc;
    }

    @Override
    public JSONObject findVoById(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        try {
            Neo4jConfigVo nc = neo4jConfigMapper.findVoById(neo4jConfig);
            json.put("code", ResultEntity.SUCCESS);
            json.put("msg", "查询成功!");
            json.put("data", nc);
        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }



    @Override
    public JSONObject findByCreator(Neo4jConfig neo4jConfig) {
        JSONObject json = new JSONObject();
        try {
            List<Neo4jConfigVo> list = neo4jConfigMapper.findByCreator(neo4jConfig);
            json.put("code", ResultEntity.SUCCESS);
            json.put("msg", "查询成功!");
            json.put("data", list);
        } catch (Exception e) {
            json.put("code", ResultEntity.EXCEPTION);
            json.put("msg", "出现异常:" + e.getMessage());
        }
        return json;
    }

    public static boolean isIpLegal(String in) {
        if (StringUtils.isEmpty(in)) {
            return false;
        }
        String startStr = "bolt://";
        //检查ip是否为空
        if (!in.contains(startStr) || in.indexOf(startStr) != 0) {
            return false;
        }
        String end = in.substring(7);
        if (!end.contains(":")) {
            return false;
        }
        String str = end.split(":")[0];
        String portStr = end.split(":")[1];
        try {
            int port = Integer.parseInt(portStr);
            if (port < 1 || port > 65535) {
                return false;
            }
            //检查ip长度，最短x.x.x.x（7位），最长为xxx.xxx.xxx.xxx（15位）
            if (str.length() < 7 || str.length() > 15) {
                return false;
            }
            //对输入字符串的首末字符判断，如果是“.”，则是非法ip
            if (str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.') {
                return false;
            }
            //按"."分割字符串，并判断分割出来的个数，如果不是4个，则是非法ip
            String[] arr = str.split("\\.");
            if (arr.length != 4) {
                return false;
            }
            //对分割出来的字符串进行单独判断
            for (int i = 0; i < arr.length; i++) {
                //如果每个字符串不是一位字符，且以'0'开头，则是非法ip,如01.02.03.004
                if (arr[i].length() > 1 && arr[i].charAt(0) == '0') {
                    return false;
                }
                //对每个字符串的每个字符进行逐一判断，如果不是数字0-9则是非法ip
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) < '0' || arr[i].charAt(j) > '9') {
                        return false;
                    }
                }
            }
            //对拆分的每一个字符串进行转换成数字，并判断是否在0-255
            for (int i = 0; i < arr.length; i++) {
                int temp = Integer.parseInt(arr[i]);
                if (i == 0) {
                    if (temp < 1 || temp > 255) {
                        return false;
                    }
                } else {
                    if (temp < 0 || temp > 255) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
