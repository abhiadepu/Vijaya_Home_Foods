package com.vijaya.itemService.controller;

import com.vijaya.itemService.dto.PickelDto;
import com.vijaya.itemService.service.PickelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items/pickels")
public class PickelController {

    @Autowired
    private PickelService pickelService;

    @PostMapping
    public ResponseEntity<PickelDto> create(@RequestBody PickelDto dto) {
        return ResponseEntity.ok(pickelService.createPickel(dto));
    }

    @GetMapping
    public ResponseEntity<List<PickelDto>> getAll() {
        return ResponseEntity.ok(pickelService.getAllPickels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PickelDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pickelService.getPickelById(id));
    }

    @PutMapping
    public ResponseEntity<PickelDto> update(@RequestBody PickelDto dto) {
        return ResponseEntity.ok(pickelService.updatePickel(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(pickelService.deletePickel(id));
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable Long id) {
        return ResponseEntity.ok(pickelService.getItemPrice(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PickelDto>> searchPickels(@RequestParam String keyword) {
        return ResponseEntity.ok(pickelService.searchPickels(keyword));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<PickelDto>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(pickelService.searchByCategory(category));
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<PickelDto>> searchByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(pickelService.searchByPriceRange(minPrice, maxPrice));
    }
}
