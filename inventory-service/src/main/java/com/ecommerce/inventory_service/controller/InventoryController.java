package com.ecommerce.inventory_service.controller;

import com.ecommerce.inventory_service.dto.InventoryRequestDTO;
import com.ecommerce.inventory_service.dto.InventoryResponseDTO;
import com.ecommerce.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController
{
    private final InventoryService  inventoryService;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean  isInStock(@PathVariable("sku") String sku,@RequestParam("quantity") Integer quantity){
        return inventoryService.isInStock(sku,quantity);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponseDTO createInventory(@RequestBody @Valid InventoryRequestDTO inventoryRequestDTO){
        return inventoryService.createInventory(inventoryRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> listInventory(){
        return inventoryService.getAllInventory();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  InventoryResponseDTO updateInventory(@PathVariable Long id, @RequestBody InventoryRequestDTO inventoryRequestDTO){
        return inventoryService.updateInventory(id, inventoryRequestDTO);
    }
    @PutMapping("/reduce")
    @ResponseStatus(HttpStatus.OK)
    public String reduceStock(@PathVariable String sku, @RequestParam Integer quantity){
        inventoryService.reduceStock(sku,quantity);
        return "Stock reducido existosamente";
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deletById(@PathVariable Long id){
        inventoryService.deleteInventory(id);
    }
}
