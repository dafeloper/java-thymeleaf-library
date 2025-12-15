package com.developer.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.library.entity.Author;
import com.developer.library.repository.AuthorRepository;
import com.developer.library.service.exception.ServiceException;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional
	public void add(String name) throws ServiceException {

		validate(name);

		Author author = new Author();
		author.setName(name);

		authorRepository.save(author);
	}

	public void update(String id, String name) throws ServiceException {

		validate(name);

		Optional<Author> ans = authorRepository.findById(id);

		if (ans.isPresent()) {

			Author author = ans.get();
			author.setName(name);
			authorRepository.save(author);
		} else {

			throw new ServiceException("No se encontro el Autor solicitado");
		}

	}

	@Transactional
	public void delete(String id) throws ServiceException {

		Optional<Author> ans = authorRepository.findById(id);
		if (ans.isPresent()) {

			Author author = ans.get();
			authorRepository.delete(author);
		} else {

			throw new ServiceException("No se encontro el Autor solicitado");
		}
	}

	private void validate(String name) throws ServiceException {

		if (name.equals(null) || name.isEmpty()) {

			throw new ServiceException("The name of the Author can't be null.");
		}
	}

	public List<Author> listAll() {
		return authorRepository.findAll();
	}
}
