package com.herocycles.pricing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herocycles.pricing.entity.CycleComponent;

public interface CycleComponentRepository extends JpaRepository<CycleComponent, Long> {

    List<CycleComponent> findByCycleId(Long cycleId);

}