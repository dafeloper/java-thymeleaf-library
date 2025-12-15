package com.developer.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

	@Query("SELECT c FROM Author c WHERE c.name = :name")
	public Optional<Author> findByName(@Param("name") String name);

}
