package com.developer.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.library.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {

	@Query("SELECT c FROM Publisher c WHERE c.name = :name")
	public Publisher findByName(@Param("name") String name);
}
