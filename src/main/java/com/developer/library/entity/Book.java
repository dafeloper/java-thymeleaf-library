package com.developer.library.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Book implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(unique = true)
	private Long isbn;
	private String title;
	private Integer year;
	private Integer numOfCopies;
	private Integer loanedNumCopies;
	private Integer availableNumCopies;
	private Boolean subscribed;
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Author author;
	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Publisher publisher;

	public Book() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNumOfCopies() {
		return numOfCopies;
	}

	public void setNumOfCopies(Integer numOfCopies) {
		this.numOfCopies = numOfCopies;
	}

	public Integer getLoanedNumCopies() {
		return loanedNumCopies;
	}

	public void setLoanedNumCopies(Integer loanedNumCopies) {
		this.loanedNumCopies = loanedNumCopies;
	}

	public Integer getAvailableNumCopies() {
		return availableNumCopies;
	}

	public void setAvailableNumCopies(Integer availableNumCopies) {
		this.availableNumCopies = availableNumCopies;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
