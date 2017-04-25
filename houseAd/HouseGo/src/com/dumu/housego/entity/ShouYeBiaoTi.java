package com.dumu.housego.entity;

public class ShouYeBiaoTi {
	private String varname;
	private String value;
	public ShouYeBiaoTi(String varname, String value) {
		super();
		this.varname = varname;
		this.value = value;
	}
	public ShouYeBiaoTi() {
		super();
	}
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ShouYeBiaoTi [varname=" + varname + ", value=" + value + "]";
	}
	
	
	
	
}
