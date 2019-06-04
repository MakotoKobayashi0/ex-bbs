package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticlePostForm;
import com.example.form.CommentPostForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事を操作するコントローラ.
 * 
 * @author Makoto
 *
 */
@Controller
@RequestMapping("/article-list")
@Transactional // リポジトリを呼ぶクラスの上につける 4冊目167ページ
public class DesplayArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 記事のフォーム
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public ArticlePostForm setUpArticlePostForm() {
		return new ArticlePostForm();
	}
	
	@ModelAttribute
	public CommentPostForm setUpCommentPostForm() {
		return new CommentPostForm();
	}

	/**
	 * 最初に実行する.
	 * 
	 * @return トップページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("articles", articleRepository.findAll());

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
//		if (form.getName() == null) {;
//			result.rejectValue("name", null, "名前を入力してください");
//			return "redirect:/article-list";
//		} else if (form.getContent() == null) {
//			result.rejectValue("content", null, "内容を入力してください");
//			return "redirect:/article-list";
//		} else {
			Article article = new Article();
			BeanUtils.copyProperties(form, article);
			articleRepository.insert(article.getName(), article.getContent());
	
			return "redirect:/article-list";
		//}
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事のid
	 * @return トップページ
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		commentRepository.deleteByArticleId(id);
		
		articleRepository.delete(id);
		return "redirect:/article-list";
	}
	
	/**
	 * コメントを削除する.
	 * 
	 * @param id コメントのid
	 * @return トップページ
	 */
	@RequestMapping("/delete-comment")
	public String deleteComment(Integer id) {
		commentRepository.delete(id);

		return "redirect:/article-list";
	}
	
	/**
	 * コメントを投稿する.
	 * 
	 * @param form コメントを受け取るフォーム
	 * @return トップページ
	 */
	@RequestMapping("/post-comment")
	public String postCommnet(CommentPostForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		commentRepository.insert(comment);
		return "redirect:/article-list"; 
	}
}
