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
import com.developer.library.service.AuthorService;
import com.developer.library.service.exception.ServiceException;

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping("/save/{id}")
	public String createAuthor(Model model, @PathVariable("id") String id, @RequestParam String fname) {

		if (id != null && !id.equals("0") ) {

			try {
				authorService.update(id, fname);
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
			
		} else {
			try {
				authorService.add(fname);
				
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
		}
		

		model.addAttribute("authors", authorService.listAll());
		model.addAttribute("activePage", "authorList");
		return "authorList.html";

	}
	
	@GetMapping("/listAll")
	public String listAll(Model model) {
		
		List<Author> authors = authorService.listAll();
		model.addAttribute("authors", authors);
		model.addAttribute("activePage", "authorList");
		
		return "authorList.html";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam String id, Model model) throws ServiceException {
		authorService.delete(id);
		
		return "redirect:/author/listAll";
	}
}
