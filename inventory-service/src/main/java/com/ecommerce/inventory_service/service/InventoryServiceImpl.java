package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.dto.InventoryRequestDTO;
import com.ecommerce.inventory_service.dto.InventoryResponseDTO;
import com.ecommerce.inventory_service.exception.ResourceNotFoundException;
import com.ecommerce.inventory_service.mapper.InventoryMapper;
import com.ecommerce.inventory_service.model.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements  InventoryService
{
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true) //conjunto de operaciones roolback
    public boolean isInStock(String sku, Integer quantity) {
        return inventoryRepository.findBySku(sku)
                .map( i -> i.getQuantity()>= quantity)
                .orElse(false); //sino
    }

    @Override
    @Transactional
    public InventoryResponseDTO createInventory(InventoryRequestDTO inventoryRequest) {
        boolean exists = inventoryRepository.existsBySku(inventoryRequest.getSku());
        if(exists)
            throw new RuntimeException("El inventario para el sku " + inventoryRequest.getSku() + " ya existe");
        Inventory inventory = inventoryMapper.toInventory(inventoryRequest);//
        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventario creado para el sku: {}", savedInventory.getSku());
        return inventoryMapper.toInventoryResponse(savedInventory);
    }

    @Override
    @Transactional(readOnly = true)//cuando es solo lectura podeer readOnly en true
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toInventoryResponse)
                .toList();
    }

    @Override
    @Transactional
    public InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO inventoryRequest) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Inventory","id",id)
        );
        inventoryMapper.updateInventoryFromRequest(inventoryRequest,inventory);
        Inventory inventoryUpdate = inventoryRepository.save(inventory);
        log.info("Inventario {} actulizado",inventoryUpdate.getSku());
        return inventoryMapper.toInventoryResponse(inventoryUpdate);
    }

    @Override
    @Transactional
    public void deleteInventory(Long id) {
        if(!inventoryRepository.existsById(id))
            throw new ResourceNotFoundException("El id del inventario no existe","id",id);
        inventoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void reduceStock(String sku, Integer quantity) {
        var inventory = inventoryRepository.findBySku(sku)
                .orElseThrow(
                        ()-> new RuntimeException("Producto no encontrado" + sku)
                );
        if(inventory.getQuantity()< quantity)
            throw new RuntimeException("Stock insuficiente para: " +sku);

        inventory.setQuantity(inventory.getQuantity()-quantity);
        inventoryRepository.save(inventory);
    }
    //comand query separacion, siempre separa metoods en 2 tipos query son preguntas devuelven datos no cambian,los comand cambias datos pero no devulven nd
    //solo es ejecutar accion,
    //netonao necisito conocer que pasa,
}
