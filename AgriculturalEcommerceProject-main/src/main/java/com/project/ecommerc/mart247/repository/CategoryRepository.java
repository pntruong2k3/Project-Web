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

import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);

    Optional<CategoryEntity> findById(Long id);
//	test

    @Query(value = "with recursive tempcompany (ID,`NAME`,`parent_Id`,`IMG`,`created_at`,`deleted_at`,`updated_at`) as (\r\n"
            + "select category.`id` AS ID,`category`.`name` AS NAME,`category`.`id` AS parent_Id,`category`.`img`,`category`.`created_at`,`category`.`updated_at`,`category`.`deleted_at` AS IMG from category\r\n"
            + " where (category.`parent_id` = 0) \r\n"
            + " union all select child.`id` AS ID,concat('----',`child`.`name`) AS ENAME,`parent`.`parent_Id` AS parent_Id,`child`.`img`,`child`.`created_at`,`child`.`updated_at`,`child`.`deleted_at` AS img from (tempcompany\r\n"
            + "parent \r\n"
            + " join category\r\n"
            + "child on((parent.`ID` = child.`parent_id`))))\r\n"
            + " select tempcompany.`ID` AS ID,`tempcompany`.`NAME` AS NAME,`tempcompany`.`parent_Id` AS parent_Id,`tempcompany`.`IMG` AS IMG,`tempcompany`.`created_at` AS created_at,`tempcompany`.`updated_at` AS updated_at,`tempcompany`.`deleted_at` AS deleted_at from tempcompany order by tempcompany.`parent_Id`", nativeQuery = true)
    List<CategoryEntity> getCategory();

    @Query(value = "select * from category where parent_id = 0", nativeQuery = true)
    List<CategoryEntity> getCatebyId();
    /* Page<CategoryEntity> findAllPage(Pageable pageable); */

    @Query(value = "update category left join product on category.id = product.category_id set category.deleted_at = now(),product.deleted_at = now() where category.id = :id", nativeQuery = true)
    @Modifying
    void deleteCategory(@Param("id") Long id);

    Page<CategoryEntity> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String name, Pageable pageable);

    Page<CategoryEntity> findByDeletedAtIsNull(Pageable pageable);

    List<CategoryEntity> findByDeletedAtIsNull();

    @Query(value = "update category set category.deleted_at = now() where category.id = :id", nativeQuery = true)
    @Modifying
    void deleteCategoryById(@Param("id") Long id);

    List<CategoryEntity> findByParentId(Long parentId);


}
