package com.vijaya.itemService.service;

import com.vijaya.itemService.dto.PickelDto;
import com.vijaya.itemService.model.Pickel;
import com.vijaya.itemService.repository.PickelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickelService {

    @Autowired
    private PickelRepository pickelRepository;

    public PickelDto createPickel(PickelDto pickelDto) {
        Pickel pickel = new Pickel(
                pickelDto.id(),
                pickelDto.name(),
                pickelDto.description(),
                pickelDto.category(),
                pickelDto.image(),
                pickelDto.price()
        );
        Pickel saved = pickelRepository.save(pickel);
        return new PickelDto(saved.getId(), saved.getName(), saved.getDescription(), saved.getCategory(), saved.getImage(),saved.getPrice());
    }

    public List<PickelDto> getAllPickels() {
        return pickelRepository.findAll()
                .stream()
                .map(item -> new PickelDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getImage(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());
    }



    public String deletePickel(Long id) {
        if (!pickelRepository.existsById(id)) {
            return "Item not found with id: " + id;
        }
        pickelRepository.deleteById(id);
        return "Deleted the Item Successfully";
    }

    public PickelDto updatePickel(PickelDto pickelDto) {

        Pickel existingPickel = pickelRepository.findById(pickelDto.id())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + pickelDto.id()));

        existingPickel.setName(pickelDto.name());
        existingPickel.setDescription(pickelDto.description());
        existingPickel.setCategory(pickelDto.category());
        existingPickel.setImage(pickelDto.image());
        existingPickel.setPrice(pickelDto.price());

        Pickel updated = pickelRepository.save(existingPickel);

        return new PickelDto(
                updated.getId(),
                updated.getName(),
                updated.getDescription(),
                updated.getCategory(),
                updated.getImage(),
                updated.getPrice()
        );
    }

    public PickelDto getPickelById(Long id) {
        Pickel pickel = pickelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        return new PickelDto(
                pickel.getId(),
                pickel.getName(),
                pickel.getDescription(),
                pickel.getCategory(),
                pickel.getImage(),
                pickel.getPrice()
        );


    }

    public BigDecimal getItemPrice(Long id) {
        Pickel pickel = pickelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        return pickel.getPrice();
    }
}
