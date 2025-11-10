package com.vijaya.itemService.controller;

import com.vijaya.itemService.dto.PindiVantaluDto;
import com.vijaya.itemService.service.PindiVantaluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items/pindivantalu")
public class PindiVantaluController {

    @Autowired
    private PindiVantaluService service;

    @PostMapping
    public ResponseEntity<PindiVantaluDto> create(@RequestBody PindiVantaluDto dto) {
        return ResponseEntity.ok(service.createPindiVantalu(dto));
    }

    @GetMapping
    public ResponseEntity<List<PindiVantaluDto>> getAll() {
        return ResponseEntity.ok(service.getAllPindiVantalu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PindiVantaluDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPindiVantaluById(id));
    }

    @PutMapping
    public ResponseEntity<PindiVantaluDto> update(@RequestBody PindiVantaluDto dto) {
        return ResponseEntity.ok(service.updatePindiVantalu(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletePindiVantalu(id));
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable Long id) {
        return ResponseEntity.ok(service.getItemPrice(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PindiVantaluDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchPindiVantalu(keyword));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<PindiVantaluDto>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(service.searchByCategory(category));
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<PindiVantaluDto>> searchByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(service.searchByPriceRange(minPrice, maxPrice));
    }
}
