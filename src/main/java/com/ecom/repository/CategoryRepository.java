package com.ecom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findByParentId(Long parentId, Pageable pageable);
	
//	Custom query for search:
	@Query("""
		       SELECT c FROM Category c
		       WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
		          OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
		       """)
	Page<Category> searchCategories(@Param("keyword") String keyword, Pageable pageable);
	
}
