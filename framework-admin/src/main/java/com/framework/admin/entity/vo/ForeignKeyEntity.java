package com.framework.admin.entity.vo;

public class ForeignKeyEntity {

	//表名
	private String tableName;
	//字段名
	private String clolumnName;
	//链接字段名
	private String refClolumn;
	
	//类名(第一个字母大写)，如：sys_user => SysUser
	private String className;
	//类名(第一个字母小写)，如：sys_user => sysUser
	private String classname;
	
    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    //备注
    private String comment;
    //是否有主键
    private boolean havePri;
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClolumnName() {
		return clolumnName;
	}

	public void setClolumnName(String clolumnName) {
		this.clolumnName = clolumnName;
	}

	public String getRefClolumn() {
		return refClolumn;
	}

	public void setRefClolumn(String refClolumn) {
		this.refClolumn = refClolumn;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isHavePri() {
		return havePri;
	}

	public void setHavePri(boolean havePri) {
		this.havePri = havePri;
	}
	
	
}
