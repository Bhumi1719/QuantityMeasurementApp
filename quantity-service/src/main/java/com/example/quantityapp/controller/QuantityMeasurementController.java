package com.example.quantityapp.controller;

import com.example.quantityapp.dto.QuantityDTO;
import com.example.quantityapp.model.QuantityMeasurementEntity;
import com.example.quantityapp.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "quantity-service");
    }

    private String currentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO dto, @RequestParam String target) {
        return service.convert(dto, target, currentUser());
    }

    @PostMapping("/add")
    public QuantityDTO add(@RequestBody List<QuantityDTO> list) {
        return service.add(list.get(0), list.get(1), currentUser());
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody List<QuantityDTO> list) {
        return service.subtract(list.get(0), list.get(1), currentUser());
    }

    @PostMapping("/multiply")
    public QuantityDTO multiply(@RequestBody List<QuantityDTO> list) {
        return service.multiply(list.get(0), list.get(1), currentUser());
    }

    @PostMapping("/divide")
    public QuantityDTO divide(@RequestBody List<QuantityDTO> list) {
        return service.divide(list.get(0), list.get(1), currentUser());
    }

    @PostMapping("/compare")
    public boolean compare(@RequestBody List<QuantityDTO> list) {
        return service.compare(list.get(0), list.get(1), currentUser());
    }

    @GetMapping
    public List<QuantityMeasurementEntity> getAll() {
        return service.getAll(currentUser());
    }

    @GetMapping("/{id}")
    public QuantityMeasurementEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }

    @PutMapping("/{id}")
    public QuantityMeasurementEntity update(@PathVariable Long id, @RequestBody QuantityMeasurementEntity entity) {
        return service.update(id, entity);
    }
}
