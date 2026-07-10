package com.herocycles.pricing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.herocycles.pricing.entity.CycleComponent;
import com.herocycles.pricing.repository.CycleComponentRepository;

@Service
public class CycleComponentService {

    private final CycleComponentRepository cycleComponentRepository;

    public CycleComponentService(CycleComponentRepository cycleComponentRepository) {
        this.cycleComponentRepository = cycleComponentRepository;
    }

    public CycleComponent saveCycleComponent(CycleComponent cycleComponent) {
        return cycleComponentRepository.save(cycleComponent);
    }

    public List<CycleComponent> getComponentsByCycleId(Long cycleId) {
        return cycleComponentRepository.findByCycleId(cycleId);
    }
}