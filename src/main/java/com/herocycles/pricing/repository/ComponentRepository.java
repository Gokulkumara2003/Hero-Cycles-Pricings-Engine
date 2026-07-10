package com.herocycles.pricing.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.herocycles.pricing.entity.Component;

public interface ComponentRepository extends JpaRepository<Component,Long>{

}
