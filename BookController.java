package com.example.bookProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookProject.model.Book;

@RestController
public class BookController {
	
	private HashMap<Integer, Book> bookHashMap = new HashMap<Integer, Book>();
	
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@PostMapping("/insertBook")
	public ResponseEntity insertBook(@RequestBody Book book) {
		logger.info("Book Coming from Insertion : {}", book);
		if(bookHashMap.containsKey(book.getId())) {
			logger.error("Book Already present.");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		bookHashMap.put(book.getId(), book);
		return new ResponseEntity(HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book) {
		bookHashMap.put(book.getId(), book);
		return bookHashMap.get(book.getId());
		
	}
	
	@DeleteMapping("/deleteBody/{id}")
	public String deleteBookById(@PathVariable("id") int bookId) {
		bookHashMap.remove(bookId);
		return "Book deleted Successfully";
	}
	
	@GetMapping("/book/{bookId}")
	public Book getBookById(@PathVariable ("bookId") int bookId) {
		logger.info("bookId Received : {}", bookId);
		return bookHashMap.get(bookId);
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookHashMap.values().stream()
				.collect(Collectors.toList());
	}
	
	@PatchMapping("/updateBookDetails/{bookId}")
	public Book updateBookDetails(@RequestBody Book book) {
		bookHashMap.put(book.getId(), book);
		return bookHashMap.get(book.getId());
	}
	
}
