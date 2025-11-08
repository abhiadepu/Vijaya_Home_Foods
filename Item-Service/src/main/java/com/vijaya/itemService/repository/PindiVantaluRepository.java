package com.vijaya.itemService.repository;

import com.vijaya.itemService.model.PindiVantalu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PindiVantaluRepository extends JpaRepository<PindiVantalu,Long> {

}
