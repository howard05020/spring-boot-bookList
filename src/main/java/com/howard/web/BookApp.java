package com.howard.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.howard.domain.Book;
import com.howard.service.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookApp {

	@Autowired
	private BookService bookService;

	/* 獲取所有讀書清單列表 */
	@GetMapping("/books")
	public Page<Book> getAll(@PageableDefault(size = 5, sort = { "id" }, direction = Direction.DESC) Pageable pageable) {
		return bookService.findAllByPage(pageable);
	}

	/* 新增一個書單 */
	@PostMapping("/books")
	public Book post(Book book) {
		return bookService.save(book);
	}

	/* 查詢一個指定書單 */
	@GetMapping("/books/{id}")
	public Book getOne(@PathVariable long id) {
		return bookService.findOne(id);
	}

	/* 更新一個指定書單 */
	@PutMapping("/books")
	public Book update(@RequestParam long id, @RequestParam String name, @RequestParam String author,
			@RequestParam String description, @RequestParam int status) {
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setStatus(status);

		return bookService.save(book);
	}

	/* 刪除一個指定書單 */
	@DeleteMapping("/books/{id}")
	public void deleteOne(@PathVariable long id) {
		bookService.delete(id);
	}

	/* 自定義查詢 */
	@PostMapping("/books/by")
	public List<Book> findBy(@RequestParam int len) {
		return bookService.findByJPQL(len);
	}

	/* 更新指定書單的狀態 */
	@PostMapping("/books/updateStatus")
	public int updateByJPQL(@RequestParam int status, @RequestParam long id) {
		return bookService.updateByJPQL(status, id);
	}

	/* 刪除一個指定書單(自定義) */
	@PostMapping("/books/delete")
	public int deleteByJPQL(@RequestParam long id) {
		return bookService.deleteByJPQL(id);
	}

	@PostMapping("/books/deleteAndUpdate")
	public int deleteAndUpdate(@RequestParam long id, @RequestParam int status, @RequestParam long uid) {
		return bookService.deleteAndUpdate(id, status, uid);
	}
}