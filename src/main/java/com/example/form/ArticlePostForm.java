package com.example.form;

/**
 * 記事の内容を受け取るフォーム
 * 
 * @author Makoto
 *
 */
public class ArticlePostForm {

	/** 名前 */
	private String name;
	/** 記事内容 */ 
	private String content;

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
		return "ArticlePostForm [name=" + name + ", content=" + content + "]";
	}

}
