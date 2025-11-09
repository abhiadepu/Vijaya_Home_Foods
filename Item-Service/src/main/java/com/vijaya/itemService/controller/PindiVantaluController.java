package com.vijaya.itemService.controller;

import com.vijaya.itemService.dto.PickelDto;
import com.vijaya.itemService.dto.PindiVantaluDto;
import com.vijaya.itemService.service.PindiVantaluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items/pindivantalu")
public class PindiVantaluController {

    @Autowired
    private PindiVantaluService pindiVantaluService;

    @PostMapping
    private PindiVantaluDto createPindiVantalu(@RequestBody PindiVantaluDto pindiVantaluDto){
        return pindiVantaluService.createPindiVantalu(pindiVantaluDto);
    }

    @GetMapping
    private List<PindiVantaluDto> getAllPindiVantalu(){
        return pindiVantaluService.getAllPindiVantalu();
    }

    @GetMapping("/{id}")
    private PindiVantaluDto getPindiVantaluById(@PathVariable Long id){
        return pindiVantaluService.getPindiVantaluById(id);
    }

    @PutMapping
    private PindiVantaluDto updatePindiVantalu(@RequestParam PindiVantaluDto pindiVantaluDto){
        return pindiVantaluService.updatePindiVantalu(pindiVantaluDto);
    }

    @DeleteMapping("/{id}")
    private String deletePindiVantalu(@PathVariable Long id) {
        return pindiVantaluService.deletePindiVantalu(id);
    }

    @GetMapping("/{id}/price")
    private BigDecimal getItemPrice(@PathVariable Long itemId){
        return pindiVantaluService.getItemPrice(itemId);
    }
}
