package com.vijaya.itemService.service;

import com.vijaya.itemService.dto.PindiVantaluDto;
import com.vijaya.itemService.exception.ResourceNotFoundException;
import com.vijaya.itemService.model.PindiVantalu;
import com.vijaya.itemService.repository.PindiVantaluRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PindiVantaluService {

    @Autowired
    private PindiVantaluRepository pindiVantaluRepository;

    @Cacheable(value = "pindivantalu")
    public List<PindiVantaluDto> getAllPindiVantalu() {
        System.out.println("Fetching PindiVantalu from DB...");
        return pindiVantaluRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "pindiItem", key = "#id")
    public PindiVantaluDto getPindiVantaluById(Long id) {
        PindiVantalu res = pindiVantaluRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PindiVantalu not found with id: " + id));
        return mapToDto(res);
    }

    @Transactional
    @CacheEvict(value = {"pindivantalu"}, allEntries = true)
    public PindiVantaluDto createPindiVantalu(PindiVantaluDto dto) {
        PindiVantalu saved = pindiVantaluRepository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    @Transactional
    @CachePut(value = "pindiItem", key = "#dto.id()")
    @CacheEvict(value = "pindivantalu", allEntries = true)
    public PindiVantaluDto updatePindiVantalu(PindiVantaluDto dto) {
        PindiVantalu item = pindiVantaluRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("PindiVantalu not found with id: " + dto.id()));
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setImage(dto.image());
        item.setPrice(dto.price());
        return mapToDto(pindiVantaluRepository.save(item));
    }

    @Transactional
    @CacheEvict(value = {"pindivantalu", "pindiItem"}, allEntries = true)
    public String deletePindiVantalu(Long id) {
        if (!pindiVantaluRepository.existsById(id))
            throw new ResourceNotFoundException("PindiVantalu not found with id: " + id);
        pindiVantaluRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public BigDecimal getItemPrice(Long id) {
        PindiVantalu res = pindiVantaluRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PindiVantalu not found with id: " + id));
        return res.getPrice();
    }

    private PindiVantaluDto mapToDto(PindiVantalu p) {
        return new PindiVantaluDto(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getImage(), p.getPrice());
    }

    private PindiVantalu mapToEntity(PindiVantaluDto d) {
        return new PindiVantalu(d.id(), d.name(), d.description(), d.category(), d.price(), d.image());
    }

    public List<PindiVantaluDto> searchPindiVantalu(String keyword) {
        return pindiVantaluRepository.searchByKeyword(keyword)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<PindiVantaluDto> searchByCategory(String category) {
        return pindiVantaluRepository.findByCategoryIgnoreCase(category)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<PindiVantaluDto> searchByPriceRange(BigDecimal min, BigDecimal max) {
        return pindiVantaluRepository.findByPriceBetween(min, max)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
