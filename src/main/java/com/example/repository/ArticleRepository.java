package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事内容の操作をするリポジトリ.
 * 
 * @author Makoto
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articles = new ArrayList<>();
		List<Comment> comments = null;
		Article article = null;
		int beforeId = 0;
		while (rs.next()) {
			int nowId = rs.getInt("a.id");
			if (beforeId != nowId) {
				article = new Article();
				article.setId(rs.getInt("a.id"));
				article.setName(rs.getString("a.name"));
				article.setContent(rs.getString("a.content"));
				comments = new ArrayList<>();
				article.setComments(comments);
				articles.add(article);
			}
			if (rs.getInt("c.id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setName(rs.getString("c.name"));
				comment.setContent(rs.getString("c.content"));
				comment.setArticleId(rs.getInt("c.article_id"));
				comments.add(comment);
			}
			
			beforeId = nowId;
		}
		return articles;
	};

	/**
	 * 記事を追加する.
	 * 
	 * @param name    投稿者名
	 * @param content 記事内容
	 */
	public void insert(String name, String content) {
		String sql = "INSERT INTO articles(name, content) VALUES(:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("content", content);

		template.update(sql, param);
	}

	/**
	 * 記事を全検検索する.
	 * 
	 * @return 記事のリスト
	 */
	public List<Article> findAll() {
		String sql = "select a.id as \"a.id\", a.name as \"a.name\", a.content as \"a.content\", \n" + 
				"c.id as \"c.id\", c.name as \"c.name\", c.content as \"c.content\", c.article_id as \"c.article_id\"\n" + 
				"from articles a left outer join comments c on a.id=c.article_id \n" + 
				"order by a.id desc;";

		List<Article> articleList = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
		System.out.println(articleList);
		return articleList;
	}

	/**
	 * 指定されたidの記事を削除する.
	 * 
	 * @param id 記事のid
	 */
	public Integer delete(Integer id) {
		String sql = "delete from articles where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
		return id;
	}
}
