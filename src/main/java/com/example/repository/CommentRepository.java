package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントを操作するリポジトリ.
 * 
 * @author Makoto
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * コメントを投稿する.
	 * 
	 * @param name      投稿者名
	 * @param content   コメント内容
	 * @param articleId どの記事にコメントしたかを示すid
	 */
	// commentをオブジェクトで受け取る
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments(name, content, article_id) " + "VALUES(:name, :content, :article_id)";

		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(comment);

		template.update(sql, paramMap);
	}
	
	/**
	 * コメントを削除する. 
	 * 
	 * @param id コメントのid
	 */
	public void delete(Integer id) {
		String sql = "delete from comments where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	public void deleteByArticleId(Integer articleId) {
		String sql = "delete from comments where article_id=:article_id";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("article_id", articleId);
		template.update(sql, paramMap);
	}
}
