package com.framework.admin.util;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.alibaba.fastjson.JSON;
import com.framework.admin.entity.vo.ColumnEntity;
import com.framework.admin.entity.vo.ForeignKeyEntity;
import com.framework.admin.entity.vo.TableEntity;
import com.framework.admin.service.FwUserService;
import com.framework.common.exception.GlobalException;
import com.framework.common.util.result.CodeMsg;
/**
 * 代码生成器   工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

    public static List<String> getTemplates(){
        List<String> templates = new ArrayList<String>();
        templates.add("velocity/Entity.java.vm");
        templates.add("velocity/Dao.java.vm");
//        templates.add("velocity/Dao.xml.vm");
        templates.add("velocity/Service.java.vm");
        templates.add("velocity/ServiceImpl.java.vm");
        templates.add("velocity/Controller.java.vm");
        templates.add("velocity/menu.sql.vm");
        templates.add("velocity/Repository.java.vm");
        templates.add("velocity/RepositoryImpl.java.vm");
        templates.add("velocity/list.html.vm");
        templates.add("velocity/add.html.vm");
        templates.add("velocity/edit.html.vm");
        templates.add("velocity/list.js.vm");
        templates.add("velocity/edit.js.vm");
        templates.add("velocity/add.js.vm");

        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(FwUserService fwUserService,Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip,List<Map<String, String>> foreignkeys,List<Map<String, String>> foreignkeysBy) {
    	
    	
    	//配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName" ));
        tableEntity.setComments(table.get("tableComment" ));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix" ));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        //所有外键信息
        Map<String, String> foreignkeyMap = new HashMap<String, String>();
        List<ForeignKeyEntity> foreignKeysList = new ArrayList<ForeignKeyEntity>();
        //遍历这个map放到上面是为了下面排除为外键的字段
        for(Map<String, String> foreignKey : foreignkeys){
        	ForeignKeyEntity foreignKeyEntity=new ForeignKeyEntity();
        	foreignKeyEntity.setTableName(foreignKey.get("ref_table"));
        	foreignKeyEntity.setClolumnName(foreignKey.get("clolumn_name"));
        	foreignKeyEntity.setRefClolumn(foreignKey.get("ref_clolumn"));
            //表名转换成Java类名
            String cname = tableToJava(foreignKeyEntity.getTableName(), config.getString("tablePrefix" ));
            foreignKeyEntity.setClassName(cname);
            foreignKeyEntity.setClassname(StringUtils.uncapitalize(cname));
            
            //列名转换成Java属性名
            String attrName = columnToJava(foreignKeyEntity.getClolumnName());
            foreignKeyEntity.setAttrName(attrName);
            foreignKeyEntity.setAttrname(StringUtils.uncapitalize(attrName));
            foreignKeyEntity.setHavePri( Boolean.valueOf(getPRI( foreignKey.get("ref_table"), fwUserService).get("havaPRI").toString()));
            foreignKeyEntity.setComment(getPRI( foreignKey.get("ref_table"), fwUserService).get("commen").toString());
        	foreignkeyMap.put(foreignKey.get("clolumn_name"), foreignKey.get("clolumn_name"));
        	foreignKeysList.add(foreignKeyEntity);
        }
        tableEntity.setForeignKeys(foreignKeysList);
        
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        System.out.println(JSON.toJSON(columns));
        for(Map<String, String> column : columns){
            ColumnEntity columnEntity = new ColumnEntity();
            String c=column.get("columnName" );
            //判断字段为外键则直接下一个
            if(null!=foreignkeyMap.get(c)) {
            	continue;
            }
            columnEntity.setColumnName(column.get("columnName" ));
            columnEntity.setDataType(column.get("dataType" ));
            columnEntity.setComments(column.get("columnComment" ));
            columnEntity.setExtra(column.get("extra" ));
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType" );
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);


        
        
        List<ForeignKeyEntity> foreignKesByList = new ArrayList<ForeignKeyEntity>();
        for(Map<String, String> foreignKey : foreignkeysBy){
        	ForeignKeyEntity foreignKeyEntity=new ForeignKeyEntity();
        	foreignKeyEntity.setTableName(foreignKey.get("table_name"));
        	foreignKeyEntity.setClolumnName(foreignKey.get("clolumn_name"));
        	foreignKeyEntity.setRefClolumn(foreignKey.get("ref_clolumn"));
            //表名转换成Java类名
            String cname = tableToJava(foreignKeyEntity.getTableName(), config.getString("tablePrefix" ));
            foreignKeyEntity.setClassName(cname);
            foreignKeyEntity.setClassname(StringUtils.uncapitalize(cname));
            
            //列名转换成Java属性名
            String attrName = columnToJava(foreignKeyEntity.getClolumnName());
            foreignKeyEntity.setAttrName(attrName);
            foreignKeyEntity.setAttrname(StringUtils.uncapitalize(attrName));
            foreignKeyEntity.setHavePri( Boolean.valueOf(getPRI( foreignKey.get("table_name"), fwUserService).get("havaPRI").toString()));
            foreignKeyEntity.setComment(getPRI( foreignKey.get("table_name"), fwUserService).get("commen").toString());
        	foreignKesByList.add(foreignKeyEntity);
        }
        tableEntity.setForeignKeyBy(foreignKesByList);
        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
//            tableEntity.setPk(tableEntity.getColumns().get(0));
        	return;
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        Velocity.init(prop);
        String mainPath = config.getString("mainPath" );
//        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package" ));
        map.put("moduleName", config.getString("moduleName" ));
        map.put("author", config.getString("author" ));
        map.put("email", config.getString("email" ));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("foreignkeys", tableEntity.getForeignKeys());
        map.put("foreignkeysBy", tableEntity.getForeignKeyBy());
        map.put("oneToMayMsg", "此OneToMany设置在删除主表时,子表对应外键设置为null,且数据库中对应外键配置成删除时 Set NUll");
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8" );
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassname(),  tableEntity.getClassName(), config.getString("package" ), config.getString("moduleName" ))));
                IOUtils.write(sw.toString(), zip, "UTF-8" );
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new GlobalException(CodeMsg.error("获取模板失败："+tableEntity.getTableName()));
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
//        if (StringUtils.isNotBlank(tablePrefix)) {
//            tableName = tableName.replaceFirst(tablePrefix, "" );
//        }
    	tableName.replaceFirst("_", "");
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
//            throw new RRException("获取配置文件失败，", e);
            throw new GlobalException(CodeMsg.error("获取配置文件失败"));
        }
    }
	private static Map<String,Object>  getPRI(String tableName,FwUserService fwUserService) {
		boolean boo=false;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String sql="SELECT column_name FROM INFORMATION_SCHEMA.`KEY_COLUMN_USAGE` WHERE table_name='"+tableName+"' AND constraint_name='PRIMARY'";
		List list=fwUserService.excuteFindBySql(sql);
		System.out.println("查询是否有主键："+list.size());
		if(list.size()>0) {
			boo= true;
		}
		resultMap.put("havaPRI",boo);
		String sql2="SELECT\n" + 
				"	table_comment \n" + 
				"FROM\n" + 
				"	information_schema.TABLES \n" + 
				"WHERE\n" + 
				"	table_schema = ( SELECT DATABASE ( ) ) \n" + 
				"	AND table_name = '"+tableName+"'";
		System.out.println(sql2);
		List list2=fwUserService.excuteFindBySql(sql2);
		if(list2.size()>0) {
			String comment=list2.get(0).toString();
			resultMap.put("commen", comment);
		}else {
			resultMap.put("commen", "");
		}
		
		return resultMap;
	}
    /**
     * 获取文件名
     */
    public static String getFileName(String template,String classname, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm" )) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }
        if (template.contains("Repository.java.vm" )) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }
        if (template.contains("RepositoryImpl.java.vm" )) {
            return packagePath + "repository" + File.separator + "impl" + File.separator + className + "RepositoryImpl.java";
        }
        if (template.contains("menu.sql.vm" )) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("list.html.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + "admin" + File.separator + classname +
                    File.separator +classname+ "List.html";
        }
        if (template.contains("add.html.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + "admin" + File.separator + classname +
        			File.separator +classname+ "Add.html";
        }
        if (template.contains("edit.html.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + "admin" + File.separator + classname +
        			File.separator +classname+ "Edit.html";
        }
        if (template.contains("list.js.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js"+File.separator + "admin" + File.separator + classname +
        			File.separator +classname+ "List.js";
        }
        if (template.contains("add.js.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "static"+ File.separator + "js" + File.separator + "admin" + File.separator + classname +
        			File.separator +classname+ "Add.js";
        }
        if (template.contains("edit.js.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js"+ File.separator + "admin" + File.separator + classname +
        			File.separator +classname+ "Edit.js";
        }
        return null;
    }
}
