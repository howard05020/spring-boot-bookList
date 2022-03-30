package com.howard.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2")
public class HelloController {

	@GetMapping("/say")
	public String hello() {
		return "Hello Spring Boot";
	}

	@GetMapping("/books")
	public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
		Map<String, Object> book = new HashMap<>();
		book.put("name", "哈利波特");
		book.put("isbn", "88852877");
		book.put("author", "J.K.羅琳");
		Map<String, Object> book2 = new HashMap<>();
		book2.put("name", "危險心靈");
		book2.put("isbn", "87654321");
		book2.put("author", "侯文詠");

		List<Map<String, Object>> contents = new ArrayList<>();
		contents.add(book);
		contents.add(book2);

		Map<String, Object> pagemap = new HashMap<>();
		pagemap.put("page", page);
		pagemap.put("size", size);
		pagemap.put("contents", contents);

		return pagemap;
	}

	/* 限制參數輸入值 : {參數名:正則表達式} */
	@GetMapping("/books/{id:[1-9]}") // id可輸入1~9
	public Object getOne(@PathVariable("id") long bid) {

		return null;
	}

	@PostMapping("/books")
	/* RequestParam和PathVariable後面可加可不加參數名, 若不加時參數名稱需與前端相同 */
	public Object post(@RequestParam("name") String name, @RequestParam String author, @RequestParam String isbn) {
		Map<String, Object> book = new HashMap<>();
		book.put("name", name);
		book.put("author", author);
		book.put("isbn", isbn);

		return book;
	}
}