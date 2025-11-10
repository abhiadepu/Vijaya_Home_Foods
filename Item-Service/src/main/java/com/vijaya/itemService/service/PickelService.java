package com.vijaya.itemService.service;

import com.vijaya.itemService.dto.PickelDto;
import com.vijaya.itemService.exception.ResourceNotFoundException;
import com.vijaya.itemService.model.Pickel;
import com.vijaya.itemService.repository.PickelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickelService {

    @Autowired
    private PickelRepository pickelRepository;

    @Cacheable(value = "pickels")
    public List<PickelDto> getAllPickels() {
        System.out.println("Fetching pickels from DB...");
        return pickelRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "pickel", key = "#id")
    public PickelDto getPickelById(Long id) {
        System.out.println("Fetching pickel " + id + " from DB...");
        Pickel pickel = pickelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pickel not found with id: " + id));
        return mapToDto(pickel);
    }


    @Transactional
    @CacheEvict(value = {"pickels"}, allEntries = true)
    public PickelDto createPickel(PickelDto dto) {
        Pickel saved = pickelRepository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    @Transactional
    @CachePut(value = "pickel", key = "#dto.id()")
    @CacheEvict(value = "pickels", allEntries = true)
    public PickelDto updatePickel(PickelDto dto) {
        Pickel existing = pickelRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Pickel not found with id: " + dto.id()));
        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setCategory(dto.category());
        existing.setImage(dto.image());
        existing.setPrice(dto.price());
        return mapToDto(pickelRepository.save(existing));
    }

    @Transactional
    @CacheEvict(value = {"pickels", "pickel"}, allEntries = true)
    public String deletePickel(Long id) {
        if (!pickelRepository.existsById(id))
            throw new ResourceNotFoundException("Pickel not found with id: " + id);
        pickelRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public BigDecimal getItemPrice(Long id) {
        Pickel pickel = pickelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pickel not found with id: " + id));
        return pickel.getPrice();
    }

    private PickelDto mapToDto(Pickel p) {
        return new PickelDto(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getImage(), p.getPrice());
    }

    private Pickel mapToEntity(PickelDto d) {
        return new Pickel(d.id(), d.name(), d.description(), d.category(), d.image(), d.price());
    }

    // Search by keyword (name or description)
    public List<PickelDto> searchPickels(String keyword) {
        return pickelRepository.searchByKeyword(keyword)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Search by category
    public List<PickelDto> searchByCategory(String category) {
        return pickelRepository.findByCategoryIgnoreCase(category)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Search within a price range
    public List<PickelDto> searchByPriceRange(BigDecimal min, BigDecimal max) {
        return pickelRepository.findByPriceBetween(min, max)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
