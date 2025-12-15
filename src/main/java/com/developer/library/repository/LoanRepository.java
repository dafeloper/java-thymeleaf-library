package com.developer.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.library.entity.Book;
import com.developer.library.entity.Client;
import com.developer.library.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

	@Query("SELECT c FROM Loan c WHERE c.client = :client")
	public List<Loan> findByClient(@Param("client") Client client);

	@Query("SELECT c FROM Loan c WHERE c.book = :book")
	public List<Loan> findByBook(@Param("book") Book book);

	@Query("SELECT c FROM Loan c WHERE c.up = :up")
	public List<Loan> findByUp(@Param("up") boolean up);

}
