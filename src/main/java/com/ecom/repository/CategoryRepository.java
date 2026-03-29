package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findByParentId(Long parentId, Pageable pageable);

	@Query("""
			   SELECT c FROM Category c
			   WHERE
			       (
			           LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			           OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       )
			       AND (:status IS NULL OR c.status = :status)
			""")
	Page<Category> searchCategories(@Param("keyword") String keyword, @Param("status") Boolean status,
			Pageable pageable);

	Optional<Category> findByIdAndStatusTrue(Long id);

	Page<Category> findByStatusTrue(Pageable pageable);

	Page<Category> findByStatusFalse(Pageable pageable);

	Page<Category> findByParentIdAndStatusTrue(Long parentId, Pageable pageable);

}
