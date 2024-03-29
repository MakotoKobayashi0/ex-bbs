package com.example.domain;

/**
 * コメントの情報を示すドメイン.
 * 
 * @author Makoto
 *
 */
public class Comment {

	/** id */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** コメント内容 */
	private String content;
	/** どの記事に投稿したかを示すid */
	private Integer articleId;

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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "\nComment [\n\tid=" + id + ", \n\tname=" + name + ", \n\tcontent=" + content + ", \n\tarticleId=" + articleId + "\n]";
	}

}
