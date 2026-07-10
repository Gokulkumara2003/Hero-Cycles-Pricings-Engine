package com.herocycles.pricing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.herocycles.pricing.dto.PriceBreakdownResponse;
import com.herocycles.pricing.entity.Cycle;
import com.herocycles.pricing.service.CycleService;

@RestController
@RequestMapping("/cycles")
@CrossOrigin("*")
public class CycleController {

    private final CycleService cycleService;

    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    // Save Cycle
    @PostMapping
    public Cycle saveCycle(@RequestBody Cycle cycle) {
        return cycleService.saveCycle(cycle);
    }

    // Get All Cycles
    @GetMapping
    public List<Cycle> getAllCycles() {
        return cycleService.getAllCycles();
    }

    // Update Cycle
    @PutMapping("/{id}")
    public Cycle updateCycle(@PathVariable Long id,
                             @RequestBody Cycle cycle) {

        return cycleService.updateCycle(id, cycle);

    }

    // Delete Cycle
    @DeleteMapping("/{id}")
    public void deleteCycle(@PathVariable Long id) {

        cycleService.deleteCycle(id);

    }

    // Price Breakdown
    @GetMapping("/{id}/price")
    public PriceBreakdownResponse getPrice(@PathVariable Long id) {

        return cycleService.getPriceBreakdown(id);

    }

}