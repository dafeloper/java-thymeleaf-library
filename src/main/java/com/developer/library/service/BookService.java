package com.developer.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.library.entity.Author;
import com.developer.library.entity.Book;
import com.developer.library.entity.Publisher;
import com.developer.library.repository.AuthorRepository;
import com.developer.library.repository.BookRepository;
import com.developer.library.repository.PublisherRepository;
import com.developer.library.service.exception.ServiceException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private PublisherRepository publisherRepo;
	
	@Transactional
	public void add(Long isbn,
			String title,
			Integer year, 
			Integer numOfCopies, 
			Boolean subscribed, 
			String author_id, 
			String publisher_id) throws ServiceException{
		
		validate(isbn, title, year, numOfCopies);
		
		Author author;
		Publisher publisher;
		
		Optional<Author> authorAns = authorRepo.findById(author_id);
		if (authorAns.isPresent()) {
			author = authorAns.get();
		} else {
			throw new ServiceException("The Author doesn't exist.");
		}
		
		Optional<Publisher> publisherAns = publisherRepo.findById(publisher_id);
		if (publisherAns.isPresent()) {
			publisher = publisherAns.get();
		} else {
			throw new ServiceException("The Publisher doesn't exist.");
		}
		
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setYear(year);
		book.setNumOfCopies(numOfCopies);
		book.setLoanedNumCopies(0);
		book.setAvailableNumCopies(numOfCopies);
		book.setSubscribed(subscribed); 
		book.setAuthor(author);
		book.setPublisher(publisher);
		
		bookRepo.save(book);
	}
	
	@Transactional
	public List<Book> getAll() {
		return bookRepo.findAll();
	}
	
	@Transactional
	public Book edit(String id,
			Long isbn,
			String title,
			Integer year,
			Integer numOfCopies,
			Boolean subscribed,
			String author_id,
			String publisher_id) throws ServiceException{
		
		return new Book();
	}
	
	public Book edit(String id, int loanedNum) throws ServiceException{
		Optional<Book> ans = bookRepo.findById(id);
		Book book;
		if (ans.isPresent()) {
			book = ans.get();
			int availableNumCopies = book.getAvailableNumCopies();
			
			if(availableNumCopies > 0) {
				book.setAvailableNumCopies(availableNumCopies - loanedNum);
				book.setLoanedNumCopies(book.getLoanedNumCopies() + loanedNum);
				return bookRepo.save(book);
			}
			
		} else {
			throw new ServiceException("The book doesn't exist.");
		}
		
		return book;
	}
	
	@Transactional
	public void destroyBook() {
		
	}
	
	private void validate (Long isbn, String title, Integer year, Integer numOfCopies) throws ServiceException {
		
		String matcher = "^[A-Za-z0-9.]*$";
		//String numMatcher = "^[0-9]*$";
		
		if (isbn.equals(null) && isbn < 10000000000L) {
			throw new ServiceException("The isbn can't be empty and must contain only numbers.");
		}
		
		if (title.equals(null) && title.isEmpty() && !title.matches(matcher)) {
			throw new ServiceException("The title of the Book can't be empty and can't have especial characters.");
		}
		
		if (year.equals(null) && year < 1000) {
			throw new ServiceException("The year can't be empty and must contain only numbers.");
		}
	}
}
