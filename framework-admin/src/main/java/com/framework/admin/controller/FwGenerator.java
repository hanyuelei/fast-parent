package com.framework.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.framework.admin.entity.vo.TablesVO;
import com.framework.admin.service.FwUserService;
import com.framework.admin.util.GenUtils;
import com.framework.common.util.result.Result;

@Controller
@RequestMapping("admin/system/generator")
public class FwGenerator {
	@Autowired
	private FwUserService fwUserService;
	
	@GetMapping(value = "list")
	public String list(HttpSession sessione) {
		return "admin/gen/list";
	}
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(String tableName) {
		Result r = Result.success();
		String sql="SELECT\r\n" + 
				"	table_name tableName,\r\n" + 
				"	ENGINE,\r\n" + 
				"	table_comment tableComment,\r\n" + 
				"	create_time createTime \r\n" + 
				"FROM\r\n" + 
				"	information_schema.TABLES \r\n" + 
				"WHERE 1=1 And\r\n" + 
				"	table_schema = ( SELECT DATABASE ( ) ) \r\n" + 
				"	AND table_type = 'base table'";
			if(StringUtils.isNotEmpty(tableName)) {
				sql+=" and table_name like  '%"+tableName+"%'";
			}
			sql+=" order by create_time desc;";
		List list=fwUserService.excuteFindBySql(sql);
		List<TablesVO> tablesVOList=new ArrayList<TablesVO>();
		for(int i=0;i<list.size();i++) {
			JSONArray jsonarry=(JSONArray) JSONArray.toJSON(list.get(i));
			TablesVO tablesVO=new TablesVO();
			tablesVO.setName(jsonarry.get(0).toString());
			tablesVO.setEngine(jsonarry.get(1).toString());
			tablesVO.setComment(jsonarry.get(2).toString());
			tablesVO.setCreateTime(jsonarry.get(3).toString());
			tablesVOList.add(tablesVO);
		}
		r.setAny("code", 0);
		r.setAny("msg", "");
		r.setAny("data", tablesVOList);
		 r.setAny("count",list.size());
		return r;

	}
	
	
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(String tables, HttpServletResponse response) throws IOException{
		byte[] data = generatorCode(tables.split(","));
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"hanyl.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
	
	
	
	
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = getTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = getColumn(tableName);
			//查询外键信息
			List<Map<String, String>> foreignkeys= getForeignKey( tableName);
			//参考表的信息
			List<Map<String, String>> foreignKeysBy= getForeignKeyBy( tableName);
			//生成代码
			GenUtils.generatorCode(fwUserService,table, columns, zip,foreignkeys,foreignKeysBy);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	* @Title: getTable  
	* @Description: TODO(获取表)  
	* @param @param name
	* @param @return    设定文件  
	* @return Map<String,String>    返回类型  
	* @throws
	 */
	public Map<String, String> getTable(String name) {
		String sql="SELECT\r\n" + 
				"	table_name tableName,\r\n" + 
				"	ENGINE,\r\n" + 
				"	table_comment tableComment,\r\n" + 
				"	create_time createTime \r\n" + 
				"FROM\r\n" + 
				"	information_schema.TABLES \r\n" + 
				"WHERE\r\n" + 
				"	table_schema = ( SELECT DATABASE ( ) ) \r\n" + 
				"	AND table_name = '"+name+"'";
		List list=fwUserService.excuteFindBySql(sql);
		Map<String, String> tableMap = new HashMap<String, String>();
		JSONArray jsonarry=(JSONArray) JSONArray.toJSON(list.get(0));
		tableMap.put("tableName", jsonarry.get(0).toString());
		tableMap.put("ENGINE", jsonarry.get(1).toString());
		tableMap.put("tableComment", jsonarry.get(2).toString());
		tableMap.put("createTime", jsonarry.get(3).toString());
		return tableMap;
	}
	
	/**
	 * 
	* @Title: getColumn  
	* @Description: TODO(根据表名获取字段名)  
	* @param @param name
	* @param @return    设定文件  
	* @return List<Map<String,String>>    返回类型  
	* @throws
	 */
	public List<Map<String, String>> getColumn(String name){
	
		String sql="SELECT\r\n" + 
				"	column_name columnName,\r\n" + 
				"	data_type dataType,\r\n" + 
				"	column_comment columnComment,\r\n" + 
				"	column_key columnKey,\r\n" + 
				"	extra \r\n" + 
				"FROM\r\n" + 
				"	information_schema.COLUMNS \r\n" + 
				"WHERE\r\n" + 
				"	table_name = '"+name+"' and table_schema = (select database()) order by ordinal_position";
		
		List list=fwUserService.excuteFindBySql(sql);
		List<Map<String, String>> columnsMapList  = new ArrayList<Map<String,String>>() ;
		for(int i=0;i<list.size();i++) {
			Map<String, String> columnMap = new HashMap<String, String>();
			JSONArray jsonarry=(JSONArray) JSONArray.toJSON(list.get(i));
			columnMap.put("columnName", jsonarry.get(0).toString());
			columnMap.put("dataType", jsonarry.get(1).toString());
			columnMap.put("columnComment",jsonarry.get(2).toString());
			columnMap.put("columnKey", jsonarry.get(3).toString());
			columnMap.put("extra", jsonarry.get(4).toString());
			columnsMapList.add(columnMap);
		}
		return columnsMapList;
	}
	/**
	 * 
	* @Title: getForeignKey  
	* @Description: TODO(获取表的所有外键信息)  
	* @param @param name
	* @param @return    设定文件  
	* @return List<Map<String,String>>    返回类型  
	* @throws
	 */
	public List<Map<String, String>> getForeignKey(String name){
		
		String sql="SELECT  i.CONSTRAINT_NAME as constraint_name,k.COLUMN_NAME as clolumn_name, k.REFERENCED_TABLE_NAME as ref_table, k.REFERENCED_COLUMN_NAME \r\n" + 
				"FROM information_schema.TABLE_CONSTRAINTS i \r\n" + 
				"LEFT JOIN information_schema.KEY_COLUMN_USAGE k ON i.CONSTRAINT_NAME = k.CONSTRAINT_NAME \r\n" + 
				"WHERE i.CONSTRAINT_TYPE = 'FOREIGN KEY' \r\n" + 
				"AND i.TABLE_SCHEMA = DATABASE()\r\n" + 
				"AND i.TABLE_NAME = '"+name+"';";
		
		List list=fwUserService.excuteFindBySql(sql);
		List<Map<String, String>> foreignKeyList  = new ArrayList<Map<String,String>>() ;
		for(int i=0;i<list.size();i++) {
			Map<String, String> foreignKeyMap = new HashMap<String, String>();
			JSONArray jsonarry=(JSONArray) JSONArray.toJSON(list.get(i));
			//外键名称
			foreignKeyMap.put("constraint_name", jsonarry.get(0).toString());
			//外键列名
			foreignKeyMap.put("clolumn_name", jsonarry.get(1).toString());
			//链接表名
			foreignKeyMap.put("ref_table",jsonarry.get(2).toString());
			//链接列名
			foreignKeyMap.put("ref_clolumn", jsonarry.get(3).toString());
			foreignKeyList.add(foreignKeyMap);
		}
		return foreignKeyList;
	}
	/**
	 * 
	 * @Title: getForeignKey  
	 * @Description: TODO(查询被参考的外键)  
	 * @param @param name
	 * @param @return    设定文件  
	 * @return List<Map<String,String>>    返回类型  
	 * @throws
	 */
	public List<Map<String, String>> getForeignKeyBy(String name){
		
		String sql="SELECT\r\n" + 
				"	TABLE_NAME ,COLUMN_NAME,REFERENCED_COLUMN_NAME\r\n" + 
				"FROM\r\n" + 
				"	INFORMATION_SCHEMA.KEY_COLUMN_USAGE \r\n" + 
				"WHERE\r\n" + 
				"	CONSTRAINT_SCHEMA = DATABASE ( ) \r\n" + 
				"	AND REFERENCED_TABLE_NAME = '"+name+"';";
		
		List list=fwUserService.excuteFindBySql(sql);
		List<Map<String, String>> foreignKeyList  = new ArrayList<Map<String,String>>() ;
		for(int i=0;i<list.size();i++) {
			Map<String, String> foreignKeyMap = new HashMap<String, String>();
			JSONArray jsonarry=(JSONArray) JSONArray.toJSON(list.get(i));
			//参考表名
			foreignKeyMap.put("table_name", jsonarry.get(0).toString());
			//参考表字段名
			foreignKeyMap.put("clolumn_name", jsonarry.get(1).toString());
			//被参考的字段名
			foreignKeyMap.put("ref_clolumn",jsonarry.get(2).toString());
			foreignKeyList.add(foreignKeyMap);
		}
		return foreignKeyList;
	}
	
}
