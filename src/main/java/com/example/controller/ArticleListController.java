package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticlePostForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を操作するコントローラ.
 * 
 * @author Makoto
 *
 */
@Controller
@RequestMapping("/article-list")
public class ArticleListController {

	@Autowired
	ArticleRepository articleRepository;

	/**
	 * 記事のフォーム
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public ArticlePostForm setUpForm() {
		return new ArticlePostForm();
	}

	@Autowired
	private HttpSession session;

	/**
	 * 最初に実行する.
	 * 
	 * @return トップページ
	 */
	@RequestMapping("")
	public String index() {
		session.setAttribute("articles", articleRepository.findAll());

		return "article-list";
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param form 記事のフォーム
 	 * @return トップページ
	 */
	@RequestMapping("/post")
	public String post(ArticlePostForm form) {
		articleRepository.insert(form.getName(), form.getContent());

		return "forward:/article-list";
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事のid
	 * @return トップページ
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		articleRepository.delete(id);

		return "forward:/article-list";
	}
}
