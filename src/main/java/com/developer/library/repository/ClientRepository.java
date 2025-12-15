package com.developer.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.library.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	@Query("SELECT c FROM Client c WHERE c.dni = :dni")
	public Client findByDni(@Param("dni") Long dni);

	@Query("SELECT c FROM Client c WHERE c.firstName = :firstName")
	public List<Client> findByFistName(@Param("firstName") String firstName);

	@Query("SELECT c FROM Client c WHERE c.lastName = :lastName")
	public List<Client> findByLastName(@Param("lastName") String lastName);

}
