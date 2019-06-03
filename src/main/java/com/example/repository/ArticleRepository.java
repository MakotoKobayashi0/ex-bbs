package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事内容の操作をするリポジトリ
 * 
 * @author Makoto
 *
 */
/**
 * @author Makoto
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
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
		String sql = "select id, name, content " + "from articles order by id desc";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 指定されたidの記事を削除する.
	 * 
	 * @param id 記事のid
	 */
	public void delete(Integer id) {
		String sql = "delete from articles where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
