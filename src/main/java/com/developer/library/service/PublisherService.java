package com.developer.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.library.entity.Publisher;
import com.developer.library.repository.PublisherRepository;
import com.developer.library.service.exception.ServiceException;

@Service
public class PublisherService {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Transactional
	public void add(String name) throws ServiceException{
		
		validate(name);
		
		Publisher publisher = new Publisher();
		publisher.setName(name);
		
		publisherRepository.save(publisher);
		
	}
	
	public void modify(String id, String name) throws ServiceException {
		
		if (name.equals(null) || name.isEmpty()) {
			throw new ServiceException("The name of the Publisher can't be null.");
		}
		
		Optional<Publisher> ans = publisherRepository.findById(id);
		
		if (ans.isPresent()) {
			Publisher publisher = ans.get();
			publisher.setName(name);
			
			publisherRepository.save(publisher);
		}
	}
	
	@Transactional
	public void destroy(String id) {
		
		Publisher publisher = publisherRepository.findById(id).get();
		publisherRepository.delete(publisher);
		
	}
	
	public List<Publisher> listAll() {
		return publisherRepository.findAll();
	}
	
	private void validate(String name) throws ServiceException {

		if (name.equals(null) || name.isEmpty()) {

			throw new ServiceException("The name of the Author can't be null.");
		}
	}
}
