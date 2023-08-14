package com.project.ecommerc.mart247.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(value = "select * from product where deleted_at IS NULL", nativeQuery = true)
	Page<ProductEntity> findByDeletedAtIsNull(Pageable pageable);

	@Query(value = "select * from product where deleted_at IS NULL", nativeQuery = true)
	List<ProductEntity> findByDeletedAtIsNull();

	ProductEntity findByName(String name);

	Optional<ProductEntity> findById(Long id);

	@Query(value = "update product set product.deleted_at = now() where product.id = :id", nativeQuery = true)
	@Modifying
	void deleteProductById(@Param("id") Long id);

	Page<ProductEntity> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String name, Pageable pageable);

	@Query("SELECT p FROM ProductEntity p WHERE p.category.id = :categoryId and p.deletedAt is NULL")
	Page<ProductEntity> findProductByCategory(@Param("categoryId") long categoryId, Pageable pageable);

}
