package com.herocycles.pricing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.herocycles.pricing.entity.CycleComponent;
import com.herocycles.pricing.service.CycleComponentService;

@RestController
@RequestMapping("/cycle-components")
@CrossOrigin("*")
public class CycleComponentController {

    private final CycleComponentService cycleComponentService;

    public CycleComponentController(CycleComponentService cycleComponentService) {

        this.cycleComponentService = cycleComponentService;

    }

    @PostMapping
    public CycleComponent saveCycleComponent(@RequestBody CycleComponent cycleComponent) {

        return cycleComponentService.saveCycleComponent(cycleComponent);

    }

    @GetMapping("/{cycleId}")
    public List<CycleComponent> getComponents(@PathVariable Long cycleId) {

        return cycleComponentService.getComponentsByCycleId(cycleId);

    }

}