package com.example.domain;

import java.util.List;

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
	/** 記事に投稿されたコメント */
	private List<Comment> comments;

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", comments=" + comments + "]";
	}

}
