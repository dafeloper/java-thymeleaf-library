package com.developer.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.library.entity.Author;
import com.developer.library.entity.Book;
import com.developer.library.entity.Publisher;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	@Query("SELECT c FROM Book c WHERE c.isbn = :isbn")
	public Book findByIsbn(@Param("isbn") Long isbn);

	@Query("SELECT c FROM Book c WHERE c.title = :title")
	public List<Book> findByTitle(@Param("title") String title);

	@Query("SELECT c FROM Book c WHERE c.year = :year")
	public List<Book> findByYear(@Param("year") Integer year);

	@Query("SELECT c FROM Book c WHERE c.author = :author")
	public List<Book> findByAuthor(@Param("author") Author author);

	@Query("SELECT c FROM Book c WHERE c.publisher = :publisher")
	public List<Book> findByPublisher(@Param("publisher") Publisher publisher);

}
