package com.developer.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.developer.library.entity.Author;
import com.developer.library.entity.Book;
import com.developer.library.entity.Publisher;
import com.developer.library.service.AuthorService;
import com.developer.library.service.BookService;
import com.developer.library.service.PublisherService;
import com.developer.library.service.exception.ServiceException;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PublisherService publisherService;
	
	@PostMapping("/save/{id}")
	public String save(Model model, @PathVariable String id,
			@RequestParam Long isbn,
			@RequestParam String title,
			@RequestParam Integer year,
			@RequestParam Integer numOfCopies,
			//@RequestParam Boolean subs,
			@RequestParam String author_id,
			@RequestParam String publisher_id) {
		Boolean subs = false;
		
		if (id != null && !id.equals("0") ) {

			try {
				bookService.edit(id, isbn, title, year, numOfCopies, subs, author_id, publisher_id);
				
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
			
		} else {
			try {
				bookService.add(isbn, title, year, numOfCopies, subs, author_id, publisher_id);
				
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
		}
		
		List<Book> bookList = bookService.getAll();
		List<Author> authors = authorService.listAll();
		List<Publisher> publishers = publisherService.listAll();
		model.addAttribute("authors", authors);
		model.addAttribute("publishers", publishers);
		model.addAttribute("activePage", "bookList");
		model.addAttribute("books", bookList);
		return "bookList.html";
	}
	
	@GetMapping("/listAll")
	public String listAllBooks(Model model) {
		
		List<Book> bookList = bookService.getAll();
		List<Author> authors = authorService.listAll();
		List<Publisher> publishers = publisherService.listAll();
		model.addAttribute("authors", authors);
		model.addAttribute("publishers", publishers);
		model.addAttribute("books", bookList);
		model.addAttribute("activePage", "bookList");
		return "bookList.html";
	}
	
}
