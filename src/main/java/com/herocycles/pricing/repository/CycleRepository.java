package com.herocycles.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herocycles.pricing.entity.Cycle;

public interface CycleRepository extends JpaRepository<Cycle, Long> {

}