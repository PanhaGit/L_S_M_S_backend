package com.team_yerng.l_s_m_s.repository;

import com.team_yerng.l_s_m_s.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 1. Get all
    @Query("SELECT c FROM Category c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:code IS NULL OR c.code LIKE %:code%) AND " +
            "(:description IS NULL OR c.description LIKE %:description%)")
    Page<Category> findAllWithSearch(
            @Param("name") String name,
            @Param("code") String code,
            @Param("description") String description,
            Pageable pageable
    );

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Optional<Category> findCategoryById(@Param("id") Long id);

    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO category(name, code, description) VALUES (:name, :code, :description)", nativeQuery = true)
    void createCategory(
            @Param("name") String name,
            @Param("code") String code,
            @Param("description") String description
    );

    // Update
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :name, c.code = :code, c.description = :description WHERE c.id = :id")
    int updateCategory(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("code") String code,
            @Param("description") String description
    );

    //  Delete
    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.id = :id")
    int deleteCategory(@Param("id") Long id);
}
