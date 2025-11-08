package com.vijaya.itemService.service;

import com.vijaya.itemService.dto.PindiVantaluDto;
import com.vijaya.itemService.model.PindiVantalu;
import com.vijaya.itemService.repository.PindiVantaluRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PindiVantaluService {

    @Autowired
    private PindiVantaluRepository pindiVantaluRepository;

    public PindiVantaluDto createPindiVantalu(PindiVantaluDto pindiVantaluDto){

        PindiVantalu pindiVantalu = new PindiVantalu(
                pindiVantaluDto.id(),
                pindiVantaluDto.name(),
                pindiVantaluDto.description(),
                pindiVantaluDto.category(),
                pindiVantaluDto.price(),
                pindiVantaluDto.image()
        );

        PindiVantalu saved = pindiVantaluRepository.save(pindiVantalu);


        return new PindiVantaluDto(saved.getId(),saved.getName(),saved.getDescription(), saved.getCategory(),saved.getImage(),saved.getPrice());
    }

    public List<PindiVantaluDto> getAllPindiVantalu(){
        return pindiVantaluRepository.findAll()
                .stream()
                .map(item -> new PindiVantaluDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getImage(),
                        item.getPrice()
                ))
                        .collect(Collectors.toList());


    }

    public PindiVantaluDto getPindiVantaluById(Long id) {
        PindiVantalu pindiVantalu = pindiVantaluRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        return new PindiVantaluDto(
                pindiVantalu.getId(),
                pindiVantalu.getName(),
                pindiVantalu.getDescription(),
                pindiVantalu.getCategory(),
                pindiVantalu.getImage(),
                pindiVantalu.getPrice()
        );
    }

    public PindiVantaluDto updatePindiVantalu(PindiVantaluDto pindiVantaluDto) {
        PindiVantalu pindiVantalu = pindiVantaluRepository.findById(pindiVantaluDto.id())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + pindiVantaluDto.id()));

        pindiVantalu.setName(pindiVantaluDto.name());
        pindiVantalu.setDescription(pindiVantaluDto.description());
        pindiVantalu.setCategory(pindiVantaluDto.category());
        pindiVantalu.setImage(pindiVantaluDto.image());
        pindiVantalu.setPrice(pindiVantaluDto.price());

        PindiVantalu updated = pindiVantaluRepository.save(pindiVantalu);

        return new PindiVantaluDto(
                updated.getId(),
                updated.getName(),
                updated.getDescription(),
                updated.getCategory(),
                updated.getImage(),
                updated.getPrice()
        );
    }

    public String deletePindiVantalu(Long id) {
        if (!pindiVantaluRepository.existsById(id)) {
            return "Item not found with id: " + id;
        }
        pindiVantaluRepository.deleteById(id);
        return "Deleted the Item Successfully";
    }
}
