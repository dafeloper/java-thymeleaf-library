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

import com.developer.library.entity.Publisher;
import com.developer.library.service.PublisherService;
import com.developer.library.service.exception.ServiceException;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	
	@Autowired
	PublisherService publisherService;
	
	@PostMapping("/save/{id}")
	public String save(Model model, @PathVariable String id, @RequestParam String name) {
		
		if (id != null && !id.equals("0")) {

			try {
				publisherService.modify(id, name);
				
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
			
		} else {
			try {
				publisherService.add(name);
				
			} catch (ServiceException ex) {
				model.addAttribute("error", ex.getMessage());
			}
		}
		

		model.addAttribute("publishers", publisherService.listAll());
		model.addAttribute("activePage", "publisherList");
		return "publisherList.html";

	}
	
	@GetMapping("/listAll")
	public String listAll(Model model) {
		
		List<Publisher> publishers = publisherService.listAll();
		model.addAttribute("publishers", publishers);
		model.addAttribute("activePage", "publisherList");
		
		return "publisherList.html";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam String id, Model model) throws ServiceException {
		publisherService.destroy(id);
		
		return "redirect:/publisher/listAll";
	}
}
