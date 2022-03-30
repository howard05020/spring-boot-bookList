package com.howard.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	Page<Book> findAll(Pageable pageable);
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByAuthorAndStatus(String author, int status);
	
	List<Book> findByDescriptionContains(String description);
	
	@Query("select b from Book b where length(b.name) > ?1")
	List<Book> findByJPQL(int len);
	
	@Transactional
	@Modifying
	@Query("update Book b set b.status = ?1 where id = ?2")
	int updateByJPQL(int status, long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from Book b where b.id = ?1", nativeQuery = true)
	int deleteByJPQL(long id);
}