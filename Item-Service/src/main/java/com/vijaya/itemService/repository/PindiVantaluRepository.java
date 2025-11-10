package com.vijaya.itemService.repository;

import com.vijaya.itemService.model.PindiVantalu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PindiVantaluRepository extends JpaRepository<PindiVantalu, Long> {

    List<PindiVantalu> findByNameContainingIgnoreCase(String name);

    List<PindiVantalu> findByCategoryIgnoreCase(String category);

    List<PindiVantalu> findByDescriptionContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM PindiVantalu p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<PindiVantalu> searchByKeyword(@Param("keyword") String keyword);

    List<PindiVantalu> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
