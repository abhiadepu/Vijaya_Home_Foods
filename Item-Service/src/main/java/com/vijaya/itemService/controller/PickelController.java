package com.vijaya.itemService.controller;

import com.vijaya.itemService.dto.PickelDto;
import com.vijaya.itemService.dto.PindiVantaluDto;
import com.vijaya.itemService.service.PickelService;
import com.vijaya.itemService.service.PindiVantaluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items/pickels")
public class PickelController {
    @Autowired
    private PickelService pickelService;

    @PostMapping
    private PickelDto createPickel(@RequestBody PickelDto pickelDto){
        return pickelService.createPickel(pickelDto);
    }

    @GetMapping
    private List<PickelDto> getAllPickels(){
        return pickelService.getAllPickels();
    }

    @GetMapping("/{id}")
    private PickelDto getPickelById(@PathVariable Long id){
        return pickelService.getPickelById(id);
    }

    @PutMapping
    private PickelDto updatePickel(@RequestParam PickelDto pickelDto){
        return pickelService.updatePickel(pickelDto);
    }

    @DeleteMapping("/{id}")
    private String deletePickel(@PathVariable Long id){
        return pickelService.deletePickel(id);
    }

    @GetMapping("/{id}/price")
    private BigDecimal getItemPrice(@PathVariable Long itemId){
        return pickelService.getItemPrice(itemId);
    }
}
