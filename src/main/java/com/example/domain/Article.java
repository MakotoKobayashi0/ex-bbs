package com.example.domain;

/**
 * 記事の情報を表すドメイン.
 * 
 * @author Makoto
 *
 */
public class Article {

	/** id */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** 記事内容 */
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "articles [id=" + id + ", name=" + name + ", content=" + content + "]";
	}

}
