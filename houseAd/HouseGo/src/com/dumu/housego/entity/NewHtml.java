package com.dumu.housego.entity;

import java.io.Serializable;

public class NewHtml  implements Serializable{
	private String  id;
	private String  content;
	private String  paginationtype;
	private String  maxcharperpage;
	private String  template;
	private String  paytype;
	private String  allow_comment;
	private String  relation;
	private String  fukuanfangshi;
	private String  huxingintro;
	public NewHtml(String id, String content, String paginationtype, String maxcharperpage, String template,
			String paytype, String allow_comment, String relation, String fukuanfangshi, String huxingintro) {
		super();
		this.id = id;
		this.content = content;
		this.paginationtype = paginationtype;
		this.maxcharperpage = maxcharperpage;
		this.template = template;
		this.paytype = paytype;
		this.allow_comment = allow_comment;
		this.relation = relation;
		this.fukuanfangshi = fukuanfangshi;
		this.huxingintro = huxingintro;
	}
	public NewHtml() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPaginationtype() {
		return paginationtype;
	}
	public void setPaginationtype(String paginationtype) {
		this.paginationtype = paginationtype;
	}
	public String getMaxcharperpage() {
		return maxcharperpage;
	}
	public void setMaxcharperpage(String maxcharperpage) {
		this.maxcharperpage = maxcharperpage;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getAllow_comment() {
		return allow_comment;
	}
	public void setAllow_comment(String allow_comment) {
		this.allow_comment = allow_comment;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getFukuanfangshi() {
		return fukuanfangshi;
	}
	public void setFukuanfangshi(String fukuanfangshi) {
		this.fukuanfangshi = fukuanfangshi;
	}
	public String getHuxingintro() {
		return huxingintro;
	}
	public void setHuxingintro(String huxingintro) {
		this.huxingintro = huxingintro;
	}
	@Override
	public String toString() {
		return "NewHtml [id=" + id + ", content=" + content + ", paginationtype=" + paginationtype + ", maxcharperpage="
				+ maxcharperpage + ", template=" + template + ", paytype=" + paytype + ", allow_comment="
				+ allow_comment + ", relation=" + relation + ", fukuanfangshi=" + fukuanfangshi + ", huxingintro="
				+ huxingintro + "]";
	}
	
	
	
	
	
}
