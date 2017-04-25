package com.dumu.housego.entity;

import java.io.Serializable;

public class NewHouseRecommendData implements Serializable {
	private String title;
	private String description;
	private String thumb;
	private String url;

	public NewHouseRecommendData(String title, String description, String thumb, String url) {
		super();
		this.title = title;
		this.description = description;
		this.thumb = thumb;
		this.url = url;
	}

	public NewHouseRecommendData() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NewHouseRecommendData [title=" + title + ", description=" + description + ", thumb=" + thumb + ", url="
				+ url + "]";
	}

}
