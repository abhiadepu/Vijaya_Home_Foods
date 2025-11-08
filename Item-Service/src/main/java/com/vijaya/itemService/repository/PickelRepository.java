package com.vijaya.itemService.repository;

import com.vijaya.itemService.model.Pickel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickelRepository extends JpaRepository<Pickel,Long> {

}
