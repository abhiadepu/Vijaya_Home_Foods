package com.vijaya.itemService.repository;

import com.vijaya.itemService.model.Pickel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PickelRepository extends JpaRepository<Pickel, Long> {

    // Search by name (case-insensitive)
    List<Pickel> findByNameContainingIgnoreCase(String name);

    // Search by category
    List<Pickel> findByCategoryIgnoreCase(String category);

    // Search by description keyword
    List<Pickel> findByDescriptionContainingIgnoreCase(String keyword);

    // Combined search by name or description
    @Query("SELECT p FROM Pickel p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Pickel> searchByKeyword(@Param("keyword") String keyword);

    // Search within a price range
    List<Pickel> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
