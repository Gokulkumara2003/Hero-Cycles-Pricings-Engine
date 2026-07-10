package com.herocycles.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herocycles.pricing.dto.PriceBreakdownResponse;
import com.herocycles.pricing.entity.Component;
import com.herocycles.pricing.entity.Cycle;
import com.herocycles.pricing.entity.CycleComponent;
import com.herocycles.pricing.repository.ComponentRepository;
import com.herocycles.pricing.repository.CycleComponentRepository;
import com.herocycles.pricing.repository.CycleRepository;

@Service
public class CycleService {

    private final CycleRepository cycleRepository;
    private final ComponentRepository componentRepository;
    private final CycleComponentRepository cycleComponentRepository;

    public CycleService(CycleRepository cycleRepository,
                        ComponentRepository componentRepository,
                        CycleComponentRepository cycleComponentRepository) {

        this.cycleRepository = cycleRepository;
        this.componentRepository = componentRepository;
        this.cycleComponentRepository = cycleComponentRepository;
    }

    // Save a cycle
    public Cycle saveCycle(Cycle cycle) {
        return cycleRepository.save(cycle);
    }

    // Get all cycles
    public List<Cycle> getAllCycles() {
        return cycleRepository.findAll();
    }

    // Calculate total price
    public PriceBreakdownResponse getPriceBreakdown(Long cycleId) {

        Optional<Cycle> cycleOptional = cycleRepository.findById(cycleId);

        if (cycleOptional.isEmpty()) {
            return null;
        }

        Cycle cycle = cycleOptional.get();

        PriceBreakdownResponse response = new PriceBreakdownResponse();

        response.setCycleName(cycle.getName());

        List<CycleComponent> cycleComponents =
                cycleComponentRepository.findByCycleId(cycleId);

        double total = 0;

        for (CycleComponent cycleComponent : cycleComponents) {

            Optional<Component> componentOptional =
                    componentRepository.findById(cycleComponent.getComponentId());

            if (componentOptional.isPresent()) {

                Component component = componentOptional.get();

                response.getComponents().add(
                        component.getName() + " - ₹" + component.getPrice());

                total = total + component.getPrice();
            }
        }

        response.setTotalPrice(total);

        return response;
    }
 // Update Cycle
    public Cycle updateCycle(Long id, Cycle updatedCycle) {

        Cycle cycle = cycleRepository.findById(id).orElseThrow();

        cycle.setName(updatedCycle.getName());

        return cycleRepository.save(cycle);

    }

    // Delete Cycle
    public void deleteCycle(Long id) {

        cycleRepository.deleteById(id);

    }

}