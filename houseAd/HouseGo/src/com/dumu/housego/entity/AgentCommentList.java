package com.dumu.housego.entity;

import java.io.Serializable;

public class AgentCommentList implements Serializable{
    private String id;
    private String comment_id;
    private String author_ip;
    private String date;
    private String approved;
    private String agent;
    private String parent;
    private String user_id;
    private String stb;
    private String author;
    private String content;
    private String userpic;
	public AgentCommentList(String id, String comment_id, String author_ip, String date, String approved, String agent,
			String parent, String user_id, String stb, String author, String content, String userpic) {
		super();
		this.id = id;
		this.comment_id = comment_id;
		this.author_ip = author_ip;
		this.date = date;
		this.approved = approved;
		this.agent = agent;
		this.parent = parent;
		this.user_id = user_id;
		this.stb = stb;
		this.author = author;
		this.content = content;
		this.userpic = userpic;
	}
	public AgentCommentList() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getAuthor_ip() {
		return author_ip;
	}
	public void setAuthor_ip(String author_ip) {
		this.author_ip = author_ip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getStb() {
		return stb;
	}
	public void setStb(String stb) {
		this.stb = stb;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	@Override
	public String toString() {
		return "AgentCommentList [id=" + id + ", comment_id=" + comment_id + ", author_ip=" + author_ip + ", date="
				+ date + ", approved=" + approved + ", agent=" + agent + ", parent=" + parent + ", user_id=" + user_id
				+ ", stb=" + stb + ", author=" + author + ", content=" + content + ", userpic=" + userpic + "]";
	}
    
    
    
}
