package com.howard.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.howard.domain.Book;
import com.howard.domain.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	/* 查詢所有書單列表 */
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	/* 新增或更新一個書單 */
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	/* 查詢一個指定書單 */
	public Book findOne(long id) {
		return bookRepository.findById(id).get();
	}

	/* 刪除一個指定書單 */
	public void delete(long id) {
		bookRepository.deleteById(id);
	}

	/* 根據作者名字找出書單列表 */
	public List<Book> findByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	/* 根據作者名字&狀態找出書單列表 */
	public List<Book> findByAuthorAndStatus(String author, int status) {
		return bookRepository.findByAuthorAndStatus(author, status);
	}

	/* 查詢描述包含字串的書單列表 */
	public List<Book> findByDescriptionContains(String description) {
		return bookRepository.findByDescriptionContains(description);
	}

	/* 自定義查詢 */
	public List<Book> findByJPQL(int len) {
		return bookRepository.findByJPQL(len);
	}

	/* 更新指令書單的狀態 */
	@Transactional
	public int updateByJPQL(int status, long id) {
		return bookRepository.updateByJPQL(status, id);
	}

	@Transactional
	public int deleteByJPQL(long id) {
		return bookRepository.deleteByJPQL(id);
	}

	/* 測試事務操作方法 */
	@Transactional
	public int deleteAndUpdate(long id, int status, long uid) {
		int dcount = bookRepository.deleteByJPQL(id);
		int ucount = bookRepository.updateByJPQL(status, uid);
		return dcount + ucount;
	}

	/* 用JPA進行分頁查詢 */
	public Page<Book> findAllByPage(Pageable pageable) {
		// Pageable pageable = PageRequest.of(0, 5, Sort.by(Order.desc("id")));
		return bookRepository.findAll(pageable);
	}
}